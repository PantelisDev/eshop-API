package com.iekakmi.eshop_api.domainLayer.models.entities;
import com.iekakmi.eshop_api.domainLayer.models.enums.*;
import jakarta.persistence.*;
import java.util.*;
@Entity(name = DatabaseTables.PRODUCT)
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private String title;
	private String description;
	private double price;
	@Column(name = "imageUrl")
	private String imageUrl;
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private ProductCategory category;
	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems = new ArrayList<>();
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	public String getImageUrl() { return imageUrl; }
	public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
	public ProductCategory getCategory() { return category; }
	public void setCategory(ProductCategory category) { this.category = category; }
	public List<OrderItem> getOrderItems() { return orderItems; }
	public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}