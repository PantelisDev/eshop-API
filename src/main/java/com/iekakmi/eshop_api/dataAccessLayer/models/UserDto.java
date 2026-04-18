package com.iekakmi.eshop_api.dataAccessLayer.models;

import jakarta.validation.constraints.*;

public class UserDto
{
	private int id;

	@NotNull(message = "First name cannot be null")
	@NotEmpty(message = "First name cannot be empty")
	private String firstName;

	@NotNull(message = "Last name cannot be null")
	@NotEmpty(message = "Last name cannot be empty")
	private String lastName;

	private int cityId;
	private String address;
	private String phoneNo;

	@NotNull(message = "Username cannot be null")
	@NotEmpty(message = "Username cannot be empty")
	private String userName;

	private String passWord;

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
}
