package guigaspar.development.spring5restapi.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import guigaspar.development.spring5restapi.domain.Customer;
import guigaspar.development.spring5restapi.model.CustomerDTO;


public class CustomerMapperTest {
	
	public static final String FIRSTNAME = "Guilherme";
    public static final String LASTNAME = "Lucena";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());

    }
}
