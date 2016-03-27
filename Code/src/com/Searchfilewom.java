package com;

import java.io.BufferedReader;
import java.io.IOException;
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

public class Searchfilewom extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.getWriter().println(".........Search Operation in Distributed File Storage without Mem cache..........\n");
		
		String name = null;
		String filename = req.getParameter("filename");
		//res.getWriter().println("file name is : " + filename);
		List<BlobInfo> blobToRead = new LinkedList<BlobInfo>();
		Iterator<BlobInfo> iterator = new BlobInfoFactory().queryBlobInfos();
		while (iterator.hasNext())
			blobToRead.add(iterator.next());

		res.setContentType("text/plain");
		int flag = 0;
		int i=0;
		while(i<blobToRead.size())
		{
			name = blobToRead.get(i).getFilename();
			// res.getWriter().println("name : " + name);
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

			res.getWriter().println("File found in Distributed File Storage...\n");

			String time = req.getParameter("searchtime");

			long starttime = Long.parseLong(time);

			long endTime = System.currentTimeMillis();

			// res.getWriter().println("End time :"+endTime);
			long duration = (endTime - starttime);

			//String durationString = String.valueOf(duration);

			// out.println("upload into blob successful");
			res.getWriter().println("\nTime Taken to Search " + duration + "MilliSeconds\n");

			/*MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
			AppEngineFile file;
			file = (AppEngineFile) cache.get(name);
			if (file == null) { // file not present in cache
				res.getWriter().println(" File is not present in cache\n</br>");
			} else { // file present in cache
				res.getWriter().println(" File is present in cache\n</br>");
			}*/

		} else {
			res.getWriter()
					.println("\n File not Found ....\n");
		}



	}

}
