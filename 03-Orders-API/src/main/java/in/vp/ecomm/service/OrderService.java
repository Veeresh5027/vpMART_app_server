package in.vp.ecomm.service;

import java.util.List;

import in.vp.ecomm.dto.OrderDto;
import in.vp.ecomm.dto.PaymentCallBackDto;
import in.vp.ecomm.request.PurchaseOrderRequest;
import in.vp.ecomm.response.PurchaseOrderResponse;

public interface OrderService {
	
	public PurchaseOrderResponse createOrder(PurchaseOrderRequest orderRequest);
	
	public PurchaseOrderResponse updateOrder(PaymentCallBackDto paymentCallBackDto);
	
	public List<OrderDto> getOrdersByEmail(String email);

}
