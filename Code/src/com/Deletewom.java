package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class Deletewom extends HttpServlet {

	private BlobstoreService blog = BlobstoreServiceFactory
			.getBlobstoreService();

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");

		PrintWriter out = res.getWriter();

		// PrintWriter out = response.getWriter();

		String getfilename = req.getParameter("Delete");

		String time = req.getParameter("deletetime");

		long starttime = Long.parseLong(time);

		String name = null;

		out.println("\n ..... Delete file from Distributed Storage System without memcache....  </br>");
		
		out.println("\nFile name is : " + getfilename+"</br>");

		// Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		// BlobKey blobKey = blobs.get(filename);

		List<BlobInfo> blobToRead = new LinkedList<BlobInfo>();
		Iterator<BlobInfo> iterator = new BlobInfoFactory().queryBlobInfos();
		while (iterator.hasNext())
			blobToRead.add(iterator.next());

		int flag = 0;
		int i=0;
		
		while(i < blobToRead.size()) 
		{
			name = blobToRead.get(i).getFilename();
			// res.getWriter().println("name : " + name);
			if (name.equals(getfilename)) {
				flag = 1;
				break;
			}

			else {
				flag = 0;
			}
			i++;
			
		}
		

		if (flag == 1) {
			blog.delete(blobToRead.get(i).getBlobKey());
			out.println("\nFound and deleted from blobstore...</br>");

			long endTime = System.currentTimeMillis();

			// res.getWriter().println("End time :"+endTime);
			double duration = (endTime - starttime);

			String durationString = String.valueOf(duration);

			// out.println("upload into blob successful");
			out.println("\nTime Taken " + durationString + "MilliSeconds</br>");

			

		} else {
			out.println("\nFile not found to delete..</br>");
		}


	}

}
