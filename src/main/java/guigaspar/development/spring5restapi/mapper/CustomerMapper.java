package guigaspar.development.spring5restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import guigaspar.development.spring5restapi.domain.Customer;
import guigaspar.development.spring5restapi.model.CustomerDTO;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
