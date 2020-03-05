package com.example.demo.seeders;

import com.example.demo.models.Bar;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BarSeeder implements ISeeder {

    @Autowired
    private IBarRepository barRepository;

    @Autowired
    private IUserRepository userRepository;

    public BarSeeder(IBarRepository barRepository, IUserRepository userRepository){
        this.barRepository = barRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void seedDB() {
        if (barRepository.findAll().isEmpty()) {
            Bar bar = new Bar();
            bar.setUser(userRepository.findByName("test"));
            bar.setName("B'art");
            bar.setAddress("Rue J.-L.-Pourtalès 5, 2000 Neuchâtel");
            bar.setAvailableTable(10);
            barRepository.save(bar);
        }
    }

}