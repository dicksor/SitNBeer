/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package ch.hearc.sitnbeer.repositories;

import ch.hearc.sitnbeer.models.Bar;
import ch.hearc.sitnbeer.models.Beer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Repository to interact with beers, find by name's beer and by bar's who own the beer
 */
@Repository
@RepositoryRestResource
public interface IBeerRepository extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer>{
    Beer findByName(String name);
    Iterable<Beer> findByBar(Bar bar);
}