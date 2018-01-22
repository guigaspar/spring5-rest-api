package guigaspar.development.spring5restapi.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import guigaspar.development.spring5restapi.model.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthorizationITTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    
    @Test
    public void testForbidden() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> entity = this.restTemplate.exchange("/api/v1/customers", HttpMethod.GET,
                new HttpEntity<Void>(headers), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
    

    @Test
    public void testAuthentication() throws JSONException {
    	HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = getToken();
        headers.set("x-auth-token", token);
        ResponseEntity<String> entity = this.restTemplate.exchange("/api/v1/customers",
                HttpMethod.GET, new HttpEntity<>("", headers), String.class);
        System.out.println(entity.getStatusCode());
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    @Test
    public void createUser() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        UserDTO userDto = new UserDTO();
        userDto.setUsername("testUserV2");
        userDto.setPassword("passTest");
        ResponseEntity<String> entity = this.restTemplate.exchange("/api/signup",
                HttpMethod.POST, new HttpEntity<>(userDto, headers), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
    
    public String getToken() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        UserDTO userDto = new UserDTO();
        userDto.setUsername("admin");
        userDto.setPassword("root");
        ResponseEntity<String> entity = this.restTemplate.exchange("/api/auth",
                HttpMethod.POST, new HttpEntity<>(userDto, headers), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().contains("token"));
         
        JSONObject jsonObj = new JSONObject(entity.getBody());
        String token = (String) jsonObj.get("token");
        System.out.println(token); 
        return token;
    }
    
}