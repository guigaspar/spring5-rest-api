package guigaspar.development.spring5restapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import guigaspar.development.spring5restapi.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{

}
