package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	Restaurant findByRestaurantNameAndAddress(String name , String address);
	
	// Custom query using JPQL
   // @Query("SELECT r FROM Restaurant r WHERE r.name = ?1 AND r.address = ?2")
   // List<Restaurant> findByNameAndAddress(String name, String address);

}
