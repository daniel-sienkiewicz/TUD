package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Person;

public interface PersonRepository extends CrudRepository<Person, ObjectId> {

	// pobranie osoby po ID
	Person findById(ObjectId id);

	// pobranie osoby po imieniu
	List<Person> findByName(String name);
}