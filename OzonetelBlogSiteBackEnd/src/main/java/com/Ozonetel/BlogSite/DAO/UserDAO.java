package com.Ozonetel.BlogSite.DAO;

import com.Ozonetel.BlogSite.Domain.User;

public interface UserDAO {
	public boolean registerUser(User user);
	public boolean updateUser(User user);
	public User getUser(String loginname);	
	public User checkUser(User user);


}
