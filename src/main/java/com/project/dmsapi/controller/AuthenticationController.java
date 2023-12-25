package com.project.dmsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dmsapi.entities.User;
import com.project.dmsapi.servies.UserService;



@Controller
@RestController
@CrossOrigin
public class AuthenticationController {
	@Autowired
	UserService userService;
	
    @ResponseBody
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user)  {
    	System.out.println(user.getPassword());
    	User foundUser = userService.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());
		if(foundUser == null)
			return ResponseEntity.ok("Kullanıcı bulunamadı");
		System.out.println("Başarılı!");
        //String token = jwtTokenUtil.generateToken(foundUser);
        return ResponseEntity.ok("OK");
    }

}
