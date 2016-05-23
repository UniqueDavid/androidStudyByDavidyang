package com.example.minismshelper.daomain;

public class Contacter {
	private String name;
	public Contacter(String name, String number) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.number=number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	private String number;
}
