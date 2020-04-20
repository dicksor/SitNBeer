/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */
 package ch.hearc.sitnbeer.services.interfaces;

public interface ISecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
