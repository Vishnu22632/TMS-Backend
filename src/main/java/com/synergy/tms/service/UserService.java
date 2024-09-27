package com.synergy.tms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	
	 // Get paginated users with filtering by full name, email, or address
    public Page<User> getUsers(String filter, int page, int size) {
        if (filter != null && !filter.isEmpty()) {
            return userRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrAddressContainingIgnoreCase(
                filter, filter, filter, PageRequest.of(page, size));
        }
        return userRepository.findAll(PageRequest.of(page, size));
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