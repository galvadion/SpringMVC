package com.services;

import java.util.List;

import com.entities.User;

public interface UserServices extends GenericService<User,Integer> {
	
	public User validateLogin(String username, String password);
}
