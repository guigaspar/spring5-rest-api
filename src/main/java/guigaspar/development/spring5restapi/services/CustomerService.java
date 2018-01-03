package guigaspar.development.spring5restapi.services;

import java.util.List;

import guigaspar.development.spring5restapi.model.CustomerDTO;

/**
 * @author Guilherme Gaspar - 03/01/2018
 */
public interface CustomerService {
	 	
	List<CustomerDTO> getAllCustomers();

	CustomerDTO getCustomerById(String id);

	CustomerDTO createNewCustomer(CustomerDTO customerDTO);

	CustomerDTO saveCustomerByDTO(String id, CustomerDTO customerDTO);
	    
	void deleteCustomerById(String id);
}
