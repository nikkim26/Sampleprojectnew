package com.example.sample.project;

import com.example.sample.project.Controller.BillController;
import com.example.sample.project.Entity.Bills;
import com.example.sample.project.Service.BillService;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class SampleProjectApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	private MockMvc mockMvc;

	@Mock
	private BillService billService;

	@InjectMocks
	private BillController billController;

	private Bills bill;

	@BeforeEach
	public void setup() {
		// Initialize a sample bill object
		bill = new Bills();
		bill.setBillId(2L); // Assuming you have a billId field
		bill.setOrderId(null); // Set to null as per your request");
		bill.setTotalAmount(150.00);
		bill.setInvoiceDate("2024-10-16");
		bill.setTaxAmount(12.00);
		bill.setShippingAmount(5.00);
	}
	@BeforeEach
	public void setup1() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(billController).build();
	}
//	@Test
//	public void testGetBillById() throws Exception {
//		// Mock the service to return the sample bill
//		when(billService.getBillById(2L)).thenReturn(ResponseEntity.ok(bill));
//
//		// Perform the GET request
//		MvcResult result = mockMvc.perform(get("/api/bills/2"))
//				.andExpect(status().isOk()) // Expect 200 OK
//				.andReturn();
//
//		// Extract the response body as a string
//		String responseBody = result.getResponse().getContentAsString();
//
//
//		String expectedJson = "{\"billId\":2,\"orderId\":null,\"invoiceDate\":\"2024-10-16\",\"totalAmount\":1100.0,\"taxAmount\":120.0,\"shippingAmount\":50.0}";
//		assertEquals(expectedJson, responseBody);
//
//
//	}

//	@Test
//	public void testCreateBill() throws Exception {
//		// Create a new Bills object for the test
//		Bills billToCreate = new Bills();
//		billToCreate.setBillId(2L);
//		billToCreate.setOrderId(null);
//		billToCreate.setInvoiceDate("2024-10-16");
//		billToCreate.setTotalAmount(150.00);
//		billToCreate.setTaxAmount(12.00);
//		billToCreate.setShippingAmount(5.00);
//
//		// Mock the service to return the created bill
//		when(billService.createBill(any(Bills.class))).thenReturn(billToCreate);
//
//		// Perform the POST request and capture the result
//		MvcResult result = mockMvc.perform(post("/api/bills")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(new ObjectMapper().writeValueAsString(billToCreate)))
//				.andExpect(status().isOk())
//				.andReturn(); // Capture the result
//
//		// Get the response content
//		String responseBody = result.getResponse().getContentAsString();
//
//		// Assert that the returned JSON matches the expected bill
//		Bills createdBill = new ObjectMapper().readValue(responseBody, Bills.class);
//		assertEquals(billToCreate.getBillId(), createdBill.getBillId());
//		assertEquals(billToCreate.getOrderId(), createdBill.getOrderId());
//		assertEquals(billToCreate.getInvoiceDate(), createdBill.getInvoiceDate());
//		assertEquals(billToCreate.getTotalAmount(), createdBill.getTotalAmount());
//		assertEquals(billToCreate.getTaxAmount(), createdBill.getTaxAmount());
//		assertEquals(billToCreate.getShippingAmount(), createdBill.getShippingAmount());
//
//		// Verify that the service method was called with the correct argument
//		verify(billService).createBill(any(Bills.class));
//	}

//
//	//    }
////
	@Test
	public void testUpdateBill() throws Exception {
		// Mock the service to return the updated bill
		when(billService.updateBill(1L, bill)).thenReturn(ResponseEntity.ok(bill));

		// Perform the PUT request
		mockMvc.perform(put("/api/bills/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(bill)))
				.andExpect(status().isOk());

		// Verify that the service method was called
		verify(billService).updateBill(1L, bill);
	}
//
	@Test
	public void testDeleteBill() throws Exception {
		// Mock the service to indicate successful deletion
		when(billService.deleteBill(1L)).thenReturn(ResponseEntity.ok().build());

		// Perform the DELETE request
		mockMvc.perform(delete("/api/bills/1"))
				.andExpect(status().isOk());

		// Verify that the service method was called
		verify(billService).deleteBill(1L);
	}
}
