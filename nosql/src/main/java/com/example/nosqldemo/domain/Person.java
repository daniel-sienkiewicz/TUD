package com.example.nosqldemo.domain;

import java.util.List;

import org.bson.types.ObjectId;

public class Person {

	private ObjectId id;
	private String name;

	// Lista samochodow - jedna osoba moze miec wiele samochodow, ale jeden
	// samochod moze miec jedna osobe
	private List<Car> cars;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}