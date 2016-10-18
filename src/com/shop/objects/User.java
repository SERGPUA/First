package com.shop.objects;

import java.util.Date;

public class User {
	private int userID;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String cantry;
	private String city;
	private String address;
	private int postCode;
	private Date registratinDate;
	private Date lastEnterDate;

	public User() {
		super();

	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public User(String login, String password, String firstName, String lastName, String cantry, String city, String address, int postCode, Date registratinDate, Date lastEnterDate) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cantry = cantry;
		this.city = city;
		this.address = address;
		this.postCode = postCode;
		this.registratinDate = registratinDate;
		this.lastEnterDate = lastEnterDate;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCantry() {
		return cantry;
	}

	public void setCantry(String cantry) {
		this.cantry = cantry;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public Date getRegistratinDate() {
		return registratinDate;
	}

	public void setRegistratinDate(Date registratinDate) {
		this.registratinDate = registratinDate;
	}

	public Date getLastEnterDate() {
		return lastEnterDate;
	}

	public void setLastEnterDate(Date lastEnterDate) {
		this.lastEnterDate = lastEnterDate;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", login=" + login + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
