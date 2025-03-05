package in.vp.ecomm.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.vp.ecomm.entities.Customer;
import in.vp.ecomm.repo.CustomerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer c = customerRepo.findByEmail(email);
		return new User(c.getEmail(), c.getPwd(), Collections.emptyList());
	}
}
