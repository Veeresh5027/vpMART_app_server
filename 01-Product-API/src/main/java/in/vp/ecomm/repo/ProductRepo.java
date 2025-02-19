package in.vp.ecomm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vp.ecomm.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
	public List<Product> findByCategoryId(Long categoryId);
	
	public List<Product> findByNameContaining(String productName);

}
