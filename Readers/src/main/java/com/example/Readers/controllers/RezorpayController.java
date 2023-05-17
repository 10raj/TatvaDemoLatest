package com.example.Readers.controllers;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/rezorpay")
public class RezorpayController {
	
	@Value("${rzp_key_id}")
	private String rezKeyId;
	
	@Value("${rzp_key_secret}")
	private String rezKeySecret;
	
	@GetMapping("/payment/createOrderId/{amount}")
	@ResponseBody
	@Transactional
	public ResponseEntity<String> createPaymentOrderId(@PathVariable String amount) {
	    String orderId=null;
	    try {
	        RazorpayClient razorpay = new RazorpayClient(rezKeyId, rezKeySecret);
	        JSONObject orderRequest = new JSONObject();
	        orderRequest.put("amount", amount); 
	        orderRequest.put("currency", "INR");
	        orderRequest.put("receipt", "order_rcptid_11");

	        Order order = razorpay.orders.create(orderRequest);
	        orderId = order.get("id");
	        return ResponseEntity.ok(order.toString());
	    } catch (RazorpayException e) {
	      
	        System.out.println(e.getMessage());
	    }
	    return null;
	}
	
	@GetMapping("/payment/make_payment/{amount}")
	@ResponseBody
	public String createPayment(@Validated@PathVariable String amount) {
	    String orderId=null;
	    String paymentId="pay_LnX6mgiizkZo4c";
	    try {
	        RazorpayClient razorpay = new RazorpayClient(rezKeyId, rezKeySecret);
	        JSONObject paymentRequest = new JSONObject();
	        paymentRequest.put("amount", amount); 
	        paymentRequest.put("currency", "INR");

	        Payment payment = razorpay.payments.capture(paymentId, paymentRequest);
	        System.out.println(payment.toString());
	        return payment.toString();
	    } catch (RazorpayException e) {
	        System.out.println(e.getMessage());
	    }
	    return "failed";
	}

}
