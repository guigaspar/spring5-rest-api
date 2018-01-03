package guigaspar.development.spring5restapi.model;

import java.util.ArrayList;

import guigaspar.development.spring5restapi.domain.Address;
import guigaspar.development.spring5restapi.domain.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private String id;
    private String lastName;
    private ArrayList<Phone> phoneNumbers;
    private Address address;
    private String age;
    private String firstName;
    
    private String email;
    
}
