package com.dao;


import java.util.List;

import com.entities.Client;
import com.entities.Employee;
import com.entities.User;

public interface UserDao extends GenericDao<User, Integer>{
	public User getUserByName(String username);

	public List<Employee> getAllEmployees();

	public List<Client> getAllClients();

	public List<Client> getClientWithNotifications();
}
