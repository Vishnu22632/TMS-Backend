package com.synergy.tms.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synergy.tms.entity.User;
import com.synergy.tms.service.UserService;


class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // to simulate Http request to the controller
    

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // initialize the Mockito Mock
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createUserTest() throws Exception {
        User user = new User(null, "Vishnu Thakur", "vishnu22632@gmail.com", "mahi07", "Lalitpur");
        User savedUser = new User(1L, "Vishnu Thakur", "vishnu22632@gmail.com", "mahi07", "Lalitpur");

        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fullName").value("Vishnu Thakur"))
                .andExpect(jsonPath("$.address").value("Lalitpur"));
        
       
        		

        verify(userService, times(1)).saveUser(any(User.class));
    }

//    @Test
//    void getAllUsersTest() throws Exception {
//        User user1 = new User(1L, "Vishnu Thakur", "vishnu22632@gmail.com", "123", "Lalitpur");
//        User user2 = new User(2L, "Anusha Yadav", "anusha123@gmail.com", "3333", "Janakpur");
//
//        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));
//
//        mockMvc.perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2));
//
//        verify(userService, times(1)).getAllUsers();
//    }

    @Test
    void getUserByIdTest() throws Exception {
        User user = new User(1L, "Vishnu Thakur", "vishnu22632@gmail.com", "123", "Lalitpur");

        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Vishnu Thakur"));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void updateUserTest() throws Exception {
        User updatedUser = new User(1L, "Vishnu Thakur", "vishnu22632@gmail.com", "mahi07", "Kathmandu");

        when(userService.getUserById(1L)).thenReturn(Optional.of(updatedUser));
        when(userService.saveUser(any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").value("mahi07"));

        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void deleteUserTest() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1L);
    }
}




