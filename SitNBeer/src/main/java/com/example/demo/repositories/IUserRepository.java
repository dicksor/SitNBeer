/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

 package com.example.demo.repositories;

import com.example.demo.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to interact with users, find user from his username or email
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);

    User findByEmail(String email);
}