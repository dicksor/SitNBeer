package com.example.demo.seeders;

import java.util.List;
import java.util.Random;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;
import com.example.demo.models.enums.OrderStatusEnum;
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

    private Random rand = new Random();

    public OrderSeeder(IOrderRepository orderRepository, List<User> fakeUsers, List<Beer> fakeBeers, List<Bar> fakeBars) {
        this.orderRepository = orderRepository;

        this.fakeUsers = fakeUsers;
        this.fakeBeers = fakeBeers;
    }

    @Override
    public void seedDB() {
        

        if (orderRepository.findAll().isEmpty()) {
            generateFakeOrder();
        }
    }

    private OrderStatusEnum getRandomOrderStatus(){
        Random rand = new Random();
        OrderStatusEnum orderStatusEnum = null;
        switch (rand.nextInt(OrderStatusEnum.values().length)) {
            case 0:
                orderStatusEnum =  OrderStatusEnum.OPEN;
                break;
            case 1:
                orderStatusEnum = OrderStatusEnum.IN_PROCESS;
                break;
            case 2: 
                orderStatusEnum = OrderStatusEnum.CLOSE;
                break;
            case 3:
                orderStatusEnum = OrderStatusEnum.REJECTED;
                break;
            default :
                orderStatusEnum = OrderStatusEnum.REJECTED;
                break;
        }
        return orderStatusEnum;
    }

    public void generateFakeOrder(){

        for(int i = 0; i< 15; i++){
            Order order = new Order();
            order.setUser(fakeUsers.get(rand.nextInt(fakeUsers.size() - 1)));

            Beer beer = fakeBeers.get(rand.nextInt(fakeBeers.size() - 1));
            order.setBeer(beer);

            Bar bar = beer.getBar();
            order.setBar(bar);
            order.setStatus(getRandomOrderStatus());
            order.setTableNumber(rand.nextInt(bar.getAvailableTable()));
            orderRepository.save(order);
        }
    }

}