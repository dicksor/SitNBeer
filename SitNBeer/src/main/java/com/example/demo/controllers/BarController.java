package com.example.demo.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.validators.BarAddValidator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.example.demo.services.BarService;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class BarController {

	@Autowired
	private BarService barService;

	@Autowired
	private BarAddValidator barAddValidator;

	@Autowired
	private IBarRepository barRepository;

	@Autowired
	private IBeerRepository beerRepository;

	@Autowired
	private IUserRepository userRepository;

	@RequestMapping("/bars")
	public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(8);

		Page<Bar> barPage = barService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
		model.addAttribute("barPage", barPage);

		int totalPages = barPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "bars";
	}

	@GetMapping("/bar/query")
    public String searchForCars(@SearchSpec Specification<Bar> specs, Model model,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);

        Page<Bar> barPage = barService.findPaginatedWithSpecs(PageRequest.of(currentPage - 1, pageSize),
                Specification.where(specs));
        model.addAttribute("barPage", barPage);

        int totalPages = barPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
		}

        return "bars";
    }

    @GetMapping("/bar/add")
	public String addBarForm(Model model) {
		model.addAttribute("bar", new Bar());
		return "createBar";
	}

	@PostMapping("/bar/add")
	public String addBar(@ModelAttribute Bar bar, Model model, BindingResult bindingResult, Principal principal) {

		barAddValidator.validate(bar, bindingResult);

		if(bindingResult.hasErrors()){
			return "createBar";
		}

		/*User loggedUser = userRepository.findByName(principal.getName());

		bar.setUser(loggedUser);
		barRepository.save(bar);*/

		return "redirect:/";
	}

	@GetMapping("/bar/{bar_id}")
	public String showBar(Model model, @PathVariable Integer bar_id){
		Optional<Bar> optionalBar = barRepository.findById(bar_id);
		Bar bar = null;
		if(optionalBar.isPresent()){
			bar = optionalBar.get();
		}else {
			return "redirect:/";
		}

		Iterable<Beer> beers = bar.getBeers();

		model.addAttribute("bar", bar);
		model.addAttribute("order", new Order());
		model.addAttribute("beers", beers);

		return "showBar";
	}
}