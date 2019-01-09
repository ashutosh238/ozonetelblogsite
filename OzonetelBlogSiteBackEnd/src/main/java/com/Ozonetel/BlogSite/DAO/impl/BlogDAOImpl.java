package com.Ozonetel.BlogSite.DAO.impl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Ozonetel.BlogSite.DAO.BlogDAO;
import com.Ozonetel.BlogSite.Domain.Blog;




@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean save(Blog blog) {
		 
			
			try {
				 
				blog.setCreatedate(new Date(System.currentTimeMillis()));
				sessionFactory.getCurrentSession().save(blog);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		
	}
	public Blog getBlog(int blogid) {
		return sessionFactory.getCurrentSession().get(Blog.class, blogid);
}

	public List<Blog> list() {
		return sessionFactory.getCurrentSession().createQuery("from Blog").list();
	}

	public boolean approveBlog(int blogid) {
		try {
			Blog blog = this.getBlog(blogid);
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
}
	
	public boolean incLikes(int blogid) {
		try {
			Blog blog = getBlog(blogid);
			blog.setLikes(blog.getLikes() + 1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean incDisLikes(int blogid) {
		try {
			Blog blog = getBlog(blogid);
			blog.setDislikes(blog.getDislikes() + 1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean delete(Blog blog) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
		
	}



	
}
