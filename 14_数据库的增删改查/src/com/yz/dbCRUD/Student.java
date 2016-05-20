package com.yz.dbCRUD;

public class Student {
	
	private int number;
	public Student(){
		super();
	}
	public Student(int number, String name, String sex) {
		// TODO Auto-generated constructor stub
		this.number=number;
		this.name=name;
		this.sex=sex;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	private String name;
	private String sex;
}
