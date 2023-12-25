package com.project.dmsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dmsapi.entities.DocFile;
import com.project.dmsapi.entities.User;
import com.project.dmsapi.helper.BaseHelper;
import com.project.dmsapi.servies.UserService;

import io.micrometer.common.util.StringUtils;



@Controller
@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	BaseHelper baseHelper;
	
	@GetMapping("/get-all")
	public List<User> getUser() {
		return userService.findAllUser();
	}

	@PostMapping("/create")
	public ResponseEntity createUser(@RequestBody User user) {
		try {
			if(StringUtils.isNotBlank(user.getPassword()))
				user.setPassword(baseHelper.generateHashedPassword(user.getPassword()));
			userService.saveUser(user);
			return ResponseEntity.ok("OK");
		}
		catch(Exception ex) {
			return ResponseEntity.ok("ERROR");
		}
	}

	@PutMapping("/update")
	public ResponseEntity updateUser(@RequestBody User user) {
		try {
			if(StringUtils.isNotBlank(user.getPassword()))
				user.setPassword(baseHelper.generateHashedPassword(user.getPassword()));
			userService.saveUser(user);
			return ResponseEntity.ok("OK");
		}
		catch(Exception ex) {
			return ResponseEntity.ok("ERROR");
		}
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity deleteUser(@PathVariable int userId) {
		try {
			userService.deleteUser(userId);
			return ResponseEntity.ok("OK");
		}
		catch(Exception ex) {
			return ResponseEntity.ok("ERROR");
		}
	}

}
