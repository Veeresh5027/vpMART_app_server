package in.vp.ecomm.entities;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product")
@Getter
@Setter
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;

	private String title;
	
	private BigDecimal unitPrice;
	
	private String imageUrl;
	
	private boolean active;
	
	private int unitsInStock;
	
	private Date dateCreated;
	
	private Date lastUpdate;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private ProductCategory category;
}
