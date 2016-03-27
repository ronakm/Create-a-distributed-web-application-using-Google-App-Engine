package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Displaycontents extends HttpServlet {

	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String name = null;
		String filename = req.getParameter("Value");
		// String filename = "/gs/blobpavan/code.txt";
		//res.getWriter().println("File name is : " + filename);
		System.out.println(filename);

		res.getWriter().println("...........Show File Content................ \n");
		
		res.getWriter().println("\n File name is : " + filename);
		
		// Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		// BlobKey blobKey = blobs.get(filename);

		List<BlobInfo> blobToRead = new LinkedList<BlobInfo>();
		
		res.getWriter().println("\n File name is : " + filename);
		
		Iterator<BlobInfo> iterator = new BlobInfoFactory().queryBlobInfos();
		while (iterator.hasNext())
			blobToRead.add(iterator.next());

		res.setContentType("text/plain");
		int flag = 0;
		int i=0;
		
		while(i < blobToRead.size())
		{
			name = blobToRead.get(i).getFilename();

			if (name.equals(filename)) {

				flag = 1;
				break;
			}

			else {
				flag = 0;
			}
			i++;
			
		}
		
		if (flag == 1) {
			FileService fileService = FileServiceFactory.getFileService();
			MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
			AppEngineFile readableFile;
			readableFile = (AppEngineFile) cache.get(name);
			if (readableFile == null) {
				res.getWriter().println("File is in Distributed File Storage \n \n");

				readableFile = fileService.getBlobFile(blobToRead.get(i)
						.getBlobKey());

				FileReadChannel readChannel = fileService.openReadChannel(
						readableFile, false);
				BufferedReader reader = new BufferedReader(Channels.newReader(
						readChannel, "UTF8"));
				String line = null;
				res.getWriter().println("File contents : ");
				//String regex = "ronak";
				//Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
				
				while ((line = reader.readLine()) != null) {

				
					res.getWriter().println(line);
				}

				readChannel.close();
			}

			else {
				res.getWriter().println("File in memchache \n");
				FileReadChannel readChannel = fileService.openReadChannel(
						readableFile, false);
				BufferedReader reader = new BufferedReader(Channels.newReader(
						readChannel, "UTF8"));
				String line = null;
				res.getWriter().println("printing the contents : \n");

				while ((line = reader.readLine()) != null) {
					res.getWriter().println(line);
				}
				
				readChannel.close();
			}

		} else {
			res.getWriter()
					.println(" File is Not Present \n");
		}

//		res.getWriter().println("result:"+search);
		
		

	}

}
