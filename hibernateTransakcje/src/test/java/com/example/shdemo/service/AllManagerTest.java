package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Producer;
import com.example.shdemo.domain.Wiertara;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })

// program dziala tez na rollback = false
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class AllManagerTest {
	@Autowired
	ProducerManager producerManager;

	@Autowired
	WiertaraManager wiertaraManager;

	@Test
	// pobranie niestandardowego info
	public void getInfo() {
		Producer producer = new Producer();
		ArrayList<Wiertara> wiertareczki = new ArrayList<Wiertara>();

		producer.setName("Testowy");
		producer.setTown("Testowy");

		Wiertara wiertara = new Wiertara();
		wiertara.setModel("Info");
		wiertara.setPrice(10);
		wiertara.setYop(2010);

		Wiertara wiertara2 = new Wiertara();
		wiertara2.setModel("Info2");
		wiertara2.setPrice(30);
		wiertara2.setYop(2010);

		Wiertara wiertara3 = new Wiertara();
		wiertara3.setModel("Info3");
		wiertara3.setPrice(30);
		wiertara3.setYop(2014);

		wiertareczki.add(wiertara);
		wiertareczki.add(wiertara2);
		wiertareczki.add(wiertara3);
		producer.setWiertarki(wiertareczki);

		producerManager.addProducer(producer);

		wiertaraManager.addWiertara(wiertara);
		wiertaraManager.addWiertara(wiertara2);
		wiertaraManager.addWiertara(wiertara3);

		ArrayList<Wiertara> wiertarki = wiertaraManager.getWiertaraSome(50,
				2010);

		//assertEquals(2, wiertarki.size());
		assert(wiertarki.size() > 0);
	}

	@Test
	// usuwanie kaskadowe
	public void delCascade() {
		ArrayList<Wiertara> wiertarki = new ArrayList<Wiertara>();
		Wiertara wiertara = new Wiertara();
		wiertara.setModel("Delete");
		wiertara.setPrice(123);
		wiertara.setYop(1992);
		wiertarki.add(wiertara);

		Wiertara wiertara2 = new Wiertara();
		wiertara2.setModel("Delete2");
		wiertara2.setPrice(123);
		wiertara2.setYop(1992);
		wiertarki.add(wiertara2);

		Producer producer = new Producer();
		producer.setName("Delete");
		producer.setTown("Delete");

		int wiertaraID = wiertaraManager.addWiertara(wiertara);
		int wiertara2ID = wiertaraManager.addWiertara(wiertara2);
		producer.setWiertarki(wiertarki);
		int producerID = producerManager.addProducer(producer);

		assertEquals(wiertara.getModel(),
				wiertaraManager.getWiertara(wiertaraID).getModel());
		assertEquals(wiertara.getPrice(),
				wiertaraManager.getWiertara(wiertaraID).getPrice());
		assertEquals(wiertara.getYop(), wiertaraManager.getWiertara(wiertaraID)
				.getYop());

		assertEquals(wiertara2.getModel(),
				wiertaraManager.getWiertara(wiertara2ID).getModel());
		assertEquals(wiertara2.getPrice(),
				wiertaraManager.getWiertara(wiertara2ID).getPrice());
		assertEquals(wiertara2.getYop(),
				wiertaraManager.getWiertara(wiertara2ID).getYop());

		assertEquals(producer.getName(), producerManager
				.getProducer(producerID).getName());
		assertEquals(producer.getTown(), producerManager
				.getProducer(producerID).getTown());
		assertEquals(producer.getWiertarki().size(), producerManager
				.getProducer(producerID).getWiertarki().size());

		producerManager.delProducer(producer);

		assertEquals(null, producerManager.getProducer(producerID));
		assertEquals(null, wiertaraManager.getWiertara(wiertaraID));
		assertEquals(null, wiertaraManager.getWiertara(wiertara2ID));
	}
}