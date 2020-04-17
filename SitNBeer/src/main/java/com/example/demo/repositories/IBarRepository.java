/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package com.example.demo.repositories;

import java.util.Optional;

import com.example.demo.models.Bar;
import com.example.demo.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository to interact with bars, find by name's bar, id's bar or owner's bar who is a user
 */
@Repository
public interface IBarRepository extends JpaRepository<Bar, Long>, JpaSpecificationExecutor<Bar>{
    Bar findByName(String name);
    Optional<Bar> findById(Integer id);
    Optional<Bar> findByUser(User user);
}