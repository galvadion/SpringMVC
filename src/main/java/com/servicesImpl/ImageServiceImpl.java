package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.ImageDao;
import com.entities.Image;
import com.services.ImageService;

@Service
public class ImageServiceImpl extends GenericServiceImpl<Image, Integer> implements ImageService{

	private ImageDao imagesDao;
	public ImageServiceImpl(){
		
	}
	
    @Autowired
    public ImageServiceImpl(
            @Qualifier("imageDaoImpl") GenericDao<Image, Integer> genericDao) {
        super(genericDao);
        this.imagesDao = (ImageDao) genericDao;
    }

	
}
