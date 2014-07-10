package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Car;

public interface CarRepository extends CrudRepository<Car, ObjectId> {

	// wyszukanie samochodu po ID
	Car findById(ObjectId id);

	// pobranie samochodu po marce
	List<Car> findByMake(String make);
}