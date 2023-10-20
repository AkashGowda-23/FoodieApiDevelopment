package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="dish")
public class Dish {
	
	
	
	
    @Id
    
    private Long id;
    private String name;
    private String category;
    private double price;
    private String description;
    private String image;
    private Set<String> availableDays;
    public Restaurant getRestaurant() {
        return restaurant;
    }
    private boolean enabled;

    @ManyToOne
   
    private Restaurant restaurant;
}
