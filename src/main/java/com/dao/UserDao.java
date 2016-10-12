package com.dao;

import java.util.List;

import com.entities.User;

public interface UserDao extends GenericDao<User, Integer>{
	public User getUserByName(String username);
}
