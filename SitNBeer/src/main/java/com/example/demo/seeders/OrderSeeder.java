package com.example.demo.seeders;

import com.example.demo.models.Order;
import com.example.demo.repositories.IOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSeeder implements ISeeder {

    @Autowired
    private IOrderRepository orderRepository;

    public OrderSeeder(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void seedDB() {
        if (orderRepository.findAll().isEmpty()) {
            Order order = new Order();
            order.setStatus(1);
            order.setTable(10);
            orderRepository.save(order);
        }
    }

}