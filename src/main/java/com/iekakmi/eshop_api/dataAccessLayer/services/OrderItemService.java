package com.iekakmi.eshop_api.dataAccessLayer.services;

import com.iekakmi.eshop_api.domainLayer.repositories.OrderItemRepository;
import com.iekakmi.eshop_api.domainLayer.repositories.OrderRepository;
import com.iekakmi.eshop_api.domainLayer.repositories.ProductRepository;
import com.iekakmi.eshop_api.domainLayer.models.entities.*;
import com.iekakmi.eshop_api.dataAccessLayer.models.OrderItemDto;
import com.iekakmi.eshop_api.dataAccessLayer.models.exceptions.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class OrderItemService
{
	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;

	public OrderItemService(OrderItemRepository orderItemRepository,
							OrderRepository orderRepository,
							ProductRepository productRepository)
	{
		this.orderItemRepository = orderItemRepository;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	public List<OrderItemDto> getOrderItems()
	{
		return orderItemRepository.findAll().stream()
				.map(oi -> {
					OrderItemDto dto = new OrderItemDto();
					dto.setId(oi.getId());
					dto.setOrderId(oi.getOrder().getId());
					dto.setProductId(oi.getProduct().getId());
					return dto;
				})
				.collect(Collectors.toList());
	}

	public OrderItemDto getOrderItemById(int id)
	{
		OrderItem oi = orderItemRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("OrderItem", id));
		OrderItemDto dto = new OrderItemDto();
		dto.setId(oi.getId());
		dto.setOrderId(oi.getOrder().getId());
		dto.setProductId(oi.getProduct().getId());
		return dto;
	}

	@Transactional
	public int createOrderItem(OrderItemDto oi)
	{
		Order order = orderRepository.findById(oi.getOrderId())
				.orElseThrow(() -> new EntityNotFoundException("Order", oi.getOrderId()));
		Product product = productRepository.findById(oi.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", oi.getProductId()));

		OrderItem entity = new OrderItem();
		entity.setOrder(order);
		entity.setProduct(product);
		OrderItem saved = orderItemRepository.save(entity);
		return saved.getId();
	}

	@Transactional
	public void deleteOrderItem(int id)
	{
		orderItemRepository.deleteById(id);
	}
}
