package com.iekakmi.eshop_api.dataAccessLayer.models;

import jakarta.validation.constraints.*;

public class CityDto
{
	private int id;

	@NotNull(message = "Name cannot be null")
	@NotEmpty(message = "Name cannot be empty")
	private String name;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
}
