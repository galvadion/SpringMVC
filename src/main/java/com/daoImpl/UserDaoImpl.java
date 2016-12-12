package com.daoImpl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entities.Client;
import com.entities.Employee;
import com.entities.User;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	public UserDaoImpl(){
		super(null);
	}
	public UserDaoImpl(Class<User> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	public User getUserByName(String username) {
		Query query=currentSession().createQuery("from User where email = :username and active=true");
		query.setParameter("username", username);
		User user=(User) query.getSingleResult();
		return user;
	}
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		Query query=currentSession().createQuery("from Employee where active=true");
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Client> getAllClients() {
		Query query=currentSession().createQuery("from Client where active=true");
		return query.getResultList();
	}
	@Override
	public List<Client> getClientWithNotifications() {
		Query query=currentSession().createQuery("from Client where active=true and allowNotification=true");
		return query.getResultList();
	}
	@Override
	public Client getByDocument(String document) {
		Query query=currentSession().createQuery("from User where document = :document");
		query.setParameter("document", document);
		Client user=(Client) query.getSingleResult();
		return user;
	}
	
}
