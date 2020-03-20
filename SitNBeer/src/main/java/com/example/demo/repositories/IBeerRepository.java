package com.example.demo.repositories;


import com.example.demo.models.Bar;
import com.example.demo.models.Beer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface IBeerRepository extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer>{
    Beer findByName(String name);
    Iterable<Beer> findByBar(Bar bar);
}