package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.models.enums.OrderStatusEnum;
import com.example.demo.models.enums.RoleEnum;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.repositories.IRoleRepository;
import com.example.demo.repositories.IUserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private IBarRepository barRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBeerRepository beerRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Test
    public void testBarRepository(){

        // Role
        Role userRole = new Role();
        userRole.setRoleName(RoleEnum.USER.toString());
        roleRepository.save(userRole);

        Role enterpriseRole = new Role();
        enterpriseRole.setRoleName(RoleEnum.ENTERPRISE.toString());
        roleRepository.save(enterpriseRole);

        Role visitorRole = new Role();
        visitorRole.setRoleName(RoleEnum.VISITOR.toString());
        roleRepository.save(visitorRole);

        // User
        User user = new User();
        user.setUsername("UserTest");
        user.setEmail("test@test.com");
        user.setPassword("password1234");
        user.setPasswordConfirm("password1234");
        user.setRole(roleRepository.findByRole(RoleEnum.ENTERPRISE.toString()));

        entityManager.persist(user);
        entityManager.flush();

        Optional<User> userSearch = userRepository.findById(user.getId());
        assertTrue(userSearch.isPresent());

        User user2 = userSearch.get();
        assertTrue(user2.getUsername().equals(user.getUsername()));
        assertTrue(user2.getPassword().equals(user.getPassword()));
        assertTrue(user2.getEmail().equals(user.getEmail()));
        assertTrue(user2.getRole().equals(user.getRole()));

        // Bar
        Bar bar = new Bar();
        bar.setName("Bart");
        bar.setAddress("BartAdress");
        bar.setAvailableTable(20);
        bar.setUser(user);

        user.setOwnedBar(bar);

        entityManager.persist(bar);
        entityManager.flush();

        Optional<Bar> barSerach = barRepository.findById(bar.getId());
        assertTrue(barSerach.isPresent());

        Bar bar2 = barSerach.get();

        assertTrue(bar2.getName().equals(bar.getName()));
        assertTrue(bar2.getAddress().equals(bar.getAddress()));
        assertTrue(bar2.getAvailableTable() == bar.getAvailableTable());
        assertTrue(bar2.getUser().equals(bar.getUser()));

        assertTrue(user.getOwnedBar().equals(bar2));

        // Beer
        Beer beer = new Beer();
        beer.setName("BeerName");
        beer.setManufacturer("BeerManufacturer");
        beer.setPrice(10);
        beer.setStockQuantity(20);
        beer.setVolume(6);
        beer.setBar(bar);

        Set<Beer> setBeers = new HashSet<>();
        setBeers.add(beer);
        bar.setBeers(setBeers);

        entityManager.persist(beer);
        entityManager.flush();

        Optional<Beer> beerSearch = beerRepository.findById(beer.getId());

        assertTrue(beerSearch.isPresent());

        Beer beer2 = beerSearch.get();

        assertTrue(beer2.getName().equals(beer.getName()));
        assertTrue(beer2.getManufacturer().equals(beer.getManufacturer()));
        assertTrue(beer2.getPrice() == beer.getPrice());
        assertTrue(beer2.getStockQuantity() == beer.getStockQuantity());
        assertTrue(beer2.getVolume() == beer.getVolume());
        assertTrue(beer2.getBar().equals(beer.getBar()));

        assertTrue(bar.getBeers().contains(beer2));

        // Order
        Order order = new Order();
        order.setBar(bar);
        order.setBeer(beer);
        order.setUser(user);
        order.setTableNumber(1);
        order.setStatus(OrderStatusEnum.IN_PROCESS);

        List<Order> listOrders = new ArrayList<>();
        listOrders.add(order);
        bar.setOrders(listOrders);

        entityManager.persist(order);
        entityManager.flush();

        Optional<Order> orderSearch = orderRepository.findById(order.getId());

        assertTrue(orderSearch.isPresent());

        Order order2 = orderSearch.get();

        assertTrue(order2.getBar().equals(order.getBar()));
        assertTrue(order2.getBeer().equals(order.getBeer()));
        assertTrue(order2.getUser().equals(order.getUser()));
        assertTrue(order2.getTableNumber() == order.getTableNumber());
        assertTrue(order2.getStatus().equals(order.getStatus()));

        assertTrue(bar.getOrders().contains(order2));
    }
}