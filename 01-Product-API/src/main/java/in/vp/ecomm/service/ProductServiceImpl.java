package in.vp.ecomm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vp.ecomm.dto.ProductCategoryDto;
import in.vp.ecomm.dto.ProductDto;
import in.vp.ecomm.mapper.ProductCategoryMapper;
import in.vp.ecomm.mapper.ProductMapper;
import in.vp.ecomm.mapper.ProductCategoryMapper;
import in.vp.ecomm.repo.ProductCategoryRepo;
import in.vp.ecomm.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductCategoryRepo categoryRepo;

	@Override
	public List<ProductCategoryDto> findAllCategories() {

		return categoryRepo.findAll()
					.stream()
					.map(ProductCategoryMapper::convertToDTO)
					.collect(Collectors.toList());
		
		
	}

	@Override
	public List<ProductDto> findProductByCategoryId(Long categoryId) {

		return productRepo.findAll()
				   .stream()
				   .map(ProductMapper::convertToDTO)
				   .collect(Collectors.toList());
		
	}

	@Override
	public ProductDto findByProductId(Long productId) {

		return productRepo.findById(productId)
					      .map(ProductMapper::convertToDTO)
					      .orElse(null);
	}

	@Override
	public List<ProductDto> findByProductName(String productName) {
		return productRepo.findByNameContaining(productName)
				   .stream()
				   .map(ProductMapper::convertToDTO)
				   .collect(Collectors.toList());
	}

}
