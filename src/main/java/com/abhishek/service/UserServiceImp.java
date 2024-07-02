package com.abhishek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.config.JwtProvider;
import com.abhishek.entity.User;
import com.abhishek.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUSerByJwtToken(String jwt) throws Exception {
		// TODO Auto-generated method stub
	    String email=jwtProvider.getEmailFromJwtToken(jwt);
		User user=findUserByEmail(email);
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email);
		if(user==null) {
			throw new Exception("User not Found");
		}
		return user;
	}

}
