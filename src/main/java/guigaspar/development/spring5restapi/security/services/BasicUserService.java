package guigaspar.development.spring5restapi.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guigaspar.development.spring5restapi.domain.Authority;
import guigaspar.development.spring5restapi.domain.User;
import guigaspar.development.spring5restapi.exception.CustomException;
import guigaspar.development.spring5restapi.mapper.UserMapper;
import guigaspar.development.spring5restapi.model.UserDTO;
import guigaspar.development.spring5restapi.repositories.UserRepository;

@Service
public class BasicUserService implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Autowired
    public BasicUserService(final UserRepository repository, final UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public User create(final UserDTO userDto) {
    	User user = userMapper.UserDtoToUser(userDto);
    	if(!checkIfUsernameExists(user.getUsername())) {
    		user.setCreatedAt(String.valueOf(LocalDateTime.now()));
    		initUser(user);
            return repository.save(user);
    	}else{
    		throw new CustomException("Username already exists");
    	}
    }

	private void initUser(final User user) {
		user.setAccountNonExpired(false);
		user.setCredentialsNonExpired(false);
		user.setEnabled(true);
		List<Authority> authorities = new ArrayList<>();
		authorities.add(Authority.ROLE_USER);
		user.setAuthorities(authorities);
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
