package com.Ozonetel.BlogSite;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.Ozonetel.BlogSite.DAO.BlogDAO;
import com.Ozonetel.BlogSite.Domain.Blog;




public class BlogDAOTestCase {
	

private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static BlogDAO blogDAO;
	
	@Autowired
private static Blog blog;
	
	@BeforeClass
	public static void init()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.Ozonetel");
		context.refresh();
		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		
		blog = (Blog) context.getBean("blog");
		
}
	
	
	
	@Test
	public void addBlogTestCase()
	{	
		blog.setBlogcontent("Trending Technology in the world");
		blog.setBlogname("Cloud Agent");
		blog.setDislikes(98);
		blog.setLikes(9);
		blog.setLoginname("ashu30");
		blog.setStatus("NA");
		
		Assert.assertEquals("Add Blog Test Case" , true  , blogDAO.save(blog));
		
	}

		 
}
