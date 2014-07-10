package com.example.jdbcdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.jdbc.domain.Kawa;
import com.example.jdbc.domain.Type;
import com.example.jdbcdemo.domain.KawaMenager;
import com.example.jdbcdemo.domain.TypeMenager;

public class KawaMenagerTest {
	TypeMenager typeManager = new TypeMenager();
	KawaMenager kawaManager = new KawaMenager();

	@Before
	public void start() {

		Type type1 = new Type("rozpuszczalna");
		Type type2 = new Type("sypana");
		Type type3 = new Type("inna");
		Type type4 = new Type("del");
		Type type5 = new Type("poprawienie");

		typeManager.AddType(type1);
		typeManager.AddType(type2);
		typeManager.AddType(type3);
		typeManager.AddType(type4);
		typeManager.AddType(type5);

		Kawa kawa1 = new Kawa("Nescafe", 1, 10);
		Kawa kawa2 = new Kawa("Nescafeasdfsaf", 1, 10);
		Kawa kawa3 = new Kawa("Nescafesadfdsadgd", 2, 10);
		Kawa kawa4 = new Kawa("Nescafeasdfsdaf", 3, 10);

		kawaManager.AddKawa(kawa1);
		kawaManager.AddKawa(kawa2);
		kawaManager.AddKawa(kawa3);
		kawaManager.AddKawa(kawa4);
	}

	@Test
	// testowanie polaczenie do bazy danych
	public void checkConnection() {
		assertNotNull(typeManager.getConnection());
		assertNotNull(kawaManager.getConnection());
	}

	@Test
	// sprawdzanie dodawania do bazy kawa (pkt 5)
	public void checkAdding() {
		Kawa kawa = new Kawa("Nescafesadfsa", 1, 10);

		kawaManager.AddKawa(kawa);
		Kawa tmp = kawaManager.searchKawa(5);

		assertEquals(5, tmp.getId());
		assertEquals("Nescafesadfsa", tmp.getName());
		assertEquals(1, tmp.getType());
		assertEquals(10, tmp.getCena());
	}

	@Test
	// sprawdzanie dodawania do bazy type (pkt 5)
	public void checkAddingType() {
		Type type = new Type("jakasTaka");

		typeManager.AddType(type);

		List<Type> typas = typeManager.WriteAllType();

		Type tmp = typas.get(typas.size() - 1);

		assertEquals("jakasTaka", tmp.getNazwa());
	}

	@Test
	// sprawdzanie wyszukiwania kawy
	public void searchChcek() {
		Kawa kawa = new Kawa(1, "Nescafe", 1, 10);
		Kawa tmp = kawaManager.searchKawa(kawa.getId());

		assertEquals(kawa.getId(), tmp.getId());
		assertEquals(kawa.getName(), tmp.getName());
		assertEquals(kawa.getType(), tmp.getType());
		assertEquals(kawa.getCena(), tmp.getCena());
	}

	@Test
	// sprawdzanie wyszukiwania typu
	public void searchChcekType() {
		Type type = new Type(2, "sypana");

		Type tmp = typeManager.searchType(type.getId());

		assertEquals(type.getNazwa(), tmp.getNazwa());
	}

	@Test
	// sprawdzanie update kawy
	public void checkUpdate() {
		Kawa kawa = new Kawa(4, "Saga", 2, 18);

		kawaManager.UpdateKawa(kawa);

		Kawa tmp = kawaManager.searchKawa(kawa.getId());

		assertEquals(kawa.getId(), tmp.getId());
		assertEquals(kawa.getName(), tmp.getName());
		assertEquals(kawa.getType(), tmp.getType());
		assertEquals(kawa.getCena(), tmp.getCena());
	}

	@Test
	// sprawdzanie update typu
	public void checkUpdateType() {
		Type type = new Type(5, "upd");

		typeManager.UpdateType(type);

		Type tmp = typeManager.searchType(type.getId());

		assertEquals(type.getId(), tmp.getId());
		assertEquals(type.getNazwa(), tmp.getNazwa());
	}

	@Test
	// sprawdzanie usuwania kawy (pkt 4)
	public void delCheck() {
		Kawa kawa = new Kawa();
		int id;
		kawa = kawaManager.searchKawa(3);
		id = kawa.getId();
		kawaManager.delKawa(kawa);

		assertNull(kawaManager.searchKawa(id));
	}

	@Test
	// sprawdzanie usuwania typu
	public void delCheckType() {
		Type type = new Type();
		int id;
		type = typeManager.searchType(4);
		id = type.getId();
		typeManager.delType(type);

		assertNull(typeManager.searchType(id));
	}

	@Test
	// pobranie wszystkich typow (pkt 2)
	public void checkAllTypes(){
		Type type1 = new Type("rozpuszczalna");
		Type type2 = new Type("sypana");
		Type type3 = new Type("inna");
		Type type4 = new Type("del");
		
		ArrayList<Type> AllTypes = new ArrayList<Type>();
		AllTypes.add(typeManager.searchType(1));
		AllTypes.add(typeManager.searchType(2));
		AllTypes.add(typeManager.searchType(3));
		AllTypes.add(typeManager.searchType(4));
		
		assertEquals(4, AllTypes.size());
		assert(type1.equals(AllTypes.get(1)));
		assert(type1.equals(AllTypes.get(2)));
		assert(type1.equals(AllTypes.get(3)));
		assert(type1.equals(AllTypes.get(4)));
	}
	
	@Test
	// pobranie wszystkich kaw z jednego typu (pkt 1)
	public void selectAllCheck(){
		Kawa kawa1 = new Kawa("Nescafe", 5, 10);
		Kawa kawa2 = new Kawa("Nescafeasdfsaf", 5, 10);
		kawaManager.AddKawa(kawa1);
		kawaManager.AddKawa(kawa2);
		
		List<Kawa> kawas = kawaManager.selectAll(5);
		System.out.println(kawas.size());
		assertEquals(2, kawas.size());
		assert(kawa1.equals(kawas.get(1)));
		assert(kawa2.equals(kawas.get(2)));
	}
	
	@After
	// czyszczenie bazy
	public void dellAll() {
		kawaManager.delAll();
	}
}