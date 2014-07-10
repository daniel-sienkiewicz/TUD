package com.example.nosqldemo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Car;
import com.example.nosqldemo.domain.Person;
import com.example.nosqldemo.repository.PersonRepository;

@Component
public class PersonManager {

	@Autowired
	private PersonRepository personRepository;

	// dodanie nowej osoby
	public void addNewPerson(Person person) {
		personRepository.save(person);
	}

	// pobranie osoby po imieniu
	public List<Person> getPersons(String name) {
		return personRepository.findByName(name);
	}

	// pobranie osoby po ID
	public Person getPerson(ObjectId id) {
		return personRepository.findById(id);
	}

	// pobranie wszytskich osob
	public List<Person> getAllPerson() {
		ArrayList<Person> persons = (ArrayList<Person>) personRepository.findAll();
		return persons;
	}

	// usuniecie osoby
	public void deleteObject(Person p) {
		personRepository.delete(p);
	}

	// usuniecie wszystkich osob
	public void deleteAll() {
		personRepository.deleteAll();
	}

	// update imienia
	public void updateName(String oldName, String newName) {
		ArrayList<Person> w = (ArrayList<Person>) personRepository.findByName(oldName);
		w.get(0).setName(newName);
		personRepository.save(w);
	}

	// usunuiecie Y z X - samochodu z osoby
	public void delCar(String personName, String carName) {
		ArrayList<Person> w = (ArrayList<Person>) personRepository.findByName(personName);

		for (Car c : w.get(0).getCars()) {
			if (c.getMake().equals(carName))
				w.get(0).getCars().remove(c);
		}

		personRepository.save(w);
	}

	// pobranie Y z X - samochodu z osoby
	public ArrayList<Car> searchY(String personName, String carName) {
		ArrayList<Person> w = (ArrayList<Person>) personRepository.findByName(personName);
		ArrayList<Car> cars = new ArrayList<Car>();

		for (Car c : w.get(0).getCars()) {
			if (c.getMake().equals(carName))
				cars.add(c);
		}

		return cars;
	}
}