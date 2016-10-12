package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.BrandDao;
import com.entities.Brand;
import com.services.BrandService;

@Service
public class BrandServiceImpl extends GenericServiceImpl<Brand, Integer> implements BrandService{

	private BrandDao brandDao;
	public BrandServiceImpl(){
		
	}
	
    @Autowired
    public BrandServiceImpl(
            @Qualifier("brandDaoImpl") GenericDao<Brand, Integer> genericDao) {
        super(genericDao);
        this.brandDao = (BrandDao) genericDao;
    }

	
}
