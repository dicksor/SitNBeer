/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package ch.hearc.sitnbeer.models.enums;

public enum RoleEnum {
    ENTERPRISE, USER, VISITOR;

    @Override
    public String toString() {
        return this.name();
    }
}