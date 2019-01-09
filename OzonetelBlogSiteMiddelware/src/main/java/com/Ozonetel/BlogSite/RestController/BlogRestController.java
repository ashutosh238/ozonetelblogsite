package com.Ozonetel.BlogSite.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ozonetel.BlogSite.DAO.BlogDAO;
import com.Ozonetel.BlogSite.DAO.BlogcommentDAO;
import com.Ozonetel.BlogSite.DAO.UserDAO;
import com.Ozonetel.BlogSite.Domain.Blog;
import com.Ozonetel.BlogSite.Domain.Blogcomment;
import com.Ozonetel.BlogSite.Domain.User;


@RestController
public class BlogRestController 
	{ 
	
		@Autowired
		UserDAO userDAO;
		
		@Autowired
		BlogDAO blogDAO;
		
		@Autowired
		BlogcommentDAO blogcommentDAO;
		
		
		@GetMapping("/listBlogs")
		public ResponseEntity<List<Blog>> listBlogs()
		{
			List<Blog> listBlogs=blogDAO.list();
			if(listBlogs.size()>0)
			{
				return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
			}
		
		}

		
	
		@PostMapping("/addBlog")
		public ResponseEntity<Blog> addBlog(@RequestBody Blog blog,HttpSession session)
		{
			User userDetail=(User)session.getAttribute("userDetail");
			
			 
			blog.setLoginname(userDetail.getLoginname());
			blog.setStatus("NA");
			if(blogDAO.save(blog))
			{
				return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
			}
		}
		
		
		@RequestMapping("/blog/approve/{blogid}")
		public ResponseEntity<Blog> approveBlog(@PathVariable int blogid)
		{
			if(blogDAO.approveBlog(blogid))
			{
				Blog blog = blogDAO.getBlog(blogid);
				blog.setStatus("A");
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
			else
			{
				Blog blog = blogDAO.getBlog(blogid);
				blog.setStatus("Could not Approved, Error Occurred");
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			}
		}
		
			
		@RequestMapping("blog/incLike/{blogid}")
		public ResponseEntity<Blog> incLike(@PathVariable int blogid)
		{
			if(blogDAO.incLikes(blogid))
			{
				Blog blog = blogDAO.getBlog(blogid);
				blog.setStatus("Like incremented successfully");
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
			else
			{
				Blog blog = blogDAO.getBlog(blogid);
				blog.setStatus("Error Occurred");
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			}
		}
		

		@RequestMapping("blog/incDisLike/{blogid}")
		public ResponseEntity<Blog> incDisLike(@PathVariable int blogid)
		{
			if(blogDAO.incDisLikes(blogid))
			{
				Blog blog = blogDAO.getBlog(blogid);
				blog.setStatus("DisLike incremented successfully");
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
			else
			{
				Blog blog = blogDAO.getBlog(blogid);
				blog.setStatus("Error Occurred");
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			}
		}
		
		
		@GetMapping("/deleteBlog/{blogid}")
		public ResponseEntity<String> deleteBlog(@PathVariable("blogid")int blogid)
		{
			Blog blog=blogDAO.getBlog(blogid);
			
			if(blogDAO.delete(blog))
			{
				return new ResponseEntity("Success",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity("Failure",HttpStatus.NOT_FOUND);
			}
		}
		
		@PostMapping("blog/comment")
		public ResponseEntity<Blogcomment> commentBlog(@RequestBody Blogcomment blogComment,HttpSession session)
		{
			User userDetail=(User)session.getAttribute("userDetail");
			int blogid = (Integer)session.getAttribute("blogidforcomment");
		
			blogComment.setLoginname(userDetail.getLoginname());
			blogComment.setBlogid(blogid);
			if(blogcommentDAO.addComment(blogComment))
			{
				return new ResponseEntity<Blogcomment>(blogComment, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Blogcomment>(blogComment, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
		

		@GetMapping("/getBlog/{blogid}")
		public ResponseEntity<Blog> getBlog(@PathVariable("blogid")int blogid,HttpSession session)
		{
			Blog blog=blogDAO.getBlog(blogid);
			if(blog!=null)
			{
				session.setAttribute("blogidforcomment", blogid);
				return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			}
			else
			{
				
				return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
			}
		}
	
		@RequestMapping("blog/listComments/{blogid}")
		public ResponseEntity<List<Blogcomment>> listBlogComments(@PathVariable int blogid)
		{
			List<Blogcomment> blogcomments = blogcommentDAO.getAllComments(blogid);
			if(blogcomments.isEmpty())
			{
				return new ResponseEntity<List<Blogcomment>>(blogcomments, HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<List<Blogcomment>>(blogcomments, HttpStatus.OK);
			}
	}
		
		
		
	

	}
