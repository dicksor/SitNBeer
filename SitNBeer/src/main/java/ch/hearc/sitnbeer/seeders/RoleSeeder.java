package ch.hearc.sitnbeer.seeders;

import ch.hearc.sitnbeer.models.Role;
import ch.hearc.sitnbeer.models.enums.RoleEnum;
import ch.hearc.sitnbeer.repositories.IRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements ISeeder {

    @Autowired
    private IRoleRepository roleRepository;

    public RoleSeeder(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Seeds the database with Roles
     */
    @Override
    public void seedDB() {
        if (roleRepository.findAll().isEmpty()) {
            Role userRole = new Role();
            userRole.setRoleName(RoleEnum.USER.toString());
            roleRepository.save(userRole);

            Role enterpriseRole = new Role();
            enterpriseRole.setRoleName(RoleEnum.ENTERPRISE.toString());
            roleRepository.save(enterpriseRole);

            Role visitorRole = new Role();
            visitorRole.setRoleName(RoleEnum.VISITOR.toString());
            roleRepository.save(visitorRole);
        }
    }

}