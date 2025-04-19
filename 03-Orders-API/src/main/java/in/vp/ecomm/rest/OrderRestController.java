package in.vp.ecomm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.vp.ecomm.dto.OrderDto;
import in.vp.ecomm.dto.PaymentCallBackDto;
import in.vp.ecomm.request.PurchaseOrderRequest;
import in.vp.ecomm.response.ApiResponse;
import in.vp.ecomm.response.PurchaseOrderResponse;
import in.vp.ecomm.service.OrderService;


@RestController
@CrossOrigin
public class OrderRestController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> createOrder(@RequestBody PurchaseOrderRequest request){
		
		ApiResponse<PurchaseOrderResponse> response = new ApiResponse<>();
		
		PurchaseOrderResponse orderResp = orderService.createOrder(request);
		
		if (orderResp!=null) {
			response.setStatus(200);
			response.setMessage("Order Created");
			response.setData(orderResp);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Order Creation Failed");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/order")
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> updateOrder(@RequestBody PaymentCallBackDto	 request){
		
		ApiResponse<PurchaseOrderResponse> response = new ApiResponse<>();
		
		PurchaseOrderResponse orderResp = orderService.updateOrder(request);
		
		if (orderResp!=null) {
			response.setStatus(200);
			response.setMessage("Order updated");
			response.setData(orderResp);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Order updation Failed");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/orders/{email}")
	public ResponseEntity<ApiResponse<List<OrderDto>>> getMyOrders(@PathVariable("email") String email){
		
		ApiResponse<List<OrderDto>> response = new ApiResponse<>();
		
		List<OrderDto> ordersByEmail = orderService.getOrdersByEmail(email);
		
		if (!ordersByEmail.isEmpty()) {
			response.setStatus(200);
			response.setMessage("Fetched Orders");
			response.setData(ordersByEmail);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Failed to fetched orders");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
