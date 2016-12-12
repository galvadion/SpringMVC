package com.services;

import java.util.List;

import com.entities.Client;
import com.entities.Employee;
import com.entities.User;

public interface UserServices extends GenericService<User,Integer> {
	
	public User validateLogin(String username, String password);

	public List<Employee> getAllEmployees();

	public List<Client> getAllClients();

	public List<Client> getClientsWithNotifications();

	public Client getByDocument(String document);

}
