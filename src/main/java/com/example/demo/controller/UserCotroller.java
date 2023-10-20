package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Order;
import com.example.demo.entity.Rating;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/User")

public class UserCotroller {
	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	private final RestaurantService restaurantService;

	public UserCotroller(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	// user register
	@PostMapping("/registeruser")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		userService.registerUser(user);
		return ResponseEntity.ok("User registered successfully.");
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order createdOrder = orderService.PlaceOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
	}

	// Search the Restaurant by name
	@GetMapping("/searchByName")
	public ResponseEntity<?> searchRestaurantsByName(@RequestParam String name) {
		try {
			List<Restaurant> restaurants = restaurantService.findRestaurantsByName(name);

			if (restaurants.isEmpty()) {
				return new ResponseEntity<>("No restaurants found with the name: " + name, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(restaurants, HttpStatus.OK);
		} catch (ResponseStatusException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// seach restaurant by location
	@GetMapping("/searchByLocation")
	public ResponseEntity<?> searchRestaurantsByLocation(@RequestParam String location) {
		try {
			List<Restaurant> restaurants = restaurantService.findRestaurantsByLocation(location);

			if (restaurants.isEmpty()) {
				return new ResponseEntity<>("No restaurants found for the location: " + location, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(restaurants, HttpStatus.OK);
		} catch (ResponseStatusException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Give a rating to a restaurant
	@PostMapping("/giveRating")
	public ResponseEntity<?> giveRating(@RequestBody Rating rating) {
		try {
			Rating createdRating = restaurantService.giveRating(rating);
			return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
		} catch (ResponseStatusException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}

	}
}
