package guigaspar.development.spring5restapi.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import guigaspar.development.spring5restapi.domain.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	User findByUsername(final String userName);

	Optional<User> findById(String id);
}
