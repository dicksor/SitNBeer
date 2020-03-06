package com.example.demo.seeders;

import com.example.demo.models.User;
import com.example.demo.models.enums.RoleEnum;
import com.example.demo.repositories.IRoleRepository;
import com.example.demo.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements ISeeder {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    public UserSeeder(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedDB() {
        if (userRepository.findAll().isEmpty()) {
            User simpleUser = createSimpleUser();
            userRepository.save(simpleUser);

            User enterpriseUser = createEntepriseUser();
            userRepository.save(enterpriseUser);
        }
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

}