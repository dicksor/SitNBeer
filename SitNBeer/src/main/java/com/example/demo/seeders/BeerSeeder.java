package com.example.demo.seeders;

import com.example.demo.models.Beer;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerSeeder implements ISeeder {

    @Autowired
    private IBeerRepository beerRepository;

    @Autowired
    private IBarRepository barRepository;

    public BeerSeeder(IBeerRepository beerRepository, IBarRepository barRepository) {
        this.beerRepository = beerRepository;
        this.barRepository = barRepository;
    }

    @Override
    public void seedDB() {
        if (beerRepository.findAll().isEmpty()) {
            Beer beer = new Beer();
            beer.setBar(barRepository.findByName("B'art"));
            beer.setManufacturer("Happy people");
            beer.setName("Porn star");
            beer.setPrice(3.5);
            beer.setStockQuantity(100);
            beer.setVolume(6.1);
            beerRepository.save(beer);
        }
    }

}