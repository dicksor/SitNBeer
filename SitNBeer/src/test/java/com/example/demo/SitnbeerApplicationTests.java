package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.models.Bar;
import com.example.demo.models.Order;
import com.example.demo.models.enums.OrderStatusEnum;
import com.example.demo.repositories.IBarRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SitnbeerApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IBarRepository barRepository;

	@Test
	public void addBarShowTest() throws Exception {
		mvc.perform(get("/bar/add")).andExpect(status().isOk())
				.andExpect(content().string(containsString("createBar")));
	}

	@Test
	public void addBeerShowTest() throws Exception {
		mvc.perform(get("/beer/add")).andExpect(status().isOk())
				.andExpect(content().string(containsString("createBeer")));
	}

	@Test
	public void homeShowTest() throws Exception {
		mvc.perform(get("/home")).andExpect(status().isOk()).andExpect(content().string(containsString("home")));
	}

	@Test
	public void checkBar() throws Exception {
		List<Bar> bars = barRepository.findAll();

		if (bars.size() > 0) {
			Bar bar = bars.get(0);

			mvc.perform(get("/bar/" + bar.getId())).andExpect(status().isOk())
					.andExpect(content().string(containsString(bar.getName())))
					.andExpect(content().string(containsString(bar.getAddress())))
					.andExpect(content().string(containsString(Integer.toString(bar.getAvailableTable()))));
		}
	}

	@Test
	public void test() throws Exception {
		assertTrue(OrderStatusEnum.CLOSE == OrderStatusEnum.CLOSE);
	}

	@Test
	public void checkOrders() throws Exception {
		List<Bar> bars = barRepository.findAll();

		if (bars.size() > 0) {
			Bar bar = bars.get(0);

			List<Order> orders = bar.getOrders();

			if (orders.size() > 0) {
				Order order = orders.get(0);

				if(order.getStatus() == OrderStatusEnum.OPEN || order.getStatus() == OrderStatusEnum.IN_PROCESS)
				{
					mvc.perform(get("/orders/" + bar.getId())).andExpect(status().isOk())
					.andExpect(content().string(containsString("orders")))
					.andExpect(content().string(containsString(Long.toString(order.getId()))))
					.andExpect(content().string(containsString(Integer.toString(order.getTableNumber()))));
				}
				else 
				{
					mvc.perform(get("/orders/" + bar.getId())).andExpect(status().isOk())
					.andExpect(content().string(containsString("orders")));
				}
			}
		}
	}

}
