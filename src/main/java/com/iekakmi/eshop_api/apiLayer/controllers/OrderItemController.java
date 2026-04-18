package com.iekakmi.eshop_api.apiLayer.controllers;

import com.iekakmi.eshop_api.dataAccessLayer.services.OrderItemService;
import com.iekakmi.eshop_api.apiLayer.models.ResponseContainer;
import com.iekakmi.eshop_api.dataAccessLayer.models.OrderItemDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController
{
	private final OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemService)
	{
		this.orderItemService = orderItemService;
	}

	@GetMapping
	public ResponseEntity<ResponseContainer<List<OrderItemDto>>> getOrderItems()
	{
		List<OrderItemDto> response = orderItemService.getOrderItems();
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseContainer<OrderItemDto>> getOrderItemById(@PathVariable Integer id)
	{
		OrderItemDto response = orderItemService.getOrderItemById(id);
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@PostMapping
	public ResponseEntity<ResponseContainer<Integer>> createOrderItem(@RequestBody OrderItemDto dto)
	{
		int id = orderItemService.createOrderItem(dto);
		return new ResponseEntity<>(new ResponseContainer<>(id), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id)
	{
		orderItemService.deleteOrderItem(id);
		return ResponseEntity.ok().build();
	}
}
