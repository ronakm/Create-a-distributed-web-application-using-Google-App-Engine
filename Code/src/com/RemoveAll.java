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
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class RemoveAll extends HttpServlet {
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException {
		
		List<BlobInfo> blobToRead = new LinkedList<BlobInfo>();
        Iterator<BlobInfo> iterator = new BlobInfoFactory().queryBlobInfos();
        while(iterator.hasNext())
        	  blobToRead.add(iterator.next());
        
        res.setContentType("text/plain");
        
        for(int i=0;i<blobToRead.size();i++)
        {
        	
        	blobstoreService.delete(blobToRead.get(i).getBlobKey());
        	
        }
        
        
        
        
        MemcacheService cache = MemcacheServiceFactory.getMemcacheService();        		                				
		cache.clearAll();
		
		res.getWriter().println("All the files from cache and Distributed Storage System has been removed" );

	}
}
