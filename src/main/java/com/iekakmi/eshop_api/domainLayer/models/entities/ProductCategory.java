package com.iekakmi.eshop_api.domainLayer.models.entities;

import com.iekakmi.eshop_api.domainLayer.models.enums.*;
import jakarta.persistence.*;
import java.util.*;

@Entity(name = DatabaseTables.PRODUCT_CATEGORY)
public class ProductCategory
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String name;

	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<>();

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public List<Product> getProducts() { return products; }
	public void setProducts(List<Product> products) { this.products = products; }
}
