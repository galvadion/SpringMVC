package com.dao;


import com.entities.User;

public interface UserDao extends GenericDao<User, Integer>{
	public User getUserByName(String username);
}
