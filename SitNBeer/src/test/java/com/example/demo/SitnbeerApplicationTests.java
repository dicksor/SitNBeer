package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.demo.models.Bar;
import com.example.demo.models.Order;
import com.example.demo.models.enums.OrderStatusEnum;
import com.example.demo.repositories.IBarRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SitnbeerApplicationTests {

	@Autowired
	private IBarRepository barRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void createBarShowTest(){
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/bar/add", String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
		assertThat(entity.getBody()).contains("createBar");
	}

	@Test
	public void createBeerShowTest(){
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/beer/add", String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
		assertThat(entity.getBody()).contains("createBeer");
	}

	@Test
	public void homeShowTest(){
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/home", String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
		assertThat(entity.getBody()).contains("home");
	}

	@Test
	public void createOrderShowTest(){
		List<Bar> bars	= barRepository.findAll();

		if(bars.size() > 0){
			Bar bar = bars.get(0);

			ResponseEntity<String> entity = this.restTemplate.getForEntity("/bar/" + bar.getId(), String.class);
			assertThat(entity.getStatusCodeValue()).isEqualTo(200);
			assertThat(entity.getBody()).contains("createOrder");
			assertThat(entity.getBody()).contains(bar.getName());
			assertThat(entity.getBody()).contains(bar.getAddress());
			assertThat(entity.getBody()).contains(Integer.toString(bar.getAvailableTable()));
		}else {
			assertThat(false).isTrue();
		}
	}

	@Test
	public void ordersShowTest(){
		List<Bar> bars	= barRepository.findAll();

		if(bars.size() > 0){
			Bar bar = bars.get(0);

			List<Order> orders = bar.getOrders();

			ResponseEntity<String> entity = this.restTemplate.getForEntity("/orders/" + bar.getId(), String.class);
			assertThat(entity.getStatusCodeValue()).isEqualTo(200);
			assertThat(entity.getBody()).contains("orders");

		}else {
			assertThat(false).isTrue();
		}
	}

	@Test
	public void ordersHistoryShowTest(){
		List<Bar> bars	= barRepository.findAll();

		if(bars.size() > 0){
			Bar bar = bars.get(0);

			List<Order> orders = bar.getOrders();

			ResponseEntity<String> entity = this.restTemplate.getForEntity("/orders/history/" + bar.getId(), String.class);
			assertThat(entity.getStatusCodeValue()).isEqualTo(200);
			assertThat(entity.getBody()).contains("ordersHistory");

		}else {
			assertThat(false).isTrue();
		}
	}
}
