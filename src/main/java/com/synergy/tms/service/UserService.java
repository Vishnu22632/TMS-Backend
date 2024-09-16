package com.synergy.tms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergy.tms.entity.User;
import com.synergy.tms.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	// save user to database
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	// Get all users
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	// Get user by id
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	// delete user by id
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	
}
