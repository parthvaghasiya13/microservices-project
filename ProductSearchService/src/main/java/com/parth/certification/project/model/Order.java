package com.parth.certification.project.model;

public class Order {
	private String Name;
	private int amount;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Order(String name, int amount) {
		super();
		Name = name;
		this.amount = amount;
	}
	
}
