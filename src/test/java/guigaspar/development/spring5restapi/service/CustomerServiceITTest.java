package guigaspar.development.spring5restapi.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import guigaspar.development.spring5restapi.domain.Customer;
import guigaspar.development.spring5restapi.initialization.AppInitialization;
import guigaspar.development.spring5restapi.mapper.CustomerMapper;
import guigaspar.development.spring5restapi.model.CustomerDTO;
import guigaspar.development.spring5restapi.repositories.CustomerRepository;
import guigaspar.development.spring5restapi.repositories.UserRepository;
import guigaspar.development.spring5restapi.services.CustomerService;
import guigaspar.development.spring5restapi.services.CustomerServiceImpl;


@RunWith(SpringRunner.class)
@DataMongoTest
public class CustomerServiceITTest {
	
	 @Autowired
	 CustomerRepository customerRepository;
	 
	 @Autowired
	 UserRepository userRepository;
	 
	 CustomerService customerService;
	 
	 @Before
	 public void setUp() throws Exception {
	
		System.out.println("Loading Customer Data");
		System.out.println(customerRepository.findAll().size());
		AppInitialization init = new AppInitialization(customerRepository, userRepository);
		init.run();

		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
	}

	@Test
	public void updateCustomerUpdateLastName() throws Exception {
		String updatedName = "UpdatedName";
		String id = getCustomerIdValue();

		Optional<Customer> originalCustomer = customerRepository.findById(id);
		assertNotNull(originalCustomer);

		String originalLastName = originalCustomer.get().getLastName();

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastName(updatedName);
		customerDTO.setId(id);
		
		customerService.saveCustomerByDTO(id, customerDTO);

		Customer updatedCustomer = customerRepository.findById(id).get();

		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getLastName());
		//assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
		
	}

	
	
	private String getCustomerIdValue() {
		List<Customer> customers = customerRepository.findAll();
		System.out.println("Customers Found: " + customers.size());
		return customers.get(0).getId();
	}
}
