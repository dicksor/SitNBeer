package com.example.demo.seeders;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.repositories.IBeerRepository;
import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerSeeder implements ISeeder {

    @Autowired
    private IBeerRepository beerRepository;

    private List<Bar> fakeBars;
    private List<Beer> fakeBeers;

    private SecureRandom rand = new SecureRandom();

    public BeerSeeder(IBeerRepository beerRepository, List<Bar> fakeBars) {
        this.beerRepository = beerRepository;
        this.fakeBars = fakeBars;

        this.fakeBeers = new LinkedList<>();
    }

    @Override
    public void seedDB() {
        if (beerRepository.findAll().isEmpty()) {
            generateFakeBeer();
        }
    }

    public List<Beer> getFakeBeers(){
        return this.fakeBeers;
    }

    private void generateFakeBeer() {
        Faker faker = new Faker();

        for (int i = 0; i < 50; i++) {
            Beer beer = new Beer();
            beer.setBar(fakeBars.get(rand.nextInt(fakeBars.size() - 1)));
            beer.setManufacturer(faker.company().name());
            beer.setName(faker.beer().name());
            beer.setPrice(faker.number().randomDouble(1, 2, 20));
            beer.setStockQuantity(faker.number().numberBetween(10, 200));
            beer.setVolume(faker.number().randomDouble(2, 0, 12));
            this.fakeBeers.add(beerRepository.save(beer));
        }
    }

}