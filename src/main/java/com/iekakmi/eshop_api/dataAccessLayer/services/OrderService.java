package com.iekakmi.eshop_api.dataAccessLayer.services;

import com.iekakmi.eshop_api.domainLayer.repositories.OrderRepository;
import com.iekakmi.eshop_api.domainLayer.repositories.UserRepository;
import com.iekakmi.eshop_api.domainLayer.models.entities.Order;
import com.iekakmi.eshop_api.domainLayer.models.entities.User;
import com.iekakmi.eshop_api.dataAccessLayer.models.OrderDto;
import com.iekakmi.eshop_api.dataAccessLayer.models.exceptions.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import java.util.List;

@Service
@Validated
public class OrderService
{
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;

	public OrderService(OrderRepository orderRepository, UserRepository userRepository)
	{
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}

	public List<OrderDto> getOrders()
	{
		return orderRepository.findAll().stream()
				.map(o -> {
					OrderDto dto = new OrderDto();
					dto.setId(o.getId());
					dto.setOrderDate(o.getOrderDate());
					dto.setUserId(o.getUser().getId());
					return dto;
				})
				.collect(Collectors.toList());
	}

	public OrderDto getOrderById(int id)
	{
		Order o = orderRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Order", id));
		OrderDto dto = new OrderDto();
		dto.setId(o.getId());
		dto.setOrderDate(o.getOrderDate());
		dto.setUserId(o.getUser().getId());
		return dto;
	}

	@Transactional
	public int createOrder(@Valid OrderDto o)
	{
		User user = userRepository.findById(o.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("User", o.getUserId()));

		Order entity = new Order();
		entity.setOrderDate(o.getOrderDate());
		entity.setUser(user);
		Order saved = orderRepository.save(entity);
		return saved.getId();
	}

	@Transactional
	public OrderDto updateOrder(@Valid OrderDto o)
	{
		Order entity = orderRepository.findById(o.getId())
				.orElseThrow(() -> new EntityNotFoundException("Order", o.getId()));
		entity.setOrderDate(o.getOrderDate());
		orderRepository.save(entity);
		return o;
	}

	@Transactional
	public void deleteOrder(int id)
	{
		orderRepository.deleteById(id);
	}
}
