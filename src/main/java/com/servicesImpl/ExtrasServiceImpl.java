package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.ExtrasDao;
import com.dao.GenericDao;
import com.entities.Extras;
import com.services.ExtrasService;

@Service
public class ExtrasServiceImpl extends GenericServiceImpl<Extras, Integer> implements ExtrasService{

	private ExtrasDao extrasDao;
	public ExtrasServiceImpl(){
		
	}
	
    @Autowired
    public ExtrasServiceImpl(
            @Qualifier("extrasDaoImpl") GenericDao<Extras, Integer> genericDao) {
        super(genericDao);
        this.extrasDao = (ExtrasDao) genericDao;
    }

	
}
