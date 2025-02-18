package in.vp.ecomm.mapper;

import org.modelmapper.ModelMapper;

import in.vp.ecomm.dto.ProductDto;
import in.vp.ecomm.entities.Product;

public class ProductMapper {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	public static ProductDto convertToDTO(Product entity) {
		
		return modelMapper.map(entity, ProductDto.class);
	}
	
	public static Product convertToEntity(ProductDto productDto) {
		
		return modelMapper.map(productDto, Product.class);
	}

}
