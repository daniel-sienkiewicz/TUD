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
import com.example.nosqldemo.service.CarManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class CarManagerTest {

	@Autowired
	CarManager carManager;

	@Before
	// przygotowywanie bazy
	public void start() {
		carManager.deleteAll();

		Car car = new Car();
		car.setMake("Audi");
		car.setModel("90");
		carManager.addNewCar(car);

		Car car2 = new Car();
		car2.setMake("Ford");
		car2.setModel("Focus");
		carManager.addNewCar(car2);

	}

	@Test
	// sprawdzanie dodawania
	public void checkAdd() {
		Car c = new Car();
		c.setMake("Seat");
		c.setModel("Ibiza");

		carManager.addNewCar(c);
		Assert.assertEquals(3, carManager.getAllCar().size());
	}

	@Test
	// pobranie wszytsich samochodow z bazy
	public void checkGetAll() {
		Assert.assertEquals(2, carManager.getAllCar().size());
	}

	@Test
	// pobranie samochodu po nazwie
	public void checkGetByName() {
		ArrayList<Car> a = (ArrayList<Car>) carManager.getCars("Audi");
		Assert.assertEquals(1, a.size());
		Assert.assertEquals("90", a.get(0).getModel());
		Assert.assertEquals("Audi", a.get(0).getMake());
	}

	@Test
	// usuniecie samochodu z bazy
	public void checkDeleteObject() {
		Car del = new Car();
		del.setMake("Usunieta");
		carManager.addNewCar(del);

		carManager.deleteObject(del);
		Assert.assertEquals(2, carManager.getAllCar().size());
		Assert.assertEquals(0, carManager.getCars("Usunieta").size());
	}

	@Test
	// usuniecie wszystkich samochodow
	public void checkDeleteAll() {
		carManager.deleteAll();
		Assert.assertEquals(0, carManager.getAllCar().size());
	}

	@Test
	// update marki
	public void checkUpdateName() {
		carManager.updateName("Audi", "Volvo");
		Assert.assertEquals(1, carManager.getCars("Volvo").size());
		Assert.assertEquals(0, carManager.getCars("Audi").size());
		Assert.assertEquals(2, carManager.getAllCar().size());
	}
}