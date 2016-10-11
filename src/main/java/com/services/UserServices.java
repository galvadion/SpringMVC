package com.services;

import java.util.List;

import com.entities.User;

public interface UserServices {
	public boolean saveOrUpdate(User users);
	public List<User> list();
	public boolean delete(User users);
	public boolean validateLogin(String username, String password);
}
