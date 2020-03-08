package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Bar;
import com.example.demo.repositories.IBarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BarService implements IPagingService<Bar> {

    @Autowired
    private IBarRepository barRepository;

    @Override
    public Page<Bar> findPaginated(Pageable pageable) {
        List<Bar> bars = barRepository.findAll();

        return paginate(pageable, bars);
    }

    @Override
    public Page<Bar> findPaginatedWithSpecs(Pageable pageable, Specification<Bar> specs) {
        List<Bar> bars = barRepository.findAll(Specification.where(specs));

        return paginate(pageable, bars);
    }
}