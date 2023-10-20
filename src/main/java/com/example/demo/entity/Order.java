package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orderss")
public class Order {
    @Id
    private Long id;

    private String restaurantName;
    private String dishName;
    private int quantity;
  
@ManyToOne
@JoinColumn(name = "rating_id")
private Rating rating;

}