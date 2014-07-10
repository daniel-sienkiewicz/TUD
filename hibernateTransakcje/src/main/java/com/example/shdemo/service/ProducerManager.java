package com.example.shdemo.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Producer;

@Component
@Transactional
public class ProducerManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int addProducer(Producer producer) {
		return (Integer) sessionFactory.getCurrentSession().save(producer);
	}
	
	public void delProducer(Producer producer){
		sessionFactory.getCurrentSession().delete(producer);
	}
	
	public Producer getProducer(int id) {
		return (Producer) sessionFactory.getCurrentSession()
				.get(Producer.class, id);
	}
}