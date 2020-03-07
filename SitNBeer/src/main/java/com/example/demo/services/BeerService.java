package com.example.demo.services;

import java.util.Collections;
import java.util.List;

import com.example.demo.models.Beer;
import com.example.demo.repositories.IBeerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BeerService implements IPagingService<Beer> {

    @Autowired
    private IBeerRepository beerRepository;

    private List<Beer> beers;

    @Override
    public Page<Beer> findPaginated(Pageable pageable) {
        this.beers = beerRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Beer> list;

        if (beers.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, beers.size());
            list = beers.subList(startItem, toIndex);
        }

        Page<Beer> beerPage = new PageImpl<Beer>(list, PageRequest.of(currentPage, pageSize), beers.size());

        return beerPage;
    }
}