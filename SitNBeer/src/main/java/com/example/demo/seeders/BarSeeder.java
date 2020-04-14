package com.example.demo.seeders;

import java.util.LinkedList;
import java.util.List;

import com.example.demo.models.Bar;
import com.example.demo.models.User;
import com.example.demo.repositories.IBarRepository;
import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BarSeeder implements ISeeder {

    @Autowired
    private IBarRepository barRepository;

    private List<User> fakeUsers;
    private List<Bar> fakeBars;

    private static final int NUMBER_OF_BARS = 20;

    public BarSeeder(IBarRepository barRepository, List<User> fakeUsers) {
        this.barRepository = barRepository;

        this.fakeUsers = fakeUsers;
        this.fakeBars = new LinkedList<>();
    }

    @Override
    public void seedDB() {
        if (barRepository.findAll().isEmpty()) {
            generateFakeBars();
        }
    }

    public List<Bar> getFakeBars() {
        return this.fakeBars;
    }

    private void generateFakeBars() {
        Faker faker = new Faker();

        for (int i = 0; i < NUMBER_OF_BARS; i++) {
            Bar bar = new Bar();
            bar.setUser(fakeUsers.get(i));
            bar.setName(faker.company().name());
            bar.setAddress(faker.address().fullAddress());
            bar.setAvailableTable(faker.number().numberBetween(5, 40));
            fakeBars.add(barRepository.save(bar));
        }
    }

}