package com.synergy.tms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.synergy.tms.entity.User;
import com.synergy.tms.repository.UserRepository;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	
    @Test
    void testSaveUser() {
        User user = new User();
        user.setId(1L);
        user.setFullName("Vishnu Thakur");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("Vishnu Thakur", savedUser.getFullName());
        verify(userRepository, times(1)).save(user);  // Verify that save was called exactly once
    }

	
	
    

    @Test
    void testGetAllUsers() {
        List<User> userList = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();  // Verify that findAll was called once
    }
	
	
    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.get().getId());
        verify(userRepository, times(1)).findById(1L);  
    }
	
	
	
	@Test
	void testDeleteUser() {
		Long userId = 1L;
		
		userService.deleteUser(userId);
		verify(userRepository, times(1)).deleteById(userId); // verify that deleteById was called once
		
	}
	
	
	
	
	
	
}









