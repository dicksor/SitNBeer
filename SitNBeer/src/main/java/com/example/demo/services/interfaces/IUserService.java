package com.example.demo.services.interfaces;

import com.example.demo.models.User;

public interface IUserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);
}
