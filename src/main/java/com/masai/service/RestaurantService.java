package com.masai.service;

import com.masai.exception.RestaurantException;
import com.masai.model.Restaurant;

public interface RestaurantService {

    Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException;
    Restaurant getRestaurant(Integer restaurantId) throws RestaurantException;
    Restaurant updateRestaurantDetails(Integer customerId, Restaurant restaurant) throws RestaurantException;
    Restaurant deleteRestaurant(Integer restaurantId) throws RestaurantException;
    Restaurant restaurantByNameAndAddress(String name, String address) throws RestaurantException;
}
