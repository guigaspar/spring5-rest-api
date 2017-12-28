package guigaspar.development.spring5restapi.initialization;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guigaspar.development.spring5restapi.domain.Address;
import guigaspar.development.spring5restapi.domain.Customer;
import guigaspar.development.spring5restapi.domain.Phone;
import guigaspar.development.spring5restapi.domain.PhoneType;
import guigaspar.development.spring5restapi.repositories.CustomerRepository;

@Component
public class AppInitialization implements CommandLineRunner{
	
	private CustomerRepository customerRepository;
	
	public AppInitialization(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		ArrayList<Phone> phones = new ArrayList<Phone>();
		
		Phone phone1 = new Phone();
		Phone phone2 = new Phone();
		
		phone1.setType(PhoneType.HOME);
		phone1.setNumber("55 11 33221199");
		
		phone2.setType(PhoneType.CEL);
		phone2.setNumber("55 11 94331 7711");
		
		phones.add(phone1);
		phones.add(phone2);
		//------------------------------------------------
		
		Address address1 = new Address();
		Address address2 = new Address();
		
		address1.setCity("São Paulo");
		address1.setPostalCode("02920120");
		address1.setState("SP");
		address1.setStreetAddress("Rua Silva Dutra, 96");
		
		address2.setCity("Rio de Janeiro");
		address2.setPostalCode("00112233");
		address2.setState("RJ");
		address2.setStreetAddress("Avenida Atlantica, 77");
		
		Customer cust1 = new Customer();
		cust1.setAge("25");
		cust1.setEmail("apitest@gmail.com");
		cust1.setFirstName("Guilherme");
		cust1.setLastName("Gaspar");
		cust1.setPhoneNumbers(phones);
		cust1.setAddress(address1);
		
		Customer cust2 = new Customer();
		cust2.setAge("25");
		cust2.setEmail("apitest@gmail.com");
		cust2.setFirstName("Guilherme");
		cust2.setLastName("Lucena");
		cust2.setPhoneNumbers(phones);
		cust2.setAddress(address2);
		
		customerRepository.save(cust1);
		customerRepository.save(cust2);	
		
	}
	
}
