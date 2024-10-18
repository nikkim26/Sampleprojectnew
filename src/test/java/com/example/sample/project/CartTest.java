package com.example.sample.project;


import com.example.sample.project.Controller.BillController;
import com.example.sample.project.Controller.CartController;
import com.example.sample.project.Entity.Bills;
import com.example.sample.project.Entity.Cart;
import com.example.sample.project.Service.BillService;

import com.example.sample.project.Service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CartTest {
    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testGetCartByUserId() throws Exception {
        // Create a sample cart
        Cart sampleCart = new Cart();
        sampleCart.setCartId(1L);
        sampleCart.setUserId(1L);
        sampleCart.setTotalPrice(100.00);

        // Mock the service to return the sample cart
        when(cartService.getCartByUserId(1L)).thenReturn(ResponseEntity.ok(sampleCart));

        // Perform the GET request
        mockMvc.perform(get("/api/carts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the service method was called
        verify(cartService).getCartByUserId(1L);
    }

    @Test
    public void testCreateCart() throws Exception {
        // Create a new Cart object for the test
        Cart cartToCreate = new Cart();
        cartToCreate.setCartId(1L);
        cartToCreate.setUserId(1L);
        cartToCreate.setTotalPrice(100.00);

        // Mock the service to return the created cart
        when(cartService.createCart(any(Cart.class))).thenReturn(cartToCreate);

        // Perform the POST request and capture the result
        MvcResult result = mockMvc.perform(post("/api/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cartToCreate)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the service method was called
        verify(cartService).createCart(any(Cart.class));
    }

    @Test
    public void testUpdateCart() throws Exception {
        // Create a Cart object for the update test
        Cart cartToUpdate = new Cart();
        cartToUpdate.setCartId(1L);
        cartToUpdate.setUserId(1L);
        cartToUpdate.setTotalPrice(150.00);

        // Mock the service to return the updated cart
        when(cartService.updateCart(eq(1L), any(Cart.class))).thenReturn(ResponseEntity.ok(cartToUpdate));

        // Perform the PUT request
        mockMvc.perform(put("/api/carts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cartToUpdate)))
                .andExpect(status().isOk());

        // Verify that the service method was called
        verify(cartService).updateCart(eq(1L), any(Cart.class));
    }

}
