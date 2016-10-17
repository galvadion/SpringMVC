package com.daoImpl;


import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
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
		Query query=currentSession().createQuery("from Users where user_name = :username");
		query.setParameter("username", username);
		User user=(User) query.getSingleResult();
		return user;
	}
	
}
