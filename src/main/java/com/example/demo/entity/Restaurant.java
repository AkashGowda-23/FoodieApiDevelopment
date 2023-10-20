package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="resto")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Restaurant {
	
	 @Id
	    private Long id;
	    private String name;
	    private String location;
	    private String category;
	    private String  contact;
	    private Double rating;
	    private boolean approved;
	    
	    @OneToMany(targetEntity =Dish.class, cascade = CascadeType.ALL)
	    @JoinColumn(name="cp_fk",referencedColumnName = "id")
	    private List<Dish> dishes;
}
