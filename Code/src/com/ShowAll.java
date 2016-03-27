package com;

import java.io.IOException;
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
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;



public class ShowAll extends HttpServlet {
	
		private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		 public void doPost(HttpServletRequest req, HttpServletResponse res)
			        throws ServletException, IOException {
			
			
			 res.getWriter().println(".................List of All the Files .................\n</br>");
			 
			 String filename = req.getParameter("List"); 		   
			 System.out.println("file name is : " + filename);
			 
			 //Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		     //BlobKey blobKey = blobs.get(filename);
		     
		     List<BlobInfo> blobToRead = new LinkedList<BlobInfo>();
		        Iterator<BlobInfo> iterator = new BlobInfoFactory().queryBlobInfos();
		        while(iterator.hasNext())
		        	  blobToRead.add(iterator.next());
		        
		        res.setContentType("text/plain");
		       
		        
		        for(int i=0;i<blobToRead.size();i++)
		        {
		        	String name = blobToRead.get(i).getFilename();
		        	System.out.println("fname "+ i + ":" +  name);
		        	//res.getWriter().println("fname "+ i + ":" +  name);
		        	
		        	
					 res.getWriter().println("fname "+ i + ":" +  name);
		        	
		        }
		        

		      
		        
			

	}

}

