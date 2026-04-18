package com.iekakmi.eshop_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.iekakmi.eshop_api.domainLayer.models.entities.*;
import com.iekakmi.eshop_api.domainLayer.repositories.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

@SpringBootTest
public class DomainLayerTests
{
	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	void testFindAllCities()
	{
		List<City> cities = cityRepository.findAll();

		Assertions.assertThat(cities).isNotNull();
		Assertions.assertThat(cities.size()).isGreaterThan(0);
	}

	@Test
	void testFindCityById()
	{
		Optional<City> city = cityRepository.findById(1);

		Assertions.assertThat(city).isPresent();
		Assertions.assertThat(city.get().getName()).isNotEmpty();
	}

	@Test
	void testFindCityByName()
	{
		List<City> cities = cityRepository.findByName("Greece");

		Assertions.assertThat(cities).isNotNull();
	}

	@Test
	void testFindAllProducts()
	{
		List<Product> products = productRepository.findAll();
		Assertions.assertThat(products.size()).isGreaterThan(0);

		Product firstProduct = products.get(0);
		Assertions.assertThat(firstProduct).isNotNull();

		ProductCategory firstProductCategory = firstProduct.getCategory();
		Assertions.assertThat(firstProductCategory).isNotNull();
		Assertions.assertThat(firstProductCategory.getName())
			.isNotNull()
			.isNotEmpty();
	}

	@Test
	void testFindProductsByCategoryId()
	{
		List<Product> products = productRepository.findByCategoryId(1);

		Assertions.assertThat(products).isNotNull();
	}

	@Test
	void testFindAllOrders()
	{
		List<Order> orders = orderRepository.findAll();

		Assertions.assertThat(orders).isNotNull();
	}
}
