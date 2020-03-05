package com.example.demo.seeders;

import com.example.demo.models.Beer;
import com.example.demo.repositories.IBeerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerSeeder implements ISeeder{

    @Autowired
    private IBeerRepository beerRepository;

    public BeerSeeder(IBeerRepository beerRepository){
        this.beerRepository = beerRepository;
    }

    @Override
    public void seedDB() {
        if (beerRepository.findAll().isEmpty()) {
            Beer beer = new Beer();
            beer.setManufacturer("Boxer");
            beer.setName("La classique");
            beer.setPrice(5.5);
            beer.setStockQuantity(100);
            beer.setVolume(6.5);
            beerRepository.save(beer);
        }
    }

}