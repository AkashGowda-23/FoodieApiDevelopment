package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Restaurant;





public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	
	 List<Restaurant> findByName(String name);
	 
	    List<Restaurant> findByLocation(String location);
	    
	    List<Restaurant> findByApproved(boolean approved);
 
}

