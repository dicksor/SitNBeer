/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */
package ch.hearc.sitnbeer.seeders;

import ch.hearc.sitnbeer.repositories.IBarRepository;
import ch.hearc.sitnbeer.repositories.IBeerRepository;
import ch.hearc.sitnbeer.repositories.IOrderRepository;
import ch.hearc.sitnbeer.repositories.IRoleRepository;
import ch.hearc.sitnbeer.repositories.IUserRepository;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements ISeeder {

    private IBarRepository barRepository;
    private IBeerRepository beerRepository;
    private IOrderRepository orderRepository;
    private IRoleRepository roleRepository;
    private IUserRepository userRepository;
    private UserSeeder userSeeder;
    private BarSeeder barSeeder;
    private BeerSeeder beerSeeder;

    public Seeder(IBarRepository barRepository, IBeerRepository beerRepository, IOrderRepository orderRepository,
            IRoleRepository roleRepository, IUserRepository userRepository) {
        this.barRepository = barRepository;
        this.beerRepository = beerRepository;
        this.orderRepository = orderRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedDB();
    }

    /**
     * Seeds all the tables in the database with fake data
     */
    @Override
    public void seedDB() {
        seedRoleTable();
        seedUserTable();
        seedBarTable();
        seedBeerTable();
        seedOrderTable();
    }

    private void seedRoleTable() {
        RoleSeeder roleSeeder = new RoleSeeder(roleRepository);
        roleSeeder.seedDB();
    }

    private void seedUserTable() {
        this.userSeeder = new UserSeeder(userRepository, roleRepository);
        userSeeder.seedDB();
    }

    private void seedBarTable() {
        this.barSeeder = new BarSeeder(barRepository, userSeeder.getFakeUsers());
        barSeeder.seedDB();
    }

    private void seedBeerTable() {
        this.beerSeeder = new BeerSeeder(beerRepository, barSeeder.getFakeBars());
        beerSeeder.seedDB();
    }

    private void seedOrderTable() {
        OrderSeeder orderSeeder = new OrderSeeder(orderRepository, userSeeder.getFakeUsers(), beerSeeder.getFakeBeers());
        orderSeeder.seedDB();
    }

}