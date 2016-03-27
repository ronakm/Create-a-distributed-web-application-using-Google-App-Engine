package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class Stats extends HttpServlet {
	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException{
		
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService(); 
		  final com.google.appengine.api.memcache.Stats statistics=cache.getStatistics();
		  if (null != statistics) {
		    double size=statistics.getTotalItemBytes();
		    double num= statistics.getItemCount();
		    res.getWriter().println("No of files in Cache are :" +  num+ "\n");
			res.getWriter().println("Total space allocated to Files in Cache is :" +  size/(1024*1024) +"MB");
		
	}
	}
}
