package com;

//Uploadfilewom - Upload file with out memchache

import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.users.UserService;

public class Uploadfileswom extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	private MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
	private AppEngineFile file;

	public void doPost(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();

		String time = request.getParameter("getcurtime");

		String no_of_files = request.getParameter("number_files");
		
		long starttime = Long.parseLong(time);

		//res.getWriter().println("Start time :" + starttime);

		// Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);

		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
		List<BlobKey> blobKey = blobs.get("Upload");

		// res.getWriter().println("upload into blob successful");

		long endTime = System.currentTimeMillis();

		//res.getWriter().println("End time :" + endTime);
		long duration = (endTime - starttime);

		out.println("\n Upload operation without MemCache \n</br>");
		
		out.println("Upload into Distributed File Storage successful</br>");
		
		out.println("\n No of Files uploaded : "+no_of_files +"\n</br></br>");
		
		out.println("Upload Time " + duration +"Milliseconds\n</br>");

	}
}
