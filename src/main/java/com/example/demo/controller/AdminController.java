package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.entity.Rating;
import com.example.demo.entity.Restaurant;
import com.example.demo.service.OrderService;
import com.example.demo.service.RatingService;
import com.example.demo.service.RestaurantService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
 @Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private OrderService orderService;
	
	
	//get all rating
	
	@GetMapping("/allRatings")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }
	
	//approve restarant 
	
	@PostMapping("/approveRestaurant/{restaurantId}")
    public ResponseEntity<String> approveRestaurant(@PathVariable Long restaurantId) {
        restaurantService.approveRestaurant(restaurantId);
        return ResponseEntity.ok("Restaurant approved successfully");
    }
	
	//reject restarant 
	
	 @PostMapping("/rejectRestaurant/{restaurantId}")
	    public ResponseEntity<String> rejectRestaurant(@PathVariable Long restaurantId) {
	        restaurantService.rejectRestaurant(restaurantId);
	        return ResponseEntity.ok("Restaurant rejected");
	    }
	 
	 //get all orders
	 
	 @GetMapping("/Getallorders")
	    public ResponseEntity<List<Order>> getAllOrders() {
	        List<Order> orders = orderService.getAllOrders();
	        return ResponseEntity.ok(orders);
	    }

	 
	 // Get all restaurants
	    @GetMapping("/allResto")
	    public List<Restaurant> getAllRestaurants() {
	        return restaurantService.getAllRestaurants();
	    }
}
