/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package ch.hearc.sitnbeer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.sitnbeer.models.Role;

/**
 * Repository to interact with roles, find role from string parameter
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}