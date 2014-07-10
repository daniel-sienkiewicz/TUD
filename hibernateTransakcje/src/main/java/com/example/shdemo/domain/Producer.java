package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Producer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String town;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Wiertara> wiertarki = new ArrayList<Wiertara>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public List<Wiertara> getWiertarki() {
		return wiertarki;
	}

	public void setWiertarki(List<Wiertara> wiertarki) {
		this.wiertarki = wiertarki;
	}
}