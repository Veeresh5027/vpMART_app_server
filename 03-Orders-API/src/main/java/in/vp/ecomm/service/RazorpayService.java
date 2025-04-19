package in.vp.ecomm.service;

import com.razorpay.Order;

public interface RazorpayService {
	
	public Order createPaymentorder(double amount);

}
