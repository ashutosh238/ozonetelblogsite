package com.Ozonetel.BlogSite;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.Ozonetel.BlogSite.DAO.BlogcommentDAO;
import com.Ozonetel.BlogSite.Domain.Blogcomment;

import junit.framework.Assert;

public class BlogCommentTestCase {
	

private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static BlogcommentDAO blogcommentDAO;
	
	@Autowired
private static Blogcomment blogcomment;
	
	@BeforeClass
	public static void init()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.Ozonetel");
		context.refresh();
		
		blogcommentDAO = (BlogcommentDAO) context.getBean("blogcommentDAO");
		
		blogcomment = (Blogcomment) context.getBean("blogcomment");
		
}
	
	
	 
	@Test
	public void addBlogCommentTestCase()
	{	 
		//blogcomment.setBlogid(6);
		blogcomment.setCommenttext("Its a ver nice blog for information");
		blogcomment.setLoginname("Shrinivas");
		
		
		Assert.assertEquals("Add BlogComment Test Case" , true  , blogcommentDAO.addComment(blogcomment));
		
	}

	@Ignore
	@Test
	public void listBlogCommentTestCase()
	{
		List<Blogcomment> listComments=blogcommentDAO.getAllComments(109);
		
		assertTrue("Problem in Listing BlogComments:",listComments.size()>0);
		
		for(Blogcomment blogComment:listComments)
		{
			System.out.print(blogComment.getBlogid()+":::");
			System.out.println(blogComment.getCommenttext());
		}
	}
}
