package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entities.User;
import com.services.UserServices;

@Controller
@RequestMapping(value="user")
public class UserController {
	
	@Autowired(required = true)
	private UserServices userServices;
	
	@RequestMapping(value="/profile",method =RequestMethod.GET)
	public ModelAndView getPage(){
		ModelAndView view=new ModelAndView("user/profile");
		return view;
	}
	
	@RequestMapping(value="/saveOrUpdate", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getSaved(User users){
		Map<String,Object> map = new HashMap<String,Object>();
		
		userServices.saveOrUpdate(users);
			map.put("status","200");
			map.put("message","Your record have been saved successfully");
		
		
		return map;
	}
	
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getAll(User users){
		Map<String,Object> map = new HashMap<String,Object>();
	
			List<User> list = userServices.getAll();
			
			if (list != null){
				map.put("status","200");
				map.put("message","Data found");
				map.put("data", list);
			}else{
				map.put("status","404");
				map.put("message","Data not found");
				
			}
		
		return map;
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delete(User users){
		Map<String,Object> map = new HashMap<String,Object>();
		
		userServices.remove(users);
			map.put("status","200");
			map.put("message","Your record have been deleted successfully");
		
		
		return map;
	}
	
}
