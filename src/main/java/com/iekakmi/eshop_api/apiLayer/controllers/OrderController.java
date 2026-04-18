package com.iekakmi.eshop_api.apiLayer.controllers;

import com.iekakmi.eshop_api.dataAccessLayer.services.OrderService;
import com.iekakmi.eshop_api.apiLayer.models.ResponseContainer;
import com.iekakmi.eshop_api.dataAccessLayer.models.OrderDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController
{
	private final OrderService orderService;

	public OrderController(OrderService orderService)
	{
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<ResponseContainer<List<OrderDto>>> getOrders()
	{
		List<OrderDto> response = orderService.getOrders();
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseContainer<OrderDto>> getOrderById(@PathVariable Integer id)
	{
		OrderDto response = orderService.getOrderById(id);
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@PostMapping
	public ResponseEntity<ResponseContainer<Integer>> createOrder(@Valid @RequestBody OrderDto dto)
	{
		int id = orderService.createOrder(dto);
		return new ResponseEntity<>(new ResponseContainer<>(id), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseContainer<OrderDto>> updateOrder(
			@PathVariable Integer id, @Valid @RequestBody OrderDto dto)
	{
		dto.setId(id);
		OrderDto updated = orderService.updateOrder(dto);
		return ResponseEntity.ok(new ResponseContainer<>(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Integer id)
	{
		orderService.deleteOrder(id);
		return ResponseEntity.ok().build();
	}
}
