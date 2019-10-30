package com.spring.mysql.controllers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mysql.models.User;
import com.spring.mysql.service.UserService;
import com.spring.mysql.utils.Constant;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(Constant.USERS)
	public List<User> getAllUsers() {

		return userService.getAllUsers();
	}

	@GetMapping(Constant.GET_USER)
	public Optional<User> getUser(@PathVariable Integer id) {
		Optional<User> user = userService.getUserById(id);
		return user;
	}

	//@PostMapping(Constant.INSERT_USER) : normal case use post, used get for testing
	@GetMapping(Constant.INSERT_USER)
	public User insertUser(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "email", required = true) String email) {

		try {
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			
			//testing purpose
			String password = "password1";

	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

	        StringBuilder sb = new StringBuilder();
	        for (byte b : hashInBytes) {
	            sb.append(String.format("%02x", b));
	        }
	        
			user.setPassword(sb.toString());
			user.setRole(1);
			return userService.insertUser(user);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@GetMapping(Constant.DELETE_USER)
	public void deleteUser(@PathVariable Integer id) {
		User user = userService.getUserById(id).orElse(null);
		if(user == null)
			return;
		
		userService.deleteUser(user);
	}
	
	@GetMapping(Constant.DELETE_USER_BY_ID)
	public void deleteUserById(@PathVariable Integer id) {
		userService.deleteUserById(id);
	}
}
