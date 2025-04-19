package in.vp.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vp.ecomm.entities.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
