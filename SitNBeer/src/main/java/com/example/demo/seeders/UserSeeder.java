package com.example.demo.seeders;

import java.util.LinkedList;
import java.util.List;

import com.example.demo.models.User;
import com.example.demo.models.enums.RoleEnum;
import com.example.demo.repositories.IRoleRepository;
import com.example.demo.repositories.IUserRepository;
import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements ISeeder {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    private List<User> fakeUsers;

    public UserSeeder(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.fakeUsers = new LinkedList<User>();
    }

    @Override
    public void seedDB() {
        if (userRepository.findAll().isEmpty()) {
            User simpleUser = createSimpleUser();
            userRepository.save(simpleUser);

            User enterpriseUser = createEntepriseUser();
            userRepository.save(enterpriseUser);

            generateFakeUsers();
        }
    }

    public List<User> getFakeUsers(){
        return this.fakeUsers;
    }

    private User createSimpleUser() {
        User user = new User();
        user.setName("test");
        user.setPassword("test");
        user.setActive(1);
        user.setEmail("test@sitnbeer.com");
        user.setRole(roleRepository.findByRole(RoleEnum.USER.toString()));
        return user;
    }

    private User createEntepriseUser() {
        User user = new User();
        user.setName("enterprise");
        user.setPassword("test");
        user.setActive(1);
        user.setEmail("enterprise@sitnbeer.com");
        user.setRole(roleRepository.findByRole(RoleEnum.ENTERPRISE.toString()));
        return user;
    }

    private void generateFakeUsers() {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setPassword(faker.internet().password());
            user.setActive(1);
            user.setEmail(faker.internet().emailAddress());
            user.setRole(roleRepository.findByRole(RoleEnum.ENTERPRISE.toString()));
            fakeUsers.add(userRepository.save(user));
        }
    }

}