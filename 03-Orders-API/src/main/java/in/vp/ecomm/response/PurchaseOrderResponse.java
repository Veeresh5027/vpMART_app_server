package in.vp.ecomm.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseOrderResponse {
	
	private String razorpayOrderId;
	private String orderStatus;
	private String orderTrackingNumber;

}
