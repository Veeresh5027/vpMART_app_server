package in.vp.ecomm.response;

import in.vp.ecomm.dto.CustomerDto;
import lombok.Data;

@Data
public class AuthResponse {
	
	private CustomerDto customer;
	
	private String token;

}
