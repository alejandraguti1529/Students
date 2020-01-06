package com.java.student;

import java.io.InputStream;

public class Student {
	private int id;
	private InputStream photo;
	private String name;
	private String address;
	private String curp;
	private char gender;
	private String birthday;
	
	public Student() {}
	public Student(int id, InputStream photo, String name, String address, String curp, char gender, String birthday) {
		this.id = id;
		this.photo=photo;
		this.name = name;
		this.address = address;
		this.curp = curp;
		this.gender = gender;
		this.birthday = birthday;
	}
	public Student(int id ) {
		this.id = id;
	}
	
	public void setid (int id) {
		this.id= id;
	}
	
	public int getid() {
		return this.id;
	}
	
	public void setPhoto (InputStream photo) {
		this.photo= photo;
	}
	
	public InputStream getPhoto() {
		return this.photo;
	}
	public void setName (String name) {
		this.name= name;
	}
	
	public String getName() {
		return this.name;
	}
	public void setAddress (String address) {
		this.address= address;
	}
	
	public String getAddress() {
		return this.address;
	}
	

	public String getCurp() {
		return this.curp;
	}
	
	public void setCurp (String curp) {
		this.curp= curp;
	}
	
	public char getGender() {
		return this.gender;
	}
	public void setGender (char gender) {
		this.gender= gender;
	}
	
	public String getBirthday() {
		return this.birthday;
	}
	public void setBirthday (String birthday) {
		this.birthday= birthday;
	}
	


}
