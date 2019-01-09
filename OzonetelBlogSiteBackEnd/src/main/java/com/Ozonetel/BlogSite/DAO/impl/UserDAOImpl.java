package com.Ozonetel.BlogSite.DAO.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Ozonetel.BlogSite.DAO.UserDAO;
import com.Ozonetel.BlogSite.Domain.User;


	@Repository("userDAO")
	@Transactional
	public class UserDAOImpl implements UserDAO {

		
		@Autowired
		private SessionFactory sessionFactory;
		
		public boolean registerUser(User user) {
			
			
			try {
				sessionFactory.getCurrentSession().saveOrUpdate(user);
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
			
			return true;
		}

		public boolean updateUser(User user) {
			
			try {
				 	sessionFactory.getCurrentSession().update(user);
			} 
			catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
			
			return true;
		}

		public User getUser(String loginname) {
			Session session=sessionFactory.openSession();
			User user=(User)session.get(User.class,loginname);
			session.close();
			return user;

		}

		public User checkUser(User user) {
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from User where loginname=:myloginname and password=:password");
			
			
		
			query.setParameter("myloginname",user.getLoginname() );
			query.setParameter("password",user.getPassword());
			List<User> listuser=query.list();
			User user1=listuser.get(0);
			session.close();
			return user1;

		}
		
		

		

	}


	
