package com.example.sample.project;

import com.example.sample.project.Controller.ProductController;
import com.example.sample.project.Entity.Product;
import com.example.sample.project.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Create a list of sample products using the default constructor and setters
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(100.00);
        product1.setStockQuantity(10);

        Product product2 = new Product();

        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(200.00);
        product2.setStockQuantity(20);

        List<Product> sampleProducts = Arrays.asList(product1, product2);

        // Mock the service to return the sample product list
        when(productService.getAllProducts()).thenReturn(sampleProducts);

        // Perform the GET request
        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the service method was called
        verify(productService).getAllProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        // Create a sample product using the default constructor and setters
        Product sampleProduct = new Product();

        sampleProduct.setName("Product 1");
        sampleProduct.setDescription("Description 1");
        sampleProduct.setPrice(100.00);
        sampleProduct.setStockQuantity(10);

        // Mock the service to return the sample product
        when(productService.getProductById(1L)).thenReturn(ResponseEntity.ok(sampleProduct));

        // Perform the GET request
        mockMvc.perform(get("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the service method was called
        verify(productService).getProductById(1L);
    }

    @Test
    public void testCreateProduct() throws Exception {
        // Create a new Product object for the test using setters
        Product productToCreate = new Product();
        productToCreate.setName("Product 1");
        productToCreate.setDescription("Description 1");
        productToCreate.setPrice(100.00);
        productToCreate.setStockQuantity(10);

        // Mock the service to return the created product with an ID
        Product createdProduct = new Product();

        createdProduct.setName("Product 1");
        createdProduct.setDescription("Description 1");
        createdProduct.setPrice(100.00);
        createdProduct.setStockQuantity(10);

        when(productService.createProduct(any(Product.class))).thenReturn(createdProduct);

        // Perform the POST request and capture the result
        MvcResult result = mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productToCreate)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the service method was called
        verify(productService).createProduct(any(Product.class));
    }


    @Test
    public void testUpdateProduct() throws Exception {
        // Create a sample product for the update test using setters
        Product productToUpdate = new Product();

        productToUpdate.setName("Updated Product");
        productToUpdate.setDescription("Updated Description");
        productToUpdate.setPrice(150.00);
        productToUpdate.setStockQuantity(15);

        // Mock the service to return the updated product
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(ResponseEntity.ok(productToUpdate));

        // Perform the PUT request
        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productToUpdate)))
                .andExpect(status().isOk());

        // Verify that the service method was called
        verify(productService).updateProduct(eq(1L), any(Product.class));
    }


    @Test
    public void testDeleteProduct() throws Exception {
        // Mock the service to handle deletion
        when(productService.deleteProduct(1L)).thenReturn(ResponseEntity.noContent().build());

        // Perform the DELETE request
        mockMvc.perform(delete("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify that the service method was called
        verify(productService).deleteProduct(1L);
    }
}

