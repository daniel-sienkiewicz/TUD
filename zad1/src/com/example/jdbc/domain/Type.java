package com.example.jdbc.domain;

public class Type {
	private int id;
	private String nazwa;

	public Type(int id, String nazwa) {
		this.id = id;
		this.nazwa = nazwa;
	}
	
	public Type(String nazwa) {
		this.nazwa = nazwa;
	}

	public Type() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

}