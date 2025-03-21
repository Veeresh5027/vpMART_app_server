package in.vp.ecomm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.vp.ecomm.dto.CustomerDto;
import in.vp.ecomm.dto.ResetPwdDto;
import in.vp.ecomm.response.ApiResponse;
import in.vp.ecomm.response.AuthResponse;
import in.vp.ecomm.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody CustomerDto customerDto){
		
		ApiResponse<String> response = new ApiResponse<>();
		
		boolean emailUnique = customerService.isEmailUnique(customerDto.getEmail());
		if(!emailUnique) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Duplicate Email");
			return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.BAD_REQUEST);
		}
		
		boolean register = customerService.register(customerDto);
		if(register) {
			response.setStatus(201);
			response.setMessage("Success");
			response.setData("Registration Success");
			return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.CREATED);
		} else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("Registration failed");
			return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody CustomerDto customerDto){
		ApiResponse<AuthResponse> response = new ApiResponse<>();
		
		AuthResponse authResp = customerService.login(customerDto);
		
		if(authResp!=null){
			response.setStatus(200);
			response.setMessage("Login Success");
			response.setData(authResp);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Invalid Credentials");
			response.setData(null);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/resetPwd")
	public ResponseEntity<ApiResponse<String>> resetPwd(@RequestBody ResetPwdDto resetPwdDto){
		ApiResponse<String> response = new ApiResponse<>();
		CustomerDto customer = customerService.getCustomerByEmail(resetPwdDto.getEmail());
		if(!pwdEncoder.matches(resetPwdDto.getOldPwd(),customer.getPwd())) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Old Password is incorrect");
			return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.BAD_REQUEST);
		}
		boolean status = customerService.resetPwd(resetPwdDto);
		if(status) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Password Updated");
			return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Password reset failed");
			return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/forgot-pwd/{email}")
	public ResponseEntity<ApiResponse<String>> forgotPwd(@PathVariable("email") String email){
		ApiResponse<String> response = new ApiResponse<>();
		Boolean status = customerService.forgotPwd(email);
		if(status) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Email sent to ResetPwd");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("No Account Found with this Email..");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
 