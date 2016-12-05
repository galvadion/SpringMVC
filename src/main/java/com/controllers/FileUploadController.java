package com.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	
	 @Value("${images}")
	  private String webcontentPath;
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file,
			@RequestParam("id") String id,@RequestParam("index") String index) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				File dir = new File(webcontentPath);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + id + "-" +index +  "-" + LocalDate.now());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				Model model=modelService.get(Integer.parseInt(id));
				List<Image> imagesList=model.getImages();
				Image image=new Image();
				image.setFileLocation(id + "-" +index +  "-" + LocalDate.now());
				image.setModel(model);
				imagesList.add(image);
				imageService.saveOrUpdate(image);
				return "You successfully uploaded file=" + id;
			} catch (Exception e) {
				return "You failed to upload " + id + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + id + " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("id") String id,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != files.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				File dir = new File(webcontentPath);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + id + "-" +i + " - " + LocalDate.now());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				Model model=modelService.get(Integer.parseInt(id));
				List<Image> imagesList=model.getImages();
				Image image=new Image();
				image.setFileLocation(id + " - " + LocalDate.now());
				image.setModel(model);
				imagesList.add(image);
				imageService.saveOrUpdate(image);
				message = message + "You successfully uploaded file=" + id;
			} catch (Exception e) {
				return "You failed to upload " + id + " => " + e.getMessage();
			}
		}
		return message;
	}
}
