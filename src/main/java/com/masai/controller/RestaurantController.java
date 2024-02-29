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

import com.masai.exception.RestaurantException;
import com.masai.model.Restaurant;
import com.masai.service.RestaurantServiceImpl;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
	@Autowired
	private RestaurantServiceImpl serviceImpl;
	
	@PostMapping("/")
	public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant addRestaurant = this.serviceImpl.addRestaurant(restaurant);
		return addRestaurant;
		
	}
	
	@GetMapping("/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable Integer restaurantId) {
		Restaurant restaurant = this.serviceImpl.getRestaurant(restaurantId);
		return restaurant;
    	
    }
	@PutMapping("/{customerId}")
    public Restaurant updateRestaurantDetails(@PathVariable Integer customerId, @RequestBody Restaurant restaurant) {
		Restaurant updateRestaurantDetails = this.serviceImpl.updateRestaurantDetails(customerId, restaurant);
		return updateRestaurantDetails;
    	
    }
	@DeleteMapping("/{restaurantId}")
    public Restaurant deleteRestaurant(@PathVariable Integer restaurantId) {
		Restaurant deleteRestaurant = this.serviceImpl.deleteRestaurant(restaurantId);
		return deleteRestaurant;
    	
    	
    }
	
	@GetMapping("/{name}/{address}")
    public Restaurant restaurantByNameAndAddress(@PathVariable String name ,@PathVariable String address) {
		Restaurant restaurantByNameAndAddress = this.serviceImpl.restaurantByNameAndAddress(name, address);
    	return restaurantByNameAndAddress;
    	
    }
	
}
