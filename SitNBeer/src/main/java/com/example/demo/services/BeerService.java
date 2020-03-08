package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Beer;
import com.example.demo.repositories.IBeerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BeerService implements IPagingService<Beer> {

    @Autowired
    private IBeerRepository beerRepository;

    @Override
    public Page<Beer> findPaginated(Pageable pageable) {
        List<Beer> beers = beerRepository.findAll();
        return paginate(pageable, beers);
    }

    @Override
    public Page<Beer> findPaginatedWithSpecs(Pageable pageable, Specification<Beer> specs) {
        List<Beer> beers = beerRepository.findAll(Specification.where(specs));
        return paginate(pageable, beers);
    }

}