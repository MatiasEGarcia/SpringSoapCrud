package com.example.personSpringSoap.service;

import java.util.Collection;

import com.example.personSpringSoap.entity.Person;

public interface PersonaService {
	
	Collection<Person> getall();
	
	void addPerson(Person person);
	 
	Person getPersonById(long id);
	 
    void updatePerson(Person person);
    
    void deletePerson(long id);
}
