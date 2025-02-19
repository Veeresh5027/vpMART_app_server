package in.vp.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vp.ecomm.entities.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {

}
