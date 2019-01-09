package com.Ozonetel.BlogSite.DAO;

import java.util.List;

import com.Ozonetel.BlogSite.Domain.Blog;



public interface BlogDAO {
	
	
	public boolean save(Blog blog);
	public List<Blog> list();
	public boolean approveBlog(int blogid);
	public Blog getBlog(int id);
	public boolean incLikes(int blogid);
	public boolean incDisLikes(int blogid);
	public boolean delete(Blog blog);

	
}