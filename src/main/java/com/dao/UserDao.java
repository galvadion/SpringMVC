package com.dao;

import java.util.List;

import com.entities.User;

public interface UserDao {
	public boolean saveOrUpdate(User users);
	public List<User> list();
	public boolean delete(User users);
	public User getUserByName(String username);
}
