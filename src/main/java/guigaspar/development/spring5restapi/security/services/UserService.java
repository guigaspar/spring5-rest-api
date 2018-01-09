package guigaspar.development.spring5restapi.security.services;

import java.util.List;

import guigaspar.development.spring5restapi.domain.User;

public interface UserService {

    User create(User object);

    User find(String id);

    User findByUsername(String userName);

    List<User> findAll();

    User update(String id, User object);

    String delete(String id);
}
