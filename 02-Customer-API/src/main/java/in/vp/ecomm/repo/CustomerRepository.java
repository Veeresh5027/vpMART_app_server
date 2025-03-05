package in.vp.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vp.ecomm.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	 public Customer findByEmail(String email);
	
}
