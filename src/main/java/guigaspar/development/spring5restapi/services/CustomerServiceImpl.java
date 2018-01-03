package guigaspar.development.spring5restapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guigaspar.development.spring5restapi.domain.Customer;
import guigaspar.development.spring5restapi.mapper.CustomerMapper;
import guigaspar.development.spring5restapi.model.CustomerDTO;
import guigaspar.development.spring5restapi.repositories.CustomerRepository;

/**
 * @author Guilherme Gaspar - 03/01/2018
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }
    
    @Override
    public List<CustomerDTO> getAllCustomers() {
    	return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                   CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                   return customerDTO;
                })
                .collect(Collectors.toList());
    }
    
	@Override
	public CustomerDTO getCustomerById(String id) {
		  return customerRepository.findById(id)
	                .map(customerMapper::customerToCustomerDTO)
	                .map(customerDTO -> {
	                    return customerDTO;
	                })
	                .orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
		return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
	}

	private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
        return returnDto;
    }

	@Override
	public CustomerDTO saveCustomerByDTO(String id, CustomerDTO customerDTO) {
		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		return saveAndReturnDTO(customer);
	}

	@Override
	public void deleteCustomerById(String id) {
		customerRepository.deleteById(id);
	}

}
