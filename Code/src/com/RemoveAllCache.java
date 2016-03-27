package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class RemoveAllCache extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException{
		
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();        		                				
		cache.clearAll();
		
		res.getWriter().println("All the files from cache has been removed" );
	}
	 
}
