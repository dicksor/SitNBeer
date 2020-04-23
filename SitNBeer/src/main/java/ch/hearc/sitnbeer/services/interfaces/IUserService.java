/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */
package ch.hearc.sitnbeer.services.interfaces;

import ch.hearc.sitnbeer.models.User;

public interface IUserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);
}
