package com.example.jdbc.domain;

public class Kawa {

	public Kawa() {

	}

	public Kawa(int id, String name, int type, int cena) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.cena = cena;
	}
	

	public Kawa(String name, int type, int cena) {;
		this.name = name;
		this.type = type;
		this.cena = cena;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getCena() {
		return this.cena;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	private String name;
	private int type;
	private int cena;
	private int id;

}