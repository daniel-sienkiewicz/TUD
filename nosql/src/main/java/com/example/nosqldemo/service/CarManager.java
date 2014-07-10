package com.example.nosqldemo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Car;
import com.example.nosqldemo.repository.CarRepository;

@Component
public class CarManager {

	@Autowired
	private CarRepository carRepository;

	// dodanie samochodu
	public void addNewCar(Car car) {
		carRepository.save(car);
	}

	// pobranie wszytskich samochodow po marce
	public List<Car> getCars(String make) {
		return carRepository.findByMake(make);
	}

	// pobranie samochodow po ID
	public Car getCar(ObjectId id) {
		return carRepository.findById(id);
	}

	// pobranie wszytskich samochodów
	public List<Car> getAllCar() {
		ArrayList<Car> cars = (ArrayList<Car>) carRepository.findAll();
		return cars;
	}

	// usuniecie samochodu
	public void deleteObject(Car w) {
		carRepository.delete(w);
	}

	// usuniecie wszystkich samochodow
	public void deleteAll() {
		carRepository.deleteAll();
	}

	// update marki
	public void updateName(String oldMake, String newMake) {
		ArrayList<Car> w = (ArrayList<Car>) carRepository.findByMake(oldMake);
		w.get(0).setMake(newMake);
		carRepository.save(w);
	}
}