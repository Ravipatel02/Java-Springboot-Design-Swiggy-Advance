package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.DeliveryException;
import com.masai.exception.OrderException;
import com.masai.exception.RestaurantException;
import com.masai.model.Customer;
import com.masai.model.DeliveryPartner;
import com.masai.model.Orders;
import com.masai.model.Restaurant;
import com.masai.model.Status;
import com.masai.repository.CustomerRepository;
import com.masai.repository.DeliveryRepository;
import com.masai.repository.OrderRepository;
import com.masai.repository.RestaurantRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private RestaurantRepository retoRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private DeliveryRepository deliRepo;
	
	

	@Override
	public Orders addOrder(Orders order, Integer restaurantId, Integer customerId, Integer deliveryId)
			throws OrderException, CustomerException, RestaurantException,DeliveryException {
		try {
			Optional<Customer> findById = this.custRepo.findById(customerId);
			if(!findById.isPresent()) {
				throw new CustomerException("Customer is not prsent with "+customerId);
			}
			Customer customer = findById.get();
			Optional<Restaurant> findById2 = this.retoRepo.findById(restaurantId);
			if(!findById2.isPresent()) {
				throw new RestaurantException("Restorent is not present with "+restaurantId);
			}
			Restaurant restaurant = findById2.get();
			Optional<DeliveryPartner> findById3 = this.deliRepo.findById(deliveryId);
			if(! findById3.isPresent()) {
				throw new DeliveryException("Delivery partner not found with ID "+deliveryId);
			}
			DeliveryPartner deliveryPartner = findById3.get();
			
			order.setCustomer(customer);
			order.setDeliveryPartner(deliveryPartner);
			order.setRestaurant(restaurant);
			
			Orders save = this.repo.save(order);
			
			return save;
			
			
			
		} catch (RestaurantException | CustomerException | DeliveryException e) {
			throw e;
			
		}catch (Exception e) {
			throw new OrderException("Error adding order: "+e.getMessage());
		}
	}

	@Override
	public Orders getOrder(Integer orderId) throws OrderException {
		try {
			Optional<Orders> findById = this.repo.findById(orderId);
			if(!findById.isPresent()) {
				throw new OrderException("Order is is not present with "+orderId);
			}
			Orders orders = findById.get();
			return orders;
			
		} catch (OrderException e) {
			throw e;
		}catch (Exception e) {
			throw new OrderException("Error Occured "+e.getMessage());
		}
	}

	@Override
	public Orders updateOrder(Integer orderId, Status status) throws OrderException {
		try {
			Optional<Orders> findById = this.repo.findById(orderId);
			if(!findById.isPresent()) {
				throw new OrderException("Order is not Present with "+orderId);
			}
			Orders orders = findById.get();
			orders.setStatus(status);
			Orders save = this.repo.save(orders);
			return save;	
			
		} catch (OrderException e) {
			throw e;
		}catch (Exception e) {
			throw new OrderException("Error occurd when update order"+e.getMessage());
		}
	}

	@Override
	public Orders deleteOrder(Integer orderId) throws OrderException {
		try {
			Optional<Orders> findById = this.repo.findById(orderId);
			if(!findById.isPresent()) {
				throw new OrderException("Oreder id is not presnt with "+ orderId);
			}
			Orders orders = findById.get();
			this.repo.deleteById(orderId);
			return orders;
			
			
		} catch (OrderException e) {
			throw e;
		}catch (Exception e) {
			throw new OrderException("Error occured when order delete "+e.getMessage());
		}
	}

	@Override
	public Orders orderByCustomerId(Integer customerId) throws OrderException {
		try {
			Optional<Customer> findById = this.custRepo.findById(customerId);
			if(!findById.isPresent()) {
				throw new OrderException("customer is not present with "+customerId);
			}
			Customer customer = findById.get();
			
			List<Orders> orders = customer.getOrders();
			if(orders.isEmpty()) {
				throw new OrderException("This customer is no order "+customerId);
			}
			return orders.get(orders.size()-1);
			
		}catch (OrderException e) {
			throw e;
		}
		catch (Exception e) {
			throw new OrderException("Error occrued "+e.getMessage());
		}
	}

}
