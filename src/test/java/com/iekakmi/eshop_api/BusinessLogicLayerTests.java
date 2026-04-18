package com.iekakmi.eshop_api;

import com.iekakmi.eshop_api.dataAccessLayer.models.*;
import com.iekakmi.eshop_api.dataAccessLayer.services.*;
import com.iekakmi.eshop_api.dataAccessLayer.models.exceptions.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class BusinessLogicLayerTests
{
	@Autowired
	private CityService cityService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	// --- CityService ---

	@Test
	void testCreateAndGetCity()
	{
		CityDto city = new CityDto();
		city.setName("TestCity");
		int id = cityService.createCity(city);

		CityDto fetched = cityService.getCityById(id);
		Assertions.assertThat(fetched.getName()).isEqualTo("TestCity");
	}

	@Test
	void testGetAllCities()
	{
		CityDto city = new CityDto();
		city.setName("TestCity");
		cityService.createCity(city);

		List<CityDto> cities = cityService.getCities();
		Assertions.assertThat(cities).isNotEmpty();
	}

	@Test
	void testUpdateCity()
	{
		CityDto city = new CityDto();
		city.setName("Original");
		int id = cityService.createCity(city);

		CityDto update = new CityDto();
		update.setId(id);
		update.setName("Updated");
		cityService.updateCity(update);

		CityDto fetched = cityService.getCityById(id);
		Assertions.assertThat(fetched.getName()).isEqualTo("Updated");
	}

	@Test
	void testDeleteCity()
	{
		CityDto city = new CityDto();
		city.setName("ToDelete");
		int id = cityService.createCity(city);

		cityService.deleteCity(id);

		Assertions.assertThatThrownBy(() -> cityService.getCityById(id))
				.isInstanceOf(EntityNotFoundException.class);
	}

	@Test
	void testGetCityByIdNotFound()
	{
		Assertions.assertThatThrownBy(() -> cityService.getCityById(999999))
				.isInstanceOf(EntityNotFoundException.class)
				.hasMessageContaining("City not found");
	}

	@Test
	void testCreateCityWithNullName()
	{
		CityDto city = new CityDto();
		city.setName(null);

		Assertions.assertThatThrownBy(() -> cityService.createCity(city))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void testCreateCityWithEmptyName()
	{
		CityDto city = new CityDto();
		city.setName("");

		Assertions.assertThatThrownBy(() -> cityService.createCity(city))
				.isInstanceOf(ConstraintViolationException.class);
	}

	// --- ProductCategoryService ---

	@Test
	void testCreateAndGetProductCategory()
	{
		ProductCategoryDto cat = new ProductCategoryDto();
		cat.setName("TestCategory");
		int id = productCategoryService.createProductCategory(cat);

		ProductCategoryDto fetched = productCategoryService.getProductCategoryById(id);
		Assertions.assertThat(fetched.getName()).isEqualTo("TestCategory");
	}

	@Test
	void testCreateProductCategoryWithNullName()
	{
		ProductCategoryDto cat = new ProductCategoryDto();
		cat.setName(null);

		Assertions.assertThatThrownBy(() -> productCategoryService.createProductCategory(cat))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void testGetProductCategoryByIdNotFound()
	{
		Assertions.assertThatThrownBy(() -> productCategoryService.getProductCategoryById(999999))
				.isInstanceOf(EntityNotFoundException.class)
				.hasMessageContaining("ProductCategory not found");
	}

	// --- ProductService ---

	@Test
	void testCreateAndGetProduct()
	{
		ProductCategoryDto cat = new ProductCategoryDto();
		cat.setName("Electronics");
		int catId = productCategoryService.createProductCategory(cat);

		ProductDto product = new ProductDto();
		product.setTitle("Laptop");
		product.setDescription("A powerful laptop");
		product.setPrice(999.99);
		product.setCategoryId(catId);
		int prodId = productService.createProduct(product);

		ProductDto fetched = productService.getProductById(prodId);
		Assertions.assertThat(fetched.getTitle()).isEqualTo("Laptop");
		Assertions.assertThat(fetched.getPrice()).isEqualTo(999.99);
		Assertions.assertThat(fetched.getCategoryName()).isEqualTo("Electronics");
	}

	@Test
	void testUpdateProduct()
	{
		ProductCategoryDto cat = new ProductCategoryDto();
		cat.setName("Books");
		int catId = productCategoryService.createProductCategory(cat);

		ProductDto product = new ProductDto();
		product.setTitle("Old Title");
		product.setDescription("Desc");
		product.setPrice(10.0);
		product.setCategoryId(catId);
		int prodId = productService.createProduct(product);

		ProductDto update = new ProductDto();
		update.setId(prodId);
		update.setTitle("New Title");
		update.setDescription("New Desc");
		update.setPrice(20.0);
		productService.updateProduct(update);

		ProductDto fetched = productService.getProductById(prodId);
		Assertions.assertThat(fetched.getTitle()).isEqualTo("New Title");
		Assertions.assertThat(fetched.getPrice()).isEqualTo(20.0);
	}

	@Test
	void testCreateProductWithInvalidCategory()
	{
		ProductDto product = new ProductDto();
		product.setTitle("Orphan");
		product.setDescription("No category");
		product.setPrice(10.0);
		product.setCategoryId(999999);

		Assertions.assertThatThrownBy(() -> productService.createProduct(product))
				.isInstanceOf(EntityNotFoundException.class)
				.hasMessageContaining("ProductCategory not found");
	}

	@Test
	void testCreateProductWithNullTitle()
	{
		ProductDto product = new ProductDto();
		product.setTitle(null);
		product.setPrice(10.0);
		product.setCategoryId(1);

		Assertions.assertThatThrownBy(() -> productService.createProduct(product))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void testCreateProductWithNegativePrice()
	{
		ProductDto product = new ProductDto();
		product.setTitle("Bad Product");
		product.setPrice(-5.0);
		product.setCategoryId(1);

		Assertions.assertThatThrownBy(() -> productService.createProduct(product))
				.isInstanceOf(ConstraintViolationException.class);
	}

	// --- UserService ---

	@Test
	void testCreateUserWithEmptyUsername()
	{
		UserDto user = new UserDto();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setUserName("");

		Assertions.assertThatThrownBy(() -> userService.createUser(user))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void testGetUserByIdNotFound()
	{
		Assertions.assertThatThrownBy(() -> userService.getUserById(999999))
				.isInstanceOf(EntityNotFoundException.class)
				.hasMessageContaining("User not found");
	}

	// --- OrderService ---

	@Test
	void testCreateOrderWithInvalidUser()
	{
		OrderDto order = new OrderDto();
		order.setOrderDate(LocalDateTime.of(2026, 1, 15, 10, 0));
		order.setUserId(999999);

		Assertions.assertThatThrownBy(() -> orderService.createOrder(order))
				.isInstanceOf(EntityNotFoundException.class)
				.hasMessageContaining("User not found");
	}

	@Test
	void testCreateOrderWithNullDate()
	{
		OrderDto order = new OrderDto();
		order.setOrderDate(null);
		order.setUserId(1);

		Assertions.assertThatThrownBy(() -> orderService.createOrder(order))
				.isInstanceOf(ConstraintViolationException.class);
	}

	// --- OrderItemService ---

	@Test
	void testCreateOrderItemWithInvalidOrder()
	{
		OrderItemDto item = new OrderItemDto();
		item.setOrderId(999999);
		item.setProductId(1);

		Assertions.assertThatThrownBy(() -> orderItemService.createOrderItem(item))
				.isInstanceOf(EntityNotFoundException.class)
				.hasMessageContaining("Order not found");
	}
}
