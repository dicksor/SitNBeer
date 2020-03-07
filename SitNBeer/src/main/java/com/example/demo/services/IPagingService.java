package com.example.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPagingService<T> {
    public Page<T> findPaginated(Pageable pageable);
}