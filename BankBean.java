package com.bank.bean;

public class BankBean {
	private int account_no;
	private String customer_name;
	private String phone_no;
	private String city;
	private int balance;
	private int pin;
	private String trans;
	private int i;
	
	public String getTrans() {
		return trans;
	}
	
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public BankBean(int account_no, String customer_name, String phone_no, String city, int balance, int pin) {
		
		this.account_no = account_no;
		this.customer_name = customer_name;
		this.phone_no = phone_no;
		this.city = city;
		this.balance = balance;
		this.pin = pin;
	}
	
	public BankBean() {
	
	}
	public void setTrans(String trans) {
		this.trans = trans;
		
	}

	
	
}
