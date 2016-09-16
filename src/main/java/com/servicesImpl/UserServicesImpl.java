package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entities.Users;
import com.services.UserServices;

@Service
public class UserServicesImpl implements UserServices{

	@Autowired
	UserDao userDao;
	
	public boolean saveOrUpdate(Users users) {
		// TODO Auto-generated method stub
		return userDao.saveOrUpdate(users);
	}

	public List<Users> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	public boolean delete(Users users) {
		// TODO Auto-generated method stub
		return userDao.delete(users);
	}

	public boolean validateLogin(String username, String password) {
		Users user=userDao.getUserByName(username);
		if(user.getPassword().equals(password)) return true;
		return false;
	}
	
}
