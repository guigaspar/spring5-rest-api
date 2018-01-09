package guigaspar.development.spring5restapi.security.services;

public interface TokenService {
	
    String getToken(String username, String password);

}
