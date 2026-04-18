package com.iekakmi.eshop_api.dataAccessLayer.models;

public class OrderItemDto
{
	private int id;
	private int orderId;
	private int productId;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public int getOrderId() { return orderId; }
	public void setOrderId(int orderId) { this.orderId = orderId; }
	public int getProductId() { return productId; }
	public void setProductId(int productId) { this.productId = productId; }
}
