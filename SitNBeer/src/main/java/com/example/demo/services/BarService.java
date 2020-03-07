package com.example.demo.services;

import java.util.Collections;
import java.util.List;

import com.example.demo.models.Bar;
import com.example.demo.repositories.IBarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BarService implements IPagingService<Bar> {

    @Autowired
    private IBarRepository barRepository;

    private List<Bar> bars;

    @Override
    public Page<Bar> findPaginated(Pageable pageable) {
        this.bars = barRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Bar> list;

        if (bars.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, bars.size());
            list = bars.subList(startItem, toIndex);
        }

        Page<Bar> barPage = new PageImpl<Bar>(list, PageRequest.of(currentPage, pageSize), bars.size());

        return barPage;
    }
}