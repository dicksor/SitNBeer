package com.example.demo.seeders;

import com.example.demo.models.Role;
import com.example.demo.models.enums.RoleEnum;
import com.example.demo.repositories.IRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements ISeeder {

    @Autowired
    private IRoleRepository roleRepository;

    public RoleSeeder(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedDB() {
        if (roleRepository.findAll().isEmpty()) {
            Role userRole = new Role();
            userRole.setRole(RoleEnum.USER.toString());
            roleRepository.save(userRole);

            Role enterpriseRole = new Role();
            enterpriseRole.setRole(RoleEnum.ENTERPRISE.toString());
            roleRepository.save(enterpriseRole);

            Role visitorRole = new Role();
            visitorRole.setRole(RoleEnum.VISITOR.toString());
            roleRepository.save(visitorRole);
        }
    }

}