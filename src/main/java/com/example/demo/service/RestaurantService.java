package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Dish;
import com.example.demo.entity.Order;
import com.example.demo.entity.Rating;
import com.example.demo.entity.Restaurant;
import com.example.demo.repository.DishRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RatingRepository;
import com.example.demo.repository.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private DishRepository dishRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RatingRepository ratingRepository;

	// adding Restaurant
	public Restaurant createRestaurant(Restaurant restaurant) {
		List<Restaurant> existingRestaurants = restaurantRepository.findByName(restaurant.getName());

		if (!existingRestaurants.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant already exists");
		}

		return restaurantRepository.save(restaurant);
	}

	// Get all restaurants
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	public Dish addDishToRestaurant(Dish dish, Long restaurantId) {
		Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

		if (restaurantOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
		}

		Restaurant restaurant = restaurantOptional.get();
		dish.setRestaurant(restaurant);
		return dishRepository.save(dish);
	}

	// removing dish
	public void deleteDishById(Long dishId) {
	    Optional<Dish> existingDish = dishRepository.findById(dishId);

	    if (existingDish.isPresent()) {
	        dishRepository.deleteById(dishId);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found");
	    }
	}






	// enable dish
	public Dish enableDish(Long dishId) {
		Dish dish = getDishById(dishId);

		if (!dish.isEnabled()) {
			dish.setEnabled(true);
			return dishRepository.save(dish);
		}
		return dish;
	}

	// disable dish
	public Dish disableDish(Long dishId) {
		Dish dish = getDishById(dishId);
		// Check if the dish is enabled
		if (dish.isEnabled()) {
			dish.setEnabled(false);
			return dishRepository.save(dish);
		}
		return dish;
	}

	// modify dish
	public Dish modifyDish(Long dishId, Dish modifiedDish) {
		Dish dish = getDishById(dishId);
		dish.setName(modifiedDish.getName());
		dish.setCategory(modifiedDish.getCategory());
		dish.setPrice(modifiedDish.getPrice());
		dish.setDescription(modifiedDish.getDescription());
		dish.setImage(modifiedDish.getImage());
		dish.setAvailableDays(modifiedDish.getAvailableDays());
		return dishRepository.save(dish);
	}

	// getdishbyid

	public Dish getDishById(Long dishId) {
	    Optional<Dish> dishOptional = dishRepository.findById(dishId);
	    return dishOptional.orElse(null);
	}


	// find resto by name
	public List<Restaurant> findRestaurantsByName(String name) {
		return restaurantRepository.findByName(name);
	}

	// find resto by location
	public List<Restaurant> findRestaurantsByLocation(String location) {
		return restaurantRepository.findByLocation(location);
	}

	// order

	public Order createOrder(String restaurantName, String dishName, int quantity) {

		List<Restaurant> existingRestaurants = restaurantRepository.findByName(restaurantName);

		if (existingRestaurants.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant does not exist");
		}

		// Create the order
		Order order = new Order();
		order.setRestaurantName(restaurantName);
		order.setDishName(dishName);
		order.setQuantity(quantity);

		return orderRepository.save(order);
	}

	public Rating giveRating(Rating rating) {
		Long restaurantId = rating.getRestaurant().getId();
		Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurantId);
		if (existingRestaurant.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Restaurant with ID " + restaurantId + " not found");
		}

		return ratingRepository.save(rating);
	}

	public void approveRestaurant(Long restaurantId) {
		Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
		restaurantOptional.ifPresent(restaurant -> {
			restaurant.setApproved(true);
			restaurantRepository.save(restaurant);
		});
	}

	// Method to reject a restaurant
	public void rejectRestaurant(Long restaurantId) {
		Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
		restaurantOptional.ifPresent(restaurant -> {
			restaurant.setApproved(false);
			restaurantRepository.save(restaurant);
		});
	}

}
