package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entities.User;
import com.services.UserServices;

@Service
public class UserServicesImpl implements UserServices{

	@Autowired
	UserDao userDao;
	
	public boolean saveOrUpdate(User users) {
		// TODO Auto-generated method stub
		return userDao.saveOrUpdate(users);
	}

	public List<User> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	public boolean delete(User users) {
		// TODO Auto-generated method stub
		return userDao.delete(users);
	}

	public boolean validateLogin(String username, String password) {
		try{
		User user=userDao.getUserByName(username);
		if(user.getPassword().equals(password)) return true;
		return false;
		}catch(NullPointerException e){
			return false;
		}
	}
	
}
