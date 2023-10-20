package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Dish;
import com.example.demo.entity.Restaurant;
import com.example.demo.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")

public class RestaurantController {
  
    @Autowired
    public static RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    
    
    
    @PostMapping("/addresto")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        try {
            Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
            return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    
    //adding dish to rest
    @PostMapping("/adddish")
    public ResponseEntity<Dish> addDishToRestaurant(
        @RequestBody Dish dish,
        @RequestParam Long restaurantId
    ) {
        try {
            Dish addedDish = restaurantService.addDishToRestaurant(dish, restaurantId);
            return new ResponseEntity<>(addedDish, HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/deleteDishById/{dishId}")
    public ResponseEntity<String> deleteDishById(@PathVariable Long dishId) {
        try {
            restaurantService.deleteDishById(dishId);
            return ResponseEntity.ok("Dish deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dish not found");
        }
    }



  




    
    @PutMapping("/dish/{dishId}/enable")
    public ResponseEntity<String> enableDish(@PathVariable Long dishId) {
        try {
            Dish enabledDish = restaurantService.enableDish(dishId);
            return ResponseEntity.ok("Dish enabled successfully");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dish not available");
        }
    }

    
    //disable dish 
    @PutMapping("/dish/{dishId}/disable")
    public ResponseEntity<String> disableDish(@PathVariable Long dishId) {
        try {
            Dish disabledDish = restaurantService.disableDish(dishId);
            return ResponseEntity.ok("Dish disabled successfully");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dish not available");
        }
    }

    
    //update dish 
    @PutMapping("/dish/{dishId}/modify")
    public ResponseEntity<?> modifyDish(@PathVariable Long dishId, @RequestBody Dish modifiedDish) {
        try {
            Dish updatedDish = restaurantService.modifyDish(dishId, modifiedDish);
            return new ResponseEntity<>(updatedDish, HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>("Dish not found", HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while modifying the dish", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    //get dish by id
    @GetMapping("/getdishbyid/{dishId}")
    public ResponseEntity<?> getDishById(@PathVariable Long dishId) {
        Dish dish = restaurantService.getDishById(dishId);
        if (dish != null) {
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Dish with ID " + dishId + " not found");
        }
    }



    
    
}

