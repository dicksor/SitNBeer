/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */
package ch.hearc.sitnbeer.seeders;

import java.security.SecureRandom;
import java.util.List;

import ch.hearc.sitnbeer.models.Bar;
import ch.hearc.sitnbeer.models.Beer;
import ch.hearc.sitnbeer.models.Order;
import ch.hearc.sitnbeer.models.enums.OrderStatusEnum;
import ch.hearc.sitnbeer.models.User;
import ch.hearc.sitnbeer.repositories.IOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSeeder implements ISeeder {

    @Autowired
    private IOrderRepository orderRepository;

    private List<User> fakeUsers;
    private List<Beer> fakeBeers;

    private SecureRandom rand = new SecureRandom();

    public OrderSeeder(IOrderRepository orderRepository, List<User> fakeUsers, List<Beer> fakeBeers) {
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

    /**
     * Seeds the database with fake orders
     */
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