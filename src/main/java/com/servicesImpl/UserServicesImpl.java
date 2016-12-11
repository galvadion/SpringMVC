package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.UserDao;
import com.entities.Client;
import com.entities.Employee;
import com.entities.User;
import com.services.UserServices;

@Service
public class UserServicesImpl extends GenericServiceImpl<User, Integer> implements UserServices{

	private UserDao userDao;
	public UserServicesImpl(){
		
	}
	
    @Autowired
    public UserServicesImpl(
            @Qualifier("userDaoImpl") GenericDao<User, Integer> genericDao) {
        super(genericDao);
        this.userDao = (UserDao) genericDao;
    }

	public User validateLogin(String username, String password) {
		try{
		User user=userDao.getUserByName(username);
		if(user.getPassword().equals(password)) return user;
		return null;
		}catch(NullPointerException e){
			return null;
		}
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return userDao.getAllEmployees();
	}

	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return userDao.getAllClients();
	}

	@Override
	public List<Client> getClientsWithNotifications() {
		// TODO Auto-generated method stub
		return userDao.getClientWithNotifications();
	}

	
}
