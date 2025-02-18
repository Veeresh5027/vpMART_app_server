package in.vp.ecomm.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {
	
private Long id;
	
	private String name;
	
	private String description;

	private String title;
	
	private BigDecimal unitPrice;
	
	private String imageUrl;
	
	private boolean active;
	
	private int unitInStock;
	
	private Date dateCreated;
	
	private Date lastUpdate;

}
