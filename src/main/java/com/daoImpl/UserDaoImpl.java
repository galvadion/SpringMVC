package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entities.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory session;

	public boolean saveOrUpdate(User users) {
		// TODO Auto-generated method stub
		session.getCurrentSession().saveOrUpdate(users);
		return false;
	}

	public List<User> list() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Users").list();
		
	}

	public boolean delete(User users) {
		try{
			session.getCurrentSession().delete(users);
		}catch(Exception ex){
			return false;
		}
		return true;
	}

	public User getUserByName(String username) {
		Query query=session.getCurrentSession().createQuery("from Users where user_name = :username");
		query.setParameter("username", username);
		User user=(User) query.uniqueResult();
		return user;
	}
	
}
