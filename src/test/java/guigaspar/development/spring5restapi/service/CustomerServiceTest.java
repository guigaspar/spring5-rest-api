package guigaspar.development.spring5restapi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import org.junit.Before;
import org.junit.Test;

import guigaspar.development.spring5restapi.domain.Customer;
import guigaspar.development.spring5restapi.mapper.CustomerMapper;
import guigaspar.development.spring5restapi.model.CustomerDTO;
import guigaspar.development.spring5restapi.repositories.CustomerRepository;
import guigaspar.development.spring5restapi.services.CustomerService;
import guigaspar.development.spring5restapi.services.CustomerServiceImpl;

public class CustomerServiceTest {
	
	@Mock
	CustomerRepository customerRepository;

	CustomerMapper customerMapper = CustomerMapper.INSTANCE;

	CustomerService customerService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		customerService = new CustomerServiceImpl(customerMapper, customerRepository);
	}

	@Test
	public void getAllCustomers() throws Exception {
		
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		customer1.setFirstName("Guilherme");
		customer1.setLastName("Gaspar");
		 
		customer2.setFirstName("Guilherme");
		customer2.setLastName("Lucena");

		when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

		// when
		List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

		// then
		assertEquals(2, customerDTOS.size());

	}
	

	@Test
	public void getCustomerById() throws Exception {
		// given
		Customer customer1 = new Customer();
		customer1.setId("Test");
		customer1.setFirstName("Guilherme");
		customer1.setLastName("Gaspar");

		when(customerRepository.findById("Test")).thenReturn(java.util.Optional.ofNullable(customer1));

		// when
		CustomerDTO customerDTO = customerService.getCustomerById("Test");

		assertEquals("Guilherme", customerDTO.getFirstName());
	}
	
	@Test
	public void createNewCustomer() throws Exception {

		// given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Guilherme");
		customerDTO.setLastName("Gaspar");
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());

		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

		// when
		CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

		// then
		assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
		assertEquals(customerDTO.getLastName(), savedDto.getLastName());
	}
	
	@Test
    public void saveCustomerByDTO() throws Exception {

		// given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Guilherme");
		customerDTO.setLastName("Gaspar");
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId("Test");

		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

		// when
		CustomerDTO savedDto = customerService.saveCustomerByDTO("Test", customerDTO);

		// then
		assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
		assertEquals(customerDTO.getLastName(), savedDto.getLastName());
	}
	
	@Test
    public void deleteCustomerById() throws Exception {
        customerRepository.deleteById("Test");
        verify(customerRepository, times(1)).deleteById("Test");
    }
}
