package in.vp.ecomm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vp.ecomm.dto.OrderDto;
import in.vp.ecomm.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
	
	public 	Order findByRazorPayOrderId(String razorPayOrderId);
	public List<Order> findByEmail(String email);

}
