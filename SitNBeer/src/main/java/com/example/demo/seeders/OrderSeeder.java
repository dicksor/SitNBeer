package com.example.demo.seeders;

import java.util.List;
import java.util.Random;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;
import com.example.demo.models.enums.OrderStatusEnum;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.models.User;
import com.example.demo.repositories.IOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSeeder implements ISeeder {

    @Autowired
    private IOrderRepository orderRepository;

    private List<User> fakeUsers;
    private List<Beer> fakeBeers;
    private List<Bar> fakeBars;

    public OrderSeeder(IOrderRepository orderRepository, List<User> fakeUsers, List<Beer> fakeBeers, List<Bar> fakeBars) {
        this.orderRepository = orderRepository;

        this.fakeUsers = fakeUsers;
        this.fakeBeers = fakeBeers;
        this.fakeBars = fakeBars;
    }

    @Override
    public void seedDB() {
        Random rand = new Random();

        if (orderRepository.findAll().isEmpty()) {
            Order order = new Order();
            order.setUser(fakeUsers.get(rand.nextInt(fakeUsers.size() - 1)));
            order.setBeer(fakeBeers.get(rand.nextInt(fakeBeers.size() - 1)));
            order.setStatus(OrderStatusEnum.IN_PROCESS);
            order.setTableNumber(10);
            orderRepository.save(order);
        }
    }

}