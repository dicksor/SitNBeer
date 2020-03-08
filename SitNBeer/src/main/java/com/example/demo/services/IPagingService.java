package com.example.demo.services;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IPagingService<T> {
    public Page<T> findPaginated(Pageable pageable);
    public Page<T> findPaginatedWithSpecs(Pageable pageable, Specification<T> specs);

    default Page<T> paginate(Pageable pageable, List<T> items) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<T> list;

        if (items.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, items.size());
            list = items.subList(startItem, toIndex);
        }

        Page<T> page = new PageImpl<T>(list, PageRequest.of(currentPage, pageSize), items.size());

        return page;
    }
}