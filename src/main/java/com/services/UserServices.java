package com.services;

import java.util.List;

import com.entities.Users;

public interface UserServices {
	public boolean saveOrUpdate(Users users);
	public List<Users> list();
	public boolean delete(Users users);
	public boolean validateLogin(String username, String password);
}
