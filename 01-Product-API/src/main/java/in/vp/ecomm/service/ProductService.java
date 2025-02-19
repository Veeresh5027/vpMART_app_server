package in.vp.ecomm.service;

import java.util.List;

import in.vp.ecomm.dto.ProductCategoryDto;
import in.vp.ecomm.dto.ProductDto;

public interface ProductService {
	
	public List<ProductCategoryDto> findAllCategories();
	
	public List<ProductDto> findProductByCategoryId(Long categoryId);

	public ProductDto findByProductId(Long productId);
	
	public List<ProductDto> findByProductName(String productName);
}
