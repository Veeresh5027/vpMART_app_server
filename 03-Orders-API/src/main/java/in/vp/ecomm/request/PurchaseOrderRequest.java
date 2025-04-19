package in.vp.ecomm.request;

import java.util.List;

import in.vp.ecomm.dto.AddressDto;
import in.vp.ecomm.dto.CustomerDto;
import in.vp.ecomm.dto.OrderDto;
import in.vp.ecomm.dto.OrderItemDto;
import lombok.Data;

@Data
public class PurchaseOrderRequest {
	
	private CustomerDto customer;
	
	private AddressDto address;
	
	private OrderDto order;
	
	private List<OrderItemDto> orderItems;

}
