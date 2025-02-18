package in.vp.ecomm.mapper;

import org.modelmapper.ModelMapper;

import in.vp.ecomm.dto.ProductCategoryDto;
import in.vp.ecomm.entities.ProductCategory;

public class ProductCategoryMapper {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	public static ProductCategoryDto convertToDTO(ProductCategory entity) {
		
		return modelMapper.map(entity, ProductCategoryDto.class);
	}
	
	public static ProductCategory convertToEntity(ProductCategoryDto categoryDto) {
		
		return modelMapper.map(categoryDto, ProductCategory.class);
	}

}
