package com.example.demo.services.interfaces;

public interface ISecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
