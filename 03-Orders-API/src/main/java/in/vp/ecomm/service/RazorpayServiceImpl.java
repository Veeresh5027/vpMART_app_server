package in.vp.ecomm.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import lombok.SneakyThrows;

@Service
public class RazorpayServiceImpl implements RazorpayService {
	
	@Value("${razorpay.key}")
	private String keyId;
	
	@Value("${razorpay.secret}")
	private String keySecret;
	
	private RazorpayClient razorpayClient;

	@Override
	@SneakyThrows
	public Order createPaymentorder(double amount) {
		
		this.razorpayClient = new RazorpayClient(keyId, keySecret);

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", amount*100);
		orderRequest.put("currency", "INR");
		orderRequest.put("payment_capture", 1);
		
		Order razorpayOrder = razorpayClient.orders.create(orderRequest);
		
		return razorpayOrder;
	}

}
