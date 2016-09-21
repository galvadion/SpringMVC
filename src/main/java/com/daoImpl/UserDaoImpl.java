package com.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entities.Users;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory session;

	public boolean saveOrUpdate(Users users) {
		// TODO Auto-generated method stub
		session.getCurrentSession().saveOrUpdate(users);
		return false;
	}

	public List<Users> list() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Users").list();
		
	}

	public boolean delete(Users users) {
		try{
			session.getCurrentSession().delete(users);
		}catch(Exception ex){
			return false;
		}
		return true;
	}

	public Users getUserByName(String username) {
		return (Users) session.getCurrentSession().createQuery("from Users where username="+username).uniqueResult();
	}
	
}
