package com.spring.mysql.service;

import java.util.List;
import java.util.Optional;

import com.spring.mysql.models.User;


public interface UserService {

	public List<User> getAllUsers();
	
	public Optional<User> getUserById(Integer id);
	
	public User insertUser(User user);
	
	public void deleteUser(User user);
	
	public void deleteUserById(Integer id);
}
