package in.vp.ecomm.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class CustomerDto {
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String pwd;
	
	private String pwdUpdated;
	
	private String phoneNo;
	
	private Date dateCreated;
	
	private Date lastUpdated;


}
