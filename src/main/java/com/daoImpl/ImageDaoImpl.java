package com.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ImageDao;
import com.entities.Image;

@Repository
@Transactional
public class ImageDaoImpl extends GenericDaoImpl<Image, Integer> implements ImageDao {

	public ImageDaoImpl(){
		super(null);
	}
	
	public ImageDaoImpl(Class<Image> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}
