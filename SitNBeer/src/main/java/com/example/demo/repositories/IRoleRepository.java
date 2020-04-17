/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package com.example.demo.repositories;

import com.example.demo.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to interact with roles, find role from string parameter
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}