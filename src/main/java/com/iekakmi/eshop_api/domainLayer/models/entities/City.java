package com.iekakmi.eshop_api.domainLayer.models.entities;

import com.iekakmi.eshop_api.domainLayer.models.enums.*;
import jakarta.persistence.*;

@Entity(name = DatabaseTables.CITY)
public class City
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
}
