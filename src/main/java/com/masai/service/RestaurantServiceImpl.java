package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.RestaurantException;
import com.masai.model.Restaurant;
import com.masai.repository.RestaurantRepository;

import io.swagger.v3.oas.models.security.SecurityScheme.In;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository repo;

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
		try {
			Restaurant save = this.repo.save(restaurant);
			return save;
			
		} catch (Exception e) {
			throw new RestaurantException("Error occured when add Restorent",e);
		}
	}

	@Override
	public Restaurant getRestaurant(Integer restaurantId) throws RestaurantException {
		try {
			Optional<Restaurant> findById = this.repo.findById(restaurantId);
			if(findById.isPresent()) {
				Restaurant restaurant = findById.get();
				return restaurant;
			}else {
				throw new RestaurantException("Restorent id is not present "+restaurantId);
			}
			
		} catch (Exception e) {
			throw new RestaurantException("Erroe occrud when get restornt",e);
		}
	}

	@Override
	public Restaurant updateRestaurantDetails(Integer customerId, Restaurant restaurant) throws RestaurantException {
	      try {
	    	  Optional<Restaurant> findById = this.repo.findById(customerId);
	    	  if(findById.isPresent()) {
	    		  Restaurant restaurant2 = findById.get();
	    		  restaurant2.setAddress(restaurant.getAddress());
	    		  restaurant2.setRestaurantName(restaurant.getRestaurantName());
	    		  Restaurant save = this.repo.save(restaurant2);
	    		  return save;
	    	  }else {
	    		  throw new RestaurantException("Restorent id is not present "+customerId);
	    	  }
			
		} catch (Exception e) {
			throw new RestaurantException("Error occurd when update Restorent deatials",e);
		}
	}

	@Override
	public Restaurant deleteRestaurant(Integer restaurantId) throws RestaurantException {
		try {
			Optional<Restaurant> findById = this.repo.findById(restaurantId);
			if(findById.isPresent()) {
				Restaurant restaurant = findById.get();
				this.repo.deleteById(restaurantId);
				return restaurant;
			}else {
				throw new RestaurantException("Resorent id is not presnt with "+restaurantId);
			}
			
		}catch (RestaurantException e) {
			throw e;
		}
		catch (Exception e) {
			throw new RestaurantException("Error occurd when delete restorent :"+e.getMessage());
		}
	}

	@Override
	public Restaurant restaurantByNameAndAddress(String name, String address) throws RestaurantException {
		try {
			Restaurant findByRestaurantNameAndAddress = this.repo.findByRestaurantNameAndAddress(name, address);
			return findByRestaurantNameAndAddress;
			
		} catch (Exception e) {
			throw new RestaurantException("Error finding restaurant:"+e.getMessage());
		}
		
	}

}
