/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package ch.hearc.sitnbeer.controllers;

import java.security.Principal;
import java.util.Optional;

import ch.hearc.sitnbeer.models.Bar;
import ch.hearc.sitnbeer.models.User;
import ch.hearc.sitnbeer.repositories.IBarRepository;
import ch.hearc.sitnbeer.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class UserProfileController {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IBarRepository barRepository;

	@GetMapping("/profile")
	public String index(Model model, Principal principal) {

		User user = userRepository.findByUsername(principal.getName());
		Optional<Bar> optionalBar = barRepository.findByUser(user);
		Bar bar = null;
		if (optionalBar.isPresent()) {
			bar = optionalBar.get();
			model.addAttribute("bar", bar);
		}

		model.addAttribute("user", user);
		return "profile";
	}
}