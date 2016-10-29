package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.User;
import com.services.UserServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



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
	
	@RequestMapping(value="/edit/{id}",method =RequestMethod.GET)
	public ModelAndView getEdit(String id){
		ModelAndView view=new ModelAndView("user/form");
		view.addObject(userServices.get(Integer.parseInt(id)));
		return view;
	}
	
	@RequestMapping(value="/saveOrUpdate", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getSaved(User users){
		System.out.println(users);
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
	
	@RequestMapping(value="/getbyid", method=RequestMethod.GET)
	public ResponseEntity<Object> getById(@RequestParam("id") Integer id){
		return ResponseEntity.ok((Object) userServices.get(id));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestParam("id") Integer id){
		User user=userServices.get(id);
		user.setActive(false);
		userServices.saveOrUpdate(user);
		return ResponseEntity.ok((Object)"It has been removed");
	}
}
