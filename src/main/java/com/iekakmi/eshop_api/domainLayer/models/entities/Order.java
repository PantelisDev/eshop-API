package com.iekakmi.eshop_api.domainLayer.models.entities;

import com.iekakmi.eshop_api.domainLayer.models.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = DatabaseTables.ORDER)
public class Order
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "orderdate")
	private LocalDateTime orderDate;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>();

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public LocalDateTime getOrderDate() { return orderDate; }
	public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	public List<OrderItem> getOrderItems() { return orderItems; }
	public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
