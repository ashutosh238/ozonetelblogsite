package com.Ozonetel.BlogSite.DAO;

import java.util.List;

import com.Ozonetel.BlogSite.Domain.Blogcomment;





public interface BlogcommentDAO {
	
	public boolean addComment(Blogcomment blogcomment);
	public boolean deleteComment(Blogcomment blogcomment);
	public List<Blogcomment> getAllComments(int blogId);
	
}
