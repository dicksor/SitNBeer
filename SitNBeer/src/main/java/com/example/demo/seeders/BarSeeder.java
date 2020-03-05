package com.example.demo.seeders;

import com.example.demo.models.Bar;
import com.example.demo.repositories.IBarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BarSeeder implements ISeeder {

    @Autowired
    private IBarRepository barRepository;

    public BarSeeder(IBarRepository barRepository){
        this.barRepository = barRepository;
    }

    @Override
    public void seedDB() {
        if (barRepository.findAll().isEmpty()) {
            Bar bar = new Bar();
            bar.setName("B'art");
            bar.setAddress("Jardin Anglais");
            bar.setAvailableTable(10);
            barRepository.save(bar);
        }
    }

}