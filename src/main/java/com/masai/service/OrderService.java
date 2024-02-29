package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.exception.RestaurantException;
import com.masai.model.Orders;
import com.masai.model.Status;

public interface OrderService {

    Orders addOrder(Orders order, Integer restaurantId, Integer customerId, Integer deliveryId) throws OrderException, CustomerException, RestaurantException;
    Orders getOrder(Integer orderId) throws OrderException;
    Orders updateOrder(Integer orderId, Status status) throws OrderException;
    Orders deleteOrder(Integer orderId) throws OrderException;
    Orders orderByCustomerId(Integer customerId) throws OrderException;
}
