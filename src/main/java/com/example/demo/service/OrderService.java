package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	
	
	@Autowired
  private  OrderRepository orderRepository;
	
	
	
	public Order PlaceOrder(Order order) {
	    return orderRepository.save(order);
	}
	
	public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
	
	


}
