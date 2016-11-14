package com.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Image;
import com.entities.Model;
import com.services.ImageService;
import com.services.ModelService;

@Controller
@RequestMapping(value = "upload")
public class FileUploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("upload/form");
		return view;
	}
	
	@Autowired
	ModelService modelService;
	
	@Autowired
	ImageService imageService;
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file) {
		String name = "mifoto";
		String id = "9";
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("user.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				Model model=modelService.get(Integer.parseInt(id));
				List<Image> imagesList=model.getImages();
				Image image=new Image();
				image.setFileLocation(serverFile.toString());
				image.setModel(model);
				imagesList.add(image);
				imageService.saveOrUpdate(image);
				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("user.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				message = message + "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}
