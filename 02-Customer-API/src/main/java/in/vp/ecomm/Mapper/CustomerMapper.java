package in.vp.ecomm.Mapper;

import org.modelmapper.ModelMapper;

import in.vp.ecomm.dto.CustomerDto;
import in.vp.ecomm.entities.Customer;

public class CustomerMapper {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	public static CustomerDto convertToDTO(Customer entity) {
		return modelMapper.map(entity, CustomerDto.class);
	}
	
	public static Customer convertToEntity(CustomerDto customerDto) {
		return modelMapper.map(customerDto, Customer.class);
	}

}
