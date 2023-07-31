package com.healthcare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.healthcare.model.User;
import com.healthcare.repo.UserRepository;



public class UserDetailServiceImpl implements UserDetailsService{
	
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		 User user=userRepo.getUserByUserName(username);
         if(user==null) {
       	  throw new UsernameNotFoundException("Could not found username");
         }
         
         CustomUserDetail u=new CustomUserDetail(user);
	
	
	
	return u;


	}

}
