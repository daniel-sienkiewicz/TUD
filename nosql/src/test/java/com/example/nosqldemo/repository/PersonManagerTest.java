package com.example.nosqldemo.repository;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.domain.Car;
import com.example.nosqldemo.domain.Person;
import com.example.nosqldemo.service.PersonManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class PersonManagerTest {

	@Autowired
	PersonManager personManager;

	@Before
	// przygotowywanie bazy
	public void start() {
		personManager.deleteAll();

		ArrayList<Car> c1 = new ArrayList<Car>();
		ArrayList<Car> c2 = new ArrayList<Car>();

		Car car = new Car();
		car.setMake("Audi");
		car.setModel("90");

		Car car2 = new Car();
		car2.setMake("Ford");
		car2.setModel("Focus");

		Car car3 = new Car();
		car3.setMake("Audi");
		car3.setModel("80");

		c1.add(car);

		c2.add(car2);
		c2.add(car3);

		Person person = new Person();
		person.setName("Sabina");
		person.setCars(c1);
		personManager.addNewPerson(person);

		Person person2 = new Person();
		person2.setName("Milena");
		person2.setCars(c2);
		personManager.addNewPerson(person2);
	}

	@Test
	// sprawdzanie dodawania
	public void checkAdd() {
		Person p = new Person();
		p.setName("Ania");

		personManager.addNewPerson(p);
		Assert.assertEquals(3, personManager.getAllPerson().size());
	}

	@Test
	// pobranie wszytskich osob z bazy
	public void checkGetAll() {
		Assert.assertEquals(2, personManager.getAllPerson().size());
	}

	@Test
	// pobranie osoby po imieniu
	public void checkGetByName() {
		ArrayList<Person> a = (ArrayList<Person>) personManager.getPersons("Sabina");
		Assert.assertEquals(1, a.size());
		Assert.assertEquals("Sabina", a.get(0).getName());
		Assert.assertEquals(1, a.get(0).getCars().size());
	}

	@Test
	// usuniecie osoby
	public void checkDeleteObject() {
		Person del = new Person();
		del.setName("Usunieta");
		personManager.addNewPerson(del);

		personManager.deleteObject(del);
		Assert.assertEquals(2, personManager.getAllPerson().size());
		Assert.assertEquals(0, personManager.getPersons("Usunieta").size());
	}

	@Test
	// usuniecie wszytskich osob
	public void checkDeleteAll() {
		personManager.deleteAll();
		Assert.assertEquals(0, personManager.getAllPerson().size());
	}

	@Test
	// update imienia osoby
	public void checkUpdateName() {
		personManager.updateName("Sabina", "Karolina");
		Assert.assertEquals(1, personManager.getPersons("Karolina").size());
		Assert.assertEquals(0, personManager.getPersons("Sabina").size());
		Assert.assertEquals(2, personManager.getAllPerson().size());
	}

	@Test
	// usuniecie Y z X - samochodu z osoby
	public void delYcheck() {
		personManager.delCar("Milena", "Ford");
		Assert.assertEquals(1, personManager.getPersons("Milena").size());
	}

	@Test
	// pobranie Y z X - samochodu z osoby
	public void searchYcheck() {
		ArrayList<Car> cars = new ArrayList<Car>();
		cars = personManager.searchY("Milena", "Audi");
		Assert.assertEquals(1, cars.size());
	}
}