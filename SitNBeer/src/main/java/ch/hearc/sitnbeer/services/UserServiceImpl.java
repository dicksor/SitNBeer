/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */
package ch.hearc.sitnbeer.services;

import ch.hearc.sitnbeer.models.User;
import ch.hearc.sitnbeer.repositories.IRoleRepository;
import ch.hearc.sitnbeer.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.hearc.sitnbeer.services.interfaces.IUserService;
import ch.hearc.sitnbeer.models.enums.RoleEnum;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByRole((RoleEnum.USER).toString()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
