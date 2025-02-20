package in.vp.ecomm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.vp.ecomm.dto.ProductCategoryDto;
import in.vp.ecomm.dto.ProductDto;
import in.vp.ecomm.response.ApiResponse;
import in.vp.ecomm.service.ProductService;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/categories")
	public ResponseEntity<ApiResponse<List<ProductCategoryDto>>> productcategories(){
		
		List<ProductCategoryDto> allCategories = productService.findAllCategories();	
		
		ApiResponse<List<ProductCategoryDto>> response = new ApiResponse<>();
		
		if(!allCategories.isEmpty()){
			response.setStatus(200);
			response.setMessage("Fetched Categories Successfully");
			response.setData(allCategories); //payload
			
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}else {
			response.setStatus(500);
			response.setMessage("Failed to Fetch Categories");
			response.setData(null); //payload
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/products/{categoryId}")
	public ResponseEntity<ApiResponse<List<ProductDto>>> products(@PathVariable("categoryId") Long categoryId){
		
		ApiResponse<List<ProductDto>> response = new ApiResponse<>();
		
		List<ProductDto> products = productService.findProductByCategoryId(categoryId);
		
		if(!products.isEmpty()) {
			response.setStatus(200);
			response.setMessage("Fetched Products Successfully");
			response.setData(products); //payload
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			response.setStatus(500);
			response.setMessage("Failed to Fetch Products");
			response.setData(null); //payload
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@GetMapping("/productsByName/{name}")
//	public ResponseEntity<ApiResponse<List<ProductDto>>> products(@PathVariable("name") String name){
//		
//		ApiResponse<List<ProductDto>> response = new ApiResponse<>();
//		
//		List<ProductDto> products = productService.findByProductName(name);
//		
//		if(!products.isEmpty()) {
//			response.setStatus(200);
//			response.setMessage("Fetched Products Successfully");
//			response.setData(products); //payload
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		}else {
//			response.setStatus(500);
//			response.setMessage("Failed to Fetch Products");
//			response.setData(null); //payload
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@GetMapping("/productsByName/{name}")
	public ResponseEntity<ApiResponse<List<ProductDto>>> products(@PathVariable("name") String name) { 
	    
	    ApiResponse<List<ProductDto>> response = new ApiResponse<>();
	    List<ProductDto> products = productService.findByProductName(name);
	    
	    if (!products.isEmpty()) {
	        response.setStatus(200);
	        response.setMessage("Fetched Products Successfully");
	        response.setData(products);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        response.setStatus(404);  // Not Found
	        response.setMessage("No products found with the given name");
	        response.setData(null);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}

	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse<ProductDto>> product(@PathVariable("productId") Long productId){
		
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		ProductDto product = productService.findByProductId(productId);
		
		if(product!=null) {
			response.setStatus(200);
			response.setMessage("Fetched Product Successfully");
			response.setData(product); //payload
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			response.setStatus(500);
			response.setMessage("Failed to Fetch Product");
			response.setData(null); //payload
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
