package guigaspar.development.spring5restapi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import guigaspar.development.spring5restapi.exception.CustomException;
import guigaspar.development.spring5restapi.exception.ResourceNotFoundException;
import guigaspar.development.spring5restapi.exception.ServiceException;

/**
 * @author Guilherme Gaspar - 03/01/2018
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("User Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<Object> handleServiceException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Authentication Error", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<Object> handleCustomException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
}
