package com.example.sample.project;


import com.example.sample.project.Controller.UserController;
import com.example.sample.project.Entity.User;
import com.example.sample.project.Service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)  // Replace with your actual controller class
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // Replace with your actual service class

    @InjectMocks
    private UserController userController; // Replace with your actual controller class



    @Test
    public void testGetAllUsers() throws Exception {
        // Sample user setup with separate fields
        Long userId1 = 1L;
        String userName1 = "User 1";
        String userEmail1 = "user1@example.com";
        User user1 = new User();
        user1.setUserId(userId1);
        user1.setUsername((userName1));
        user1.setEmail(userEmail1);

        Long userId2 = 2L;
        String userName2 = "User 2";
        String userEmail2 = "user2@example.com";
        User user2 = new User();
        user2.setUserId(userId2);
        user2.setUsername(userName2);
        user2.setEmail(userEmail2);

        List<User> sampleUsers = Arrays.asList(user1, user2);

        // Mock the service
        when(userService.getAllUsers()).thenReturn(sampleUsers);

        // Perform the GET request
        MvcResult result = mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Deserialize the response to a List of User
        String responseContent = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> returnedUsers = objectMapper.readValue(responseContent, new TypeReference<List<User>>() {});

        // Assert the values directly
        assertEquals(2, returnedUsers.size());
        assertEquals(userId1, returnedUsers.get(0).getUserId());
        assertEquals(userName1, returnedUsers.get(0).getUsername());
        assertEquals(userEmail1, returnedUsers.get(0).getEmail());

        assertEquals(userId2, returnedUsers.get(1).getUserId());
        assertEquals(userName2, returnedUsers.get(1).getUsername());
        assertEquals(userEmail2, returnedUsers.get(1).getEmail());

        // Verify service call
        verify(userService).getAllUsers();
    }


    @Test
    public void testGetUserById() throws Exception {
        // Create a sample user
        User sampleUser = new User();
        sampleUser.setUserId(1L);
        sampleUser.setUsername("User 1");
        sampleUser.setEmail("user1@example.com");

        // Mock the service to return the sample user
        // If your service method returns ResponseEntity<User>
        when(userService.getUserById(1L)).thenReturn(ResponseEntity.ok(sampleUser));


        // Perform the GET request and get the result
        MvcResult result = mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Deserialize the response body to a User object
        String responseContent = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        User returnedUser = objectMapper.readValue(responseContent, User.class);

        // Assert the fields of the returned user
        assertEquals(1L, returnedUser.getUserId());
        assertEquals("User 1", returnedUser.getUsername());
        assertEquals("user1@example.com", returnedUser.getEmail());

        // Verify that the service method was called
        verify(userService).getUserById(1L);
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Define fields for the user being updated
        Long userId = 1L;
        String updatedUserName = "Updated User";
        String updatedUserEmail = "updateduser@example.com";

        // Create a user object with the updated information
        User updatedUser = new User();
        updatedUser.setUserId(userId);  // Ensure you use the correct method to set the ID
        updatedUser.setUsername(updatedUserName);
        updatedUser.setEmail(updatedUserEmail);

        // Mock the service to return the updated user
        when(userService.updateUser(anyLong(), any(User.class))).thenReturn(ResponseEntity.ok(updatedUser));

        // Perform the PUT request
        MvcResult result = mockMvc.perform(put("/api/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andReturn();

        // Deserialize the response to a User object
        String responseContent = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        User returnedUser = objectMapper.readValue(responseContent, User.class);

        // Assert the values directly
        assertEquals(userId, returnedUser.getUserId());
        assertEquals(updatedUserName, returnedUser.getUsername());
        assertEquals(updatedUserEmail, returnedUser.getEmail());

        // Verify that the service method was called
        verify(userService).updateUser(eq(userId), any(User.class));
    }


        }

