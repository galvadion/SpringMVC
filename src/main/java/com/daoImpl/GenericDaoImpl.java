package com.daoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metamodel.source.annotations.entity.EntityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GenericDao;


@Repository
@Transactional
@SuppressWarnings("unchecked")
public abstract class GenericDaoImpl<T, K extends Serializable> implements GenericDao<T, K> {

	@Autowired
	private SessionFactory session;
	
	protected Class<? extends T> daoType;
	
	public GenericDaoImpl(Class<T> entityClass){
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
	}
	
	protected Session currentSession(){
		return session.getCurrentSession();
	}
	
	public boolean saveOrUpdate(T object) {
		session.getCurrentSession().saveOrUpdate(object);
		return false;
	}

	public boolean delete(T object) {
		try{
			session.getCurrentSession().delete(object);
		}catch(Exception ex){
			return false;
		}
		return true;
	}
    public T getById(K key) {
        return (T) currentSession().get(daoType, key);
    }
     
    public List<T> getAll() {
        return currentSession().createCriteria(daoType).list();
    }


}
