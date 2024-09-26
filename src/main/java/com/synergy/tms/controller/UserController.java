package com.synergy.tms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.tms.entity.User;
import com.synergy.tms.service.UserService;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	
	@GetMapping
    public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(required = false) String filter) {
        return userService.getUsers(filter, page, size);
    }
	
	
	
	
	// create user api
	
		@PostMapping
		public ResponseEntity<User> createUser(@RequestBody User user){
			User savedUser=userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		}

		// get all user api
//		@GetMapping
//		public List<User> getAllUsers(){
//			return userService.getAllUsers();
//		}
	
//	get user by id api
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
		Optional<User> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
		
	}
	
	
	// update user by id
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
		if(userService.getUserById(id).isPresent()) {
			user.setId(id);
			User updatedUser = userService.saveUser(user);
			return ResponseEntity.ok(updatedUser);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id)
	{
		if(userService.getUserById(id).isPresent()) {
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
			
	}

}