package in.vp.ecomm.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.vp.ecomm.Mapper.CustomerMapper;
import in.vp.ecomm.dto.CustomerDto;
import in.vp.ecomm.dto.ResetPwdDto;
import in.vp.ecomm.entities.Customer;
import in.vp.ecomm.repo.CustomerRepository;
import in.vp.ecomm.response.AuthResponse;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	Random rnd = new Random();

	@Override
	public boolean isEmailUnique(String email) {
		Customer c = customerRepo.findByEmail(email);
		return c==null;
	}

	@Override
	public boolean register(CustomerDto customerDto) {
		
		String orgPwd = getRandomPwd();
		String encodedPwd = pwdEncoder.encode(orgPwd);
		
		Customer entity = CustomerMapper.convertToEntity(customerDto);
		entity.setPwd(encodedPwd);
		entity.setPwdUpdated("NO");
		
		Customer savedEntity = customerRepo.save(entity);
		
		if(savedEntity.getId()!=null) {
			String subject = "Registration Success";
			String body = "Your Login password is :: " + orgPwd;
			return emailService.sendEmail(customerDto.getEmail(), subject, body);
		}
		return false;
	}

	@Override
	public boolean resetPwd(ResetPwdDto resetPwdDto) {
		Customer c = customerRepo.findByEmail(resetPwdDto.getEmail());
		
		if(c!=null) {
			String newPwd = resetPwdDto.getNewPwd();
			String encodedPwd = pwdEncoder.encode(newPwd);
			c.setPwd(encodedPwd);
			c.setPwdUpdated("YES");
			customerRepo.save(c);
			return true;
		}
		return false;
	}

	@Override
	public CustomerDto getCustomerByEmail(String email) {
		Customer c = customerRepo.findByEmail(email);
		
		if(c!=null) {
			return CustomerMapper.convertToDTO(c);
		}
		return null;
	}

	@Override
	public AuthResponse login(CustomerDto customerDto) {
		
		AuthResponse response = new AuthResponse();
		
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(customerDto.getEmail(), customerDto.getPwd());
		
		Authentication authenticate = authManager.authenticate(authToken);
		
		if (authenticate.isAuthenticated()) {
			Customer c = customerRepo.findByEmail(customerDto.getEmail());
			response.setCustomer(CustomerMapper.convertToDTO(c));
			response.setToken(""); //TODO
		} 
		
		return response;
	}
	
	@Override
	public Boolean forgotPwd(String email) {
		Customer c = customerRepo.findByEmail(email);
		if(c !=null) {
			String sub = "reset Pwd Request";
			String body = "temp body";
			emailService.sendEmail(email, sub, body);
			return true;
		}
		return false; 
	} 
	
	private String getRandomPwd() {

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		StringBuilder pwd = new StringBuilder();


		while (pwd.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			pwd.append(SALTCHARS.charAt(index));
		}

		return pwd.toString();
	}

}
