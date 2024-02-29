package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.exception.RestaurantException;
import com.masai.model.Orders;
import com.masai.model.Status;
import com.masai.service.OrderServiceImpl;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	 @Autowired
	 private OrderServiceImpl serviceImpl;
	  
	 @PostMapping("/{restaurantId}/{customerId}/{deliveryId}")
	  public Orders addOrder(@RequestBody Orders order,@PathVariable Integer restaurantId,@PathVariable Integer customerId,@PathVariable Integer deliveryId) {
		 Orders addOrder = this.serviceImpl.addOrder(order, restaurantId, customerId, deliveryId);
		 return addOrder;
		 
		  
	  }
	 @GetMapping("/{orderId}")
	  public  Orders getOrder(@PathVariable Integer orderId) {
		 Orders order = this.serviceImpl.getOrder(orderId);
		 return order; 
		  
	  }
	 @PutMapping("/{orderId}/{status}")
	  public Orders updateOrder(@PathVariable Integer orderId,@PathVariable Status status) {
		  Orders updateOrder = this.serviceImpl.updateOrder(orderId, status);
		  return updateOrder;
	  }
	 
	 @DeleteMapping("/{orderId}")
	  public Orders deleteOrder(Integer orderId) {
		 Orders deleteOrder = this.serviceImpl.deleteOrder(orderId);
		 return deleteOrder;
		  
	  }
	 @GetMapping("/customer/{customerId}")
	  public Orders orderByCustomerId(Integer customerId) {
		  Orders orderByCustomerId = this.serviceImpl.orderByCustomerId(customerId);
		  return orderByCustomerId;
	  }
}
