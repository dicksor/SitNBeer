/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package ch.hearc.sitnbeer.seeders;

import java.util.LinkedList;
import java.util.List;

import ch.hearc.sitnbeer.models.User;
import ch.hearc.sitnbeer.models.enums.RoleEnum;
import ch.hearc.sitnbeer.repositories.IRoleRepository;
import ch.hearc.sitnbeer.repositories.IUserRepository;
import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements ISeeder {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    private List<User> fakeUsers;

    public UserSeeder(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.fakeUsers = new LinkedList<>();
    }

    @Override
    public void seedDB() {
        if (userRepository.findAll().isEmpty()) {
            User simpleUser = createSimpleUser();
            userRepository.save(simpleUser);

            User enterpriseUser = createEntepriseUser();
            userRepository.save(enterpriseUser);

            generateFakeUsers();
        }
    }

    public List<User> getFakeUsers(){
        return this.fakeUsers;
    }

    private User createSimpleUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@sitnbeer.com");
        user.setRole(roleRepository.findByRole(RoleEnum.USER.toString()));
        return user;
    }

    private User createEntepriseUser() {
        User user = new User();
        user.setUsername("enterprise");
        user.setPassword("test");
        user.setEmail("enterprise@sitnbeer.com");
        user.setRole(roleRepository.findByRole(RoleEnum.ENTERPRISE.toString()));
        return user;
    }

    /**
     * Seeds the database with fake users
     */
    private void generateFakeUsers() {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setUsername(faker.name().fullName());
            user.setPassword(faker.internet().password());
            user.setEmail(faker.internet().emailAddress());
            user.setRole(roleRepository.findByRole(RoleEnum.ENTERPRISE.toString()));
            fakeUsers.add(userRepository.save(user));
        }
    }
}