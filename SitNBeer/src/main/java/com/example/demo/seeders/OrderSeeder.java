package com.example.demo.seeders;

import com.example.demo.models.Order;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSeeder implements ISeeder {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBeerRepository beerRepository;

    @Autowired
    private IBarRepository barRepository;

    public OrderSeeder(IOrderRepository orderRepository, IUserRepository userRepository, IBeerRepository beerRepository,
            IBarRepository barRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.beerRepository = beerRepository;
        this.barRepository = barRepository;
    }

    @Override
    public void seedDB() {
        if (orderRepository.findAll().isEmpty()) {
            Order order = new Order();
            order.setBar(barRepository.findByName("B'art"));
            order.setBeer(beerRepository.findByName("Porn star"));
            order.setUser(userRepository.findByName("test"));
            order.setStatus(1);
            order.setTable(10);
            orderRepository.save(order);
        }
    }

}