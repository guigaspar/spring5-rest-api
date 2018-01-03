package guigaspar.development.spring5restapi.domain;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "customers")
public class Customer
{
	private String id;
    private String lastName;
    private ArrayList<Phone> phoneNumbers;
    private Address address;
    private String age;
    private String firstName;
    
    private String email;
    
}