package com;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;

public class SizeStorage extends HttpServlet {

		private BlobInfoFactory infoFactory=new BlobInfoFactory();
		
		public void doPost(HttpServletRequest req, HttpServletResponse res)
		        throws ServletException, IOException{
			
			double siz_storage=0.0;
			List<BlobInfo> blobToRead = new LinkedList<BlobInfo>();
	        Iterator<BlobInfo> iterator = new BlobInfoFactory().queryBlobInfos();
	        while(iterator.hasNext())
	        	  blobToRead.add(iterator.next());
	        
	        res.setContentType("text/plain");
	        
	        for(int i=0;i<blobToRead.size();i++)
	        {
	        	
	        	
	        	siz_storage= siz_storage+blobToRead.get(i).getSize();
	        	
	        	
	        }
			
	        res.getWriter().println("Total Number of FIles in Storage DIstributed System are " + blobToRead.size());
		
			res.getWriter().println("Size of Distributed STorage is :" +  siz_storage/(1024*1024) + "MB");
			
			
			
		}
}
