package com.example.demo.seeders;

import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.repositories.IRoleRepository;
import com.example.demo.repositories.IUserRepository;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements ISeeder {

    private IBarRepository barRepository;
    private IBeerRepository beerRepository;
    private IOrderRepository orderRepository;
    private IRoleRepository roleRepository;
    private IUserRepository userRepository;

    public Seeder(IBarRepository barRepository, IBeerRepository beerRepository, IOrderRepository orderRepository,
            IRoleRepository roleRepository, IUserRepository userRepository) {
        this.barRepository = barRepository;
        this.beerRepository = beerRepository;
        this.orderRepository = orderRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedDB();
    }

    @Override
    public void seedDB() {
        seedRoleTable();
        seedUserTable();
        /*
         * seedBeerTable(); seedBarTable(); seedOrderTable();
         */
    }

    private void seedRoleTable() {
        RoleSeeder roleSeeder = new RoleSeeder(roleRepository);
        roleSeeder.seedDB();
    }

    private void seedUserTable() {
        UserSeeder userSeeder = new UserSeeder(userRepository, roleRepository);
        userSeeder.seedDB();
    }

    private void seedBarTable() {
        BarSeeder barSeeder = new BarSeeder(barRepository);
        barSeeder.seedDB();
    }

    private void seedBeerTable() {
        BeerSeeder beerSeeder = new BeerSeeder(beerRepository);
        beerSeeder.seedDB();
    }

    private void seedOrderTable() {
        OrderSeeder orderSeeder = new OrderSeeder(orderRepository);
        orderSeeder.seedDB();
    }

}