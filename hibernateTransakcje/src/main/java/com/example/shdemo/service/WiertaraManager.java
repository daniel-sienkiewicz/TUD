package com.example.shdemo.service;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Wiertara;

@Component
@Transactional
public class WiertaraManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int addWiertara(Wiertara wiertara) {
		return (Integer) sessionFactory.getCurrentSession().save(wiertara);
	}
	
	public Wiertara getWiertara(int id) {
		return (Wiertara) sessionFactory.getCurrentSession()
				.get(Wiertara.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Wiertara> getWiertaraSome(int price, int yop) {
		return (ArrayList<Wiertara>) sessionFactory.getCurrentSession()
				.getNamedQuery("wiertara.some").setInteger("price", price).setInteger("yop", yop)
				.list();
	}
}