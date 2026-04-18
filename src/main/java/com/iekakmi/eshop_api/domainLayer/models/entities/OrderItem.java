package com.iekakmi.eshop_api.domainLayer.models.entities;

import com.iekakmi.eshop_api.domainLayer.models.enums.*;
import jakarta.persistence.*;

@Entity(name = DatabaseTables.ORDER_ITEM)
public class OrderItem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "orderid")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public Order getOrder() { return order; }
	public void setOrder(Order order) { this.order = order; }
	public Product getProduct() { return product; }
	public void setProduct(Product product) { this.product = product; }
}
