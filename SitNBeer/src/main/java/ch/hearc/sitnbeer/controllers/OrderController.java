/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package ch.hearc.sitnbeer.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import ch.hearc.sitnbeer.models.Bar;
import ch.hearc.sitnbeer.models.Beer;
import ch.hearc.sitnbeer.models.Order;
import ch.hearc.sitnbeer.models.User;
import ch.hearc.sitnbeer.models.enums.OrderStatusEnum;
import ch.hearc.sitnbeer.repositories.IBarRepository;
import ch.hearc.sitnbeer.repositories.IBeerRepository;
import ch.hearc.sitnbeer.repositories.IOrderRepository;
import ch.hearc.sitnbeer.repositories.IUserRepository;
import ch.hearc.sitnbeer.validators.OrderAddValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class OrderController {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IBeerRepository beerRepository;

	@Autowired
	private IBarRepository barRepository;

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private OrderAddValidator orderAddValidator;

	// Constantes
	private static final String CLIENT_ORDERS = "clientOrders";
	private static final String ORDERS = "orders";
	private static final String ORDERS_HISTORY = "ordersHistory";
	private static final String HOME = "home";
	private static final String SHOW_BAR = "showBar";

	@GetMapping("/orders/client/{clientId}")
	public String ordersClient(Model model, @PathVariable long clientId) {
		Optional<User> optionalUser = userRepository.findById(clientId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			List<Order> orders = user.getOrders();

			model.addAttribute(ORDERS, orders);

			return CLIENT_ORDERS;
		}
		return HOME;
	}

	@GetMapping("/orders/{barId}")
	public String orders(Model model, @PathVariable long barId) {
		Optional<Bar> optionalBar = barRepository.findById(barId);
		if (optionalBar.isPresent()) {
			Bar bar = optionalBar.get();
			List<Order> orders = bar.getOrders();
			model.addAttribute(ORDERS, orders);

			return ORDERS;
		}

		return HOME;
	}

	@GetMapping("/orders/history/{barId}")
	public String ordersHistory(Model model, @PathVariable long barId) {
		Optional<Bar> optionalBar = barRepository.findById(barId);
		if (optionalBar.isPresent()) {
			Bar bar = optionalBar.get();
			List<Order> orders = bar.getOrders();
			model.addAttribute(ORDERS, orders);

			return ORDERS_HISTORY;
		}

		return HOME;
	}

	/**
	 * Update an order status for an order
	 * 
	 * @param newOrderStatusString New order status for an order
	 * @param orderId              Id of an order
	 * @return JSON which contains the new status of an order
	 */
	@GetMapping(value = "/order/update/{newOrderStatusString}/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String processOrder(@PathVariable String newOrderStatusString, @PathVariable long orderId) {
		String status = "{\"status\":\"PARAM_ERROR\"}";
		Optional<Order> optionalOrder = orderRepository.findById(orderId);

		try {
			OrderStatusEnum newOrderStatus = OrderStatusEnum.valueOf(newOrderStatusString);

			if (optionalOrder.isPresent()) {
				Order order = optionalOrder.get();
				order.setStatus(newOrderStatus);

				orderRepository.save(order);

				status = "{\"status\":\"OK\", \"orderStatus\":\"" + newOrderStatus + "\"}";
			}
		} catch (Exception e) {
			status = "{\"status\":\"PARSE_ERROR\"}";
		}
		return status;
	}

	/**
	 * Delete an order from an id
	 * 
	 * @param orderId Id of order
	 * @return JSON, which indicates whether the deletion has been carried out
	 *         correctly, status:OK or status:PARAM_ERROR
	 */
	@GetMapping(value = "order/delete/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteOrder(@PathVariable long orderId) {
		String status = "{\"status\":\"PARAM_ERRORRR\"}";

		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if (optionalOrder.isPresent()) {
			Order order = optionalOrder.get();
			orderRepository.delete(order);
			status = "{\"status\":\"OK\"}";
		}
		return status;
	}

	@PostMapping("/order/add")
	public String addOrder(@ModelAttribute Order order, @RequestParam("order-beer") long beerId, Model model,
			BindingResult bindingResult, Principal principal) {

		Optional<Beer> optionalBeer = beerRepository.findById(beerId);
		if (optionalBeer.isPresent()) {
			Beer beer = optionalBeer.get();
			order.setBeer(beer);
			order.setBar(beer.getBar());
		}

		orderAddValidator.validate(order, bindingResult);

		if (bindingResult.hasErrors()) {
			return SHOW_BAR;
		}
		order.setStatus(OrderStatusEnum.OPEN);
		order.setUser(userRepository.findByUsername(principal.getName()));
		orderRepository.save(order);

		return HOME;
	}
}