package com.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ImageDao;
import com.entities.Images;

@Repository
@Transactional
public class ImageDaoImpl extends GenericDaoImpl<Images, Integer> implements ImageDao {

	public ImageDaoImpl(){
		super(null);
	}
	
	public ImageDaoImpl(Class<Images> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}
