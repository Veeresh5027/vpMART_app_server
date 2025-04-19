package in.vp.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vp.ecomm.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
