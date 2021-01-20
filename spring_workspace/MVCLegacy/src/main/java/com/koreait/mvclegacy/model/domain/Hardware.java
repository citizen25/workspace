package com.koreait.mvclegacy.model.domain;

public class Hardware {
	private int hardware_id;
	private String name;
	private String brand;
	private int price;

	public int getHardware_id() {
		return hardware_id;
	}
	public void setHardware_id(int hardware_id) {
		this.hardware_id = hardware_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
