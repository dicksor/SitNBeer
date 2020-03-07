package com.example.demo.repositories;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBeerRepository extends JpaRepository<Beer, Long>{
    Beer findByName(String name);
    Iterable<Beer> findByBar(Bar bar);
}