package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entities.Client;
import com.entities.User;
import com.services.UserServices;

@Controller
@RequestMapping(value = "client")
public class ClientController {

	@Autowired(required = true)
	private UserServices userServices;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("user/list");
		return view;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("user/form");
		return view;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getEditPage() {
		ModelAndView view = new ModelAndView("user/form");
		return view;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Object> getSaved(@RequestBody Client users) {
		try {
			userServices.saveOrUpdate(users);
			return ResponseEntity.ok((Object) users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Object) "There has been an error");
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAll(User users) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<User> list = userServices.getAll();

		if (list != null) {
			map.put("status", "200");
			map.put("message", "Data found");
			map.put("data", list);
		} else {
			map.put("status", "404");
			map.put("message", "Data not found");

		}

		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> delete(User users) {
		Map<String, Object> map = new HashMap<String, Object>();

		userServices.remove(users);
		map.put("status", "200");
		map.put("message", "Your record have been deleted successfully");

		return map;
	}

}
