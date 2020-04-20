/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */
package ch.hearc.sitnbeer.services;

import java.util.List;

import ch.hearc.sitnbeer.models.Beer;
import ch.hearc.sitnbeer.repositories.IBeerRepository;

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