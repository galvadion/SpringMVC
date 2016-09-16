package com.dao;

import java.util.List;

import com.entities.Users;

public interface UserDao {
	public boolean saveOrUpdate(Users users);
	public List<Users> list();
	public boolean delete(Users users);
	public Users getUserByName(String username);
}
