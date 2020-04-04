package com.example.demo.controllers;

import java.security.Principal;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.User;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.validators.BeerAddValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.example.demo.services.BeerService;
import com.sipios.springsearch.anotation.SearchSpec;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class BeerController {

    @Autowired
    private BeerService beerService;

    @Autowired
	private IBeerRepository beerRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBarRepository barRepository;

    @Autowired
	private BeerAddValidator beerAddValidator;

    @GetMapping("/beer/add") 
	public String addBeerForm(Model model) {
        model.addAttribute("beer", new Beer());
        return "createBeer";
    }

    @GetMapping("/beers")
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);

        Page<Beer> beerPage = beerService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("beerPage", beerPage);

        int totalPages = beerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "beers";
    }

    @GetMapping("/beer/query")
    public String searchForCars(@SearchSpec Specification<Beer> specs, Model model,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);

        Page<Beer> beerPage = beerService.findPaginatedWithSpecs(PageRequest.of(currentPage - 1, pageSize),
                Specification.where(specs));
        model.addAttribute("beerPage", beerPage);

        int totalPages = beerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "beers";
    }

    @PostMapping("/beer/add")
    public String addBeer(@ModelAttribute Beer beer, Model model, BindingResult bindingResult, Principal principal){
        beerAddValidator.validate(beer, bindingResult);

		if(bindingResult.hasErrors()){
			return "createBeer";
        }
        User loggedUser = userRepository.findByUsername(principal.getName());
        Bar bar = loggedUser.getOwnedBar();
        beer.setBar(bar);

        beerRepository.save(beer);

        return "home";
    }

    @GetMapping("/beer/update/{beerId}")
    public String updateBeerForm(@PathVariable long beerId, Model model){

        Optional<Beer> optionalBeer = beerRepository.findById(beerId);
        if(optionalBeer.isPresent()){
            model.addAttribute("beer", optionalBeer.get());
            return "updateBeer";
        }
        return "home";
    }

    @PostMapping("/beer/update/{id}")
    public String updateBeer(@PathVariable Long id, @Valid Beer beer, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            beer.setId(id);
            return "updateBeer";
        }

        beerRepository.save(beer);
        return "home";
    }

    @GetMapping("/beer/edit/{barId}")
    public String updateBeerOfBar(Model model, @PathVariable long barId)
    {
        Optional<Bar> optionalBar = barRepository.findById(barId);
        Bar bar = null;

        if(optionalBar.isPresent())
        {
            bar = optionalBar.get();
            Iterable<Beer> beers = beerRepository.findByBar(bar);
            model.addAttribute("beers", beers);
            return "beersOfBar";
        }

        return "home";
    }
}