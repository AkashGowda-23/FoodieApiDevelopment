package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service

public class UserService {
	
	
	 @Autowired
	    private UserRepository userRepository;
	 
	 public User registerUser(User user) {
	        
	        user.setRoles("ROLE_USER");
	        return userRepository.save(user);
	    }
}
