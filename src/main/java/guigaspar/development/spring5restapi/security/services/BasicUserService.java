package guigaspar.development.spring5restapi.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guigaspar.development.spring5restapi.domain.User;
import guigaspar.development.spring5restapi.exception.CustomException;
import guigaspar.development.spring5restapi.repositories.UserRepository;

@Service
public class BasicUserService implements UserService {

    private final UserRepository repository;

    @Autowired
    public BasicUserService(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(final User user) {
    	if(!checkIfUsernameExists(user.getUsername())) {
    		user.setCreatedAt(String.valueOf(LocalDateTime.now()));
            return repository.save(user);
    	}else{
    		throw new CustomException("Username already exists");
    	}
    }

    @Override
    public User find(final String id) {
    	Optional<User> userFind = repository.findById(id);
    	if(userFind.isPresent()) {
    		return userFind.get();
    	}
    	return null;
		
    }

    @Override
    public User findByUsername(final String userName) {
        return repository.findByUsername(userName);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User update(final String id, final User user) {
        user.setId(id);

        final Optional<User> saved = repository.findById(id);

        if(saved.isPresent()) {
        	user.setCreatedAt(saved.get().getCreatedAt());
        	user.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        }else {
        	user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        
        repository.save(user);
        return user;
    }

    @Override
    public String delete(final String id) {
        repository.deleteById(id);
        return id;
    }
    
    public boolean checkIfUsernameExists(String username) {
    	if(repository.findByUsername(username) != null) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
