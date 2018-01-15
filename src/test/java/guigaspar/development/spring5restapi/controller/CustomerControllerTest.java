package guigaspar.development.spring5restapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import guigaspar.development.spring5restapi.exception.ResourceNotFoundException;
import guigaspar.development.spring5restapi.model.CustomerDTO;
import guigaspar.development.spring5restapi.services.CustomerService;

public class CustomerControllerTest{
	
	 @Mock
	 CustomerService customerService;

	 @InjectMocks
	 CustomerController customerController;
	 
	 MockMvc mockMvc;
	 
	 String firtsId;
	 
	 @Before
	 public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    mockMvc = MockMvcBuilders.standaloneSetup(customerController)
	            .setControllerAdvice(new RestResponseEntityExceptionHandler())
	            .build();
	 }

	 @Test
	 public void testListAllCustomers() throws Exception {
		 
		 CustomerDTO customer1 = new CustomerDTO();
		 CustomerDTO customer2 = new CustomerDTO();
		 
		 customer1.setFirstName("Guilherme");
		 customer1.setLastName("Gaspar");
		 
		 customer2.setFirstName("Guilherme");
		 customer2.setLastName("Lucena");
		 
		 when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

	        mockMvc.perform(get(CustomerController.BASE_URL)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.customers", hasSize(2)));
	 }
	 
	 @Test
	 public void testGetCustomerById() throws Exception {
	 
		 CustomerDTO customer1 = new CustomerDTO();
	     customer1.setFirstName("Guilherme");
	     customer1.setLastName("Gaspar");
	     
	     when(customerService.getCustomerById("1")).thenReturn(customer1);
	    
	        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
	                .contentType(MediaType.APPLICATION_JSON))
			        .andExpect(status().isOk())
		            .andExpect(jsonPath("$.firstName", equalTo("Guilherme")));
	        
	 }
	 
	 @Test
	 public void testUpdateCustomer() throws Exception {
		 
		 CustomerDTO customer1 = new CustomerDTO();
	     customer1.setFirstName("Guilherme");
	     customer1.setLastName("Gaspar");
	     
	     CustomerDTO returnDTO = new CustomerDTO();
	     returnDTO.setFirstName(customer1.getFirstName());
	     returnDTO.setLastName(customer1.getLastName());
	     
	     when(customerService.saveCustomerByDTO(eq("1"), any(CustomerDTO.class))).thenReturn(returnDTO);
	     
	     //when/then
	        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(customer1)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.firstName", equalTo("Guilherme")))
	                .andExpect(jsonPath("$.lastName", equalTo("Gaspar")));
	 }

	 @Test
	 public void testDeleteCustomer() throws Exception {

	    mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());

	    verify(customerService).deleteCustomerById("1");
	 }

	 @Test
	 public void testNotFoundException() throws Exception {

		when(customerService.getCustomerById("1234")).thenThrow(ResourceNotFoundException.class);

	    mockMvc.perform(get(CustomerController.BASE_URL + "/1234")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNotFound());
	 }
	 
	 public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	 }
	 
}
