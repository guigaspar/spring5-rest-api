package guigaspar.development.spring5restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guigaspar.development.spring5restapi.converter.ConverterFacade;
import guigaspar.development.spring5restapi.domain.User;
import guigaspar.development.spring5restapi.model.UserDTO;
import guigaspar.development.spring5restapi.security.services.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    private final UserService service;

    private final ConverterFacade converterFacade;

    public SignUpController(final UserService service,
                            final ConverterFacade converterFacade) {
        this.service = service;
        this.converterFacade = converterFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create user")
    public User signUp(@RequestBody final UserDTO dto) {
        return service.create(converterFacade.convert(dto));
    }
    
}
