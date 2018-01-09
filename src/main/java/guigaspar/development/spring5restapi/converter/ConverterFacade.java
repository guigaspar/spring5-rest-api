package guigaspar.development.spring5restapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import guigaspar.development.spring5restapi.domain.User;
import guigaspar.development.spring5restapi.model.UserDTO;


@Component
public class ConverterFacade {
	
	@Autowired
    private ConverterFactory converterFactory;

    public User convert(final UserDTO dto) {
        return (User) converterFactory.getConverter(dto.getClass()).convert(dto);
    }
}
