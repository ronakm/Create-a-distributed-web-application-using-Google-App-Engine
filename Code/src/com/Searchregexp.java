package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.users.UserService;

public class Searchregexp extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	private MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
	private AppEngineFile file;

	public void doPost(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {

		
		res.getWriter().println("\n.................Regular Expression in File Content...................\n");
		
		
		//String filename = request.getParameter("regexp");
		//Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		
		
		String name = null;
		String filename = request.getParameter("filename");
		
		String regexp=request.getParameter("regexp");
		
		
		int search=0;
		
		// String filename = "/gs/blobpavan/code.txt";
		//res.getWriter().println("file name is : " + filename);
		System.out.println(filename);

		// Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		// BlobKey blobKey = blobs.get(filename);

		List<BlobInfo> blobToRead = new LinkedList<BlobInfo>();
		Iterator<BlobInfo> iterator = new BlobInfoFactory().queryBlobInfos();
		while (iterator.hasNext())
			blobToRead.add(iterator.next());

		res.setContentType("text/plain");
		int flag = 0;
		int i;
		for (i = 0; i < blobToRead.size(); i++) {

			name = blobToRead.get(i).getFilename();

			if (name.equals(filename)) {

				flag = 1;
				break;
			}

			else {
				flag = 0;
			}
		}

		if (flag == 1) {
			FileService fileService = FileServiceFactory.getFileService();
			MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
			AppEngineFile readableFile;
			readableFile = (AppEngineFile) cache.get(name);
			if (readableFile == null) {
				//res.getWriter().println("file is in blobstore");

				readableFile = fileService.getBlobFile(blobToRead.get(i)
						.getBlobKey());

				FileReadChannel readChannel = fileService.openReadChannel(
						readableFile, false);
				BufferedReader reader = new BufferedReader(Channels.newReader(
						readChannel, "UTF8"));
				String line = null;
				//res.getWriter().println("printing the contents : ");
				//String regex = "ronak";
				//Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
				
				Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
				
				while ((line = reader.readLine()) != null) {

					Matcher matcher = pattern.matcher(line);
					  
					  if(matcher.find())
					  {
					  search=1;
					  }
					
				
					//res.getWriter().println(line);
				}

				readChannel.close();
			}
			else {
				//res.getWriter().println("file in memchache ");
				FileReadChannel readChannel = fileService.openReadChannel(
						readableFile, false);
				BufferedReader reader = new BufferedReader(Channels.newReader(
						readChannel, "UTF8"));
				String line = null;
				//res.getWriter().println("printing the contents : ");

				Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
				
				while ((line = reader.readLine()) != null) {

					Matcher matcher = pattern.matcher(line);
					  
					  if(matcher.find())
					  {
					  search=1;
					  }
					
				
					//res.getWriter().println(line);
				}

				
				readChannel.close();
			}


			if(search==1)
			{
				
				res.getWriter().println("\n Regular Expression Found in the file content \n");
				
			}
			else
			{
				
				res.getWriter().println("\nRegular Expression not Found in the file content");
			}
			
			

		} else {
			res.getWriter().println("File not found...");
		}

		
		
		
//		res.getWriter().println("result:"+search);
		//res.getWriter().println("we are inside Value.java  ");

		
		
		
	}
}
