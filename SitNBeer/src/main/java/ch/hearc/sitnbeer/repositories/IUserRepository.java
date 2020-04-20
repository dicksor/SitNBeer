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

import ch.hearc.sitnbeer.models.User;

/**
 * Repository to interact with users, find user from his username or email
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);

    User findByEmail(String email);
}