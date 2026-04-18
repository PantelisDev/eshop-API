package com.iekakmi.eshop_api.domainLayer.models.entities;

import com.iekakmi.eshop_api.domainLayer.models.enums.*;
import jakarta.persistence.*;
import java.util.*;

@Entity(name = DatabaseTables.USER)
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "cityid")
	private int cityId;

	private String address;

	@Column(name = "phoneno")
	private String phoneNo;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String passWord;

	@OneToMany(mappedBy = "user")
	private List<Order> orders = new ArrayList<>();

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public int getCityId() { return cityId; }
	public void setCityId(int cityId) { this.cityId = cityId; }
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	public String getPhoneNo() { return phoneNo; }
	public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
	public String getPassWord() { return passWord; }
	public void setPassWord(String passWord) { this.passWord = passWord; }
	public List<Order> getOrders() { return orders; }
	public void setOrders(List<Order> orders) { this.orders = orders; }
}
