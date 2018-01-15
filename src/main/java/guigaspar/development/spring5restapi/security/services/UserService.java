package guigaspar.development.spring5restapi.security.services;

import java.util.List;

import guigaspar.development.spring5restapi.domain.User;
import guigaspar.development.spring5restapi.model.UserDTO;

public interface UserService {

    User create(UserDTO object);

    User find(String id);

    User findByUsername(String userName);

    List<User> findAll();

    User update(String id, User object);

    String delete(String id);
}
