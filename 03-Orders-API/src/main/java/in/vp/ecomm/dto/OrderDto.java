package in.vp.ecomm.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderDto {
	
private Long orderId;
	
	private String orderTrackingNum;
	
	private String razorPayOrderId; //payment intitiated
	
	private String email;
	
	private String orderStatus;
	
	private double totalPrice;
	
	private int totalQuantity;
	
	private String razorPayPaymentId;//payment completed
	
	private String invoiceUrl;
	
	private LocalDate deliveryDate;

}
