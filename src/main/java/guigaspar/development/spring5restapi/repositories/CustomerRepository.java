package guigaspar.development.spring5restapi.repositories;

import org.springframework.data.repository.CrudRepository;

import guigaspar.development.spring5restapi.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{

}
