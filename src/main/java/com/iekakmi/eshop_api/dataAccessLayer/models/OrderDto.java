package com.iekakmi.eshop_api.dataAccessLayer.models;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class OrderDto
{
	private int id;

	@NotNull(message = "Order date cannot be null")
	private LocalDateTime orderDate;

	private int userId;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public LocalDateTime getOrderDate() { return orderDate; }
	public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }
}
