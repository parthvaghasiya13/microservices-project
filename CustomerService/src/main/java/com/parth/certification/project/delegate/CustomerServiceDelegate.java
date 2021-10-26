//package com.parth.certification.project.delegate;
//
//
//import org.springframework.beans.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//
//@Service
//public class CustomerServiceDelegate {
//
//	@Autowired
//	RestTemplate restTemplate;
//	
//	@HystrixCommand(fallbackMethod = "callProductServiceAndGetDate_Fallback")
//	public String callProductServiceAndGetDate(String productNumber) {
//		String resposne = restTemplate.exchange("http://localhost:5555/items/getProductDetailsForCustomer/{productnumber}", 
//				HttpMethod.GET,
//				null,
//				new ParameterizedTypeReference<String>() {
//			
//		}, productNumber).getBody();
//		
//		return "Normal Flow!!!" + productNumber;
//	}
//}
