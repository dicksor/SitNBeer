package com.example.demo.seeders;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements ISeeder {

    public Seeder() {
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedDB();
    }

    @Override
    public void seedDB() {
        UserSeeder userSeeder = new UserSeeder();
        userSeeder.seedDB();

        BarSeeder barSeeder = new BarSeeder();
        barSeeder.seedDB();

        BeerSeeder beerSeeder = new BeerSeeder();
        beerSeeder.seedDB();

        OrderSeeder orderSeeder = new OrderSeeder();
        orderSeeder.seedDB();
    }

}