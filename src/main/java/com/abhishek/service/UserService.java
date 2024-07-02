package com.abhishek.service;

import com.abhishek.entity.User;

public interface UserService {

	public User findUSerByJwtToken(String jwt) throws Exception;
	
	public User findUserByEmail(String email)throws Exception;
}
