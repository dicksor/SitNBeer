package com.example.demo.repositories;

import java.util.Optional;

import com.example.demo.models.Bar;
import com.example.demo.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IBarRepository extends JpaRepository<Bar, Long>, JpaSpecificationExecutor<Bar>{
    Bar findByName(String name);
    Optional<Bar> findById(Integer id);
    Optional<Bar> findByUser(User user);
}