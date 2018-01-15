package guigaspar.development.spring5restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import guigaspar.development.spring5restapi.domain.User;
import guigaspar.development.spring5restapi.model.UserDTO;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDTO userToUserDTO(User user);

    User UserDtoToUser(UserDTO userDTO);
}
