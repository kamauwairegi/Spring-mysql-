package com.spring.mysql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mysql.models.User;
import com.spring.mysql.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = (List<User>) userRepository.findAll();
		
		return users;
	}

	@Override
	public Optional<User> getUserById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}

	@Override
	public User insertUser(User user) {
		user = userRepository.save(user);
		return user;
	}

	@Override
	public void deleteUser(User user) {
		 userRepository.delete(user);
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

}
