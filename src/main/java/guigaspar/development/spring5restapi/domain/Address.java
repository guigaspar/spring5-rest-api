package guigaspar.development.spring5restapi.domain;

import lombok.Data;

@Data
public class Address {
	
	private String streetAddress;

    private String postalCode;

    private String state;

    private String city;

}
