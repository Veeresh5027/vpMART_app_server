package in.vp.ecomm.service;

import in.vp.ecomm.dto.CustomerDto;
import in.vp.ecomm.dto.ResetPwdDto;
import in.vp.ecomm.response.AuthResponse;

public interface CustomerService {
	
	public boolean isEmailUnique(String email);
	
	public boolean register(CustomerDto customerDto);
	
	public boolean resetPwd(ResetPwdDto resetPwdDto);
	
	public CustomerDto getCustomerByEmail(String email);
	
	public AuthResponse login(CustomerDto customerDto);
	
	public Boolean forgotPwd(String email);

}
