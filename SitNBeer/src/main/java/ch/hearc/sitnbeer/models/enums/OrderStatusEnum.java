/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package ch.hearc.sitnbeer.models.enums;

public enum OrderStatusEnum {
    CLOSE, OPEN, IN_PROCESS, REJECTED;

    @Override
    public String toString() {
        return this.name();
    }
}