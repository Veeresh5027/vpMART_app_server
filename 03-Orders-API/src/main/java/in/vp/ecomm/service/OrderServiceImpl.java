package in.vp.ecomm.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vp.ecomm.dto.AddressDto;
import in.vp.ecomm.dto.CustomerDto;
import in.vp.ecomm.dto.OrderDto;
import in.vp.ecomm.dto.OrderItemDto;
import in.vp.ecomm.dto.PaymentCallBackDto;
import in.vp.ecomm.entities.Address;
import in.vp.ecomm.entities.Customer;
import in.vp.ecomm.entities.Order;
import in.vp.ecomm.entities.OrderItem;
import in.vp.ecomm.repo.AddressRepo;
import in.vp.ecomm.repo.CustomerRepository;
import in.vp.ecomm.repo.OrderItemRepo;
import in.vp.ecomm.repo.OrderRepo;
import in.vp.ecomm.request.PurchaseOrderRequest;
import in.vp.ecomm.response.PurchaseOrderResponse;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private OrderItemRepo orderItemRepo;
	
	@Autowired
	private RazorpayService razorpayService;
	
	@Autowired
	private EmailService emailService;

	@Override
	public PurchaseOrderResponse createOrder(PurchaseOrderRequest orderRequest) {

		CustomerDto customerDto = orderRequest.getCustomer();
		AddressDto addressDto = orderRequest.getAddress();
		OrderDto orderDto = orderRequest.getOrder();
		List<OrderItemDto> orderItemsList = orderRequest.getOrderItems();
		
		//check customer presence in db save if req
		Customer c = custRepo.findByEmail(customerDto.getEmail());
		if(c == null) {
			//TODO: inter service communication
		c.setName(customerDto.getName());
		c.setEmail(customerDto.getEmail());
		c.setPhoneNo(customerDto.getPhoneNo());
		custRepo.save(c);
		}
		
		//save Address
		Address address = new Address();
		address.setHouseNum(addressDto.getHouseNum());
		address.setStreet(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setZipCode(addressDto.getZipCode());
		address.setCustomer(c); //Association mapping
		addressRepo.save(address);
		
		//save order
		Order newOrder = new Order();
		String orderTrackingNum = generateOrderTrackingNum();
		newOrder.setOrderTrackingNum(generateOrderTrackingNum());
		com.razorpay.Order paymentorder = razorpayService.createPaymentorder(orderDto.getTotalPrice());
		newOrder.setRazorPayOrderId(paymentorder.get("id"));
		newOrder.setOrderStatus(paymentorder.get("status"));
		newOrder.setTotalPrice(orderDto.getTotalPrice());
		newOrder.setTotalQuantity(orderDto.getTotalQuantity());
		newOrder.setEmail(c.getEmail());
		newOrder.setCustomer(c); //Association mapping
		newOrder.setAddress(address); //Association mapping
		
		orderRepo.save(newOrder);
		
		//save order items
		for(OrderItemDto itemDto : orderItemsList) {
			OrderItem item = new OrderItem();
			BeanUtils.copyProperties(itemDto, item);
			item.setId(null);
			item.setOrder(newOrder); //Association mapping
			orderItemRepo.save(item);
		}
		
		//Prepare and return response
		return PurchaseOrderResponse.builder()
									.razorpayOrderId(paymentorder.get("id"))
									.orderStatus(paymentorder.get("status"))
									.orderTrackingNumber(orderTrackingNum)
									.build();
	}

	@Override
	public PurchaseOrderResponse updateOrder(PaymentCallBackDto paymentCallBackDto) {

		Order order = orderRepo.findByRazorPayOrderId(paymentCallBackDto.getRazorpayOrderId());
		
		if(order!=null) {
			order.setOrderStatus("CONFIRMED");
			order.setDeliveryDate(LocalDate.now().plusDays(3));
			order.setRazorPayPaymentId(paymentCallBackDto.getRazorpayPaymentId());
			orderRepo.save(order);
			
			String subject = "Your order is confirmed";
			String body = "Thank you, You will recieve your order on" + order.getDeliveryDate();
			
			emailService.sendEmail(order.getEmail(), subject, body);
		}
		
		return PurchaseOrderResponse.builder()
				.razorpayOrderId(paymentCallBackDto.getRazorpayOrderId())
				.orderStatus(order.getOrderStatus())
				.orderTrackingNumber(order.getOrderTrackingNum())
				.build();
	}

	@Override
	public List<OrderDto> getOrdersByEmail(String email) {
		
		List<OrderDto> dtosList = new ArrayList<>();

		List<Order> ordersList = orderRepo.findByEmail(email);
		
		for(Order order : ordersList) {
			OrderDto dto = new OrderDto();
			BeanUtils.copyProperties(order, dto);
			dtosList.add(dto);
		}
		
		return dtosList;
	}
	
	private String generateOrderTrackingNum() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		
		String randomUuid = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
		
		//combine timestamp and randomUuid to form track number
		return "OD"+timestamp+randomUuid;
		
	}

}
