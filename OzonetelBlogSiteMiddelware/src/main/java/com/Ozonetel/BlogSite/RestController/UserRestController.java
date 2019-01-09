package com.Ozonetel.BlogSite.RestController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ozonetel.BlogSite.DAO.UserDAO;
import com.Ozonetel.BlogSite.Domain.User;

@RestController
public class UserRestController

	{	@Autowired
		UserDAO userDAO;
	
		@PostMapping("/registerUser")
		public ResponseEntity<User> registerUser(@RequestBody User userDetail)
		{
			userDetail.setRoles("ROLE_USER");
			if(userDAO.registerUser(userDetail))
			{
				return new ResponseEntity<User>(userDetail,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<User>(userDetail,HttpStatus.NOT_FOUND);
			}
		}
		
		@PostMapping("/checkLogin")
		public ResponseEntity<User> checkLogin(@RequestBody User userDetail,HttpSession session)
		{
			User tempUser=userDAO.checkUser(userDetail);
			if(tempUser!=null)
			
			{
				session.setAttribute("userDetail",tempUser);
				session.setAttribute("loginname",tempUser.getLoginname());
				return new ResponseEntity<User>(tempUser,HttpStatus.OK);
				
			}
			else
			{
				return new ResponseEntity<User>(tempUser,HttpStatus.NOT_FOUND);
			}
			
		
		
		}
	}
