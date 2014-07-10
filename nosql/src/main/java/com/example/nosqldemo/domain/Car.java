package com.example.nosqldemo.domain;

import org.bson.types.ObjectId;

public class Car {

	private ObjectId id;
	private String make;
	private String model;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}