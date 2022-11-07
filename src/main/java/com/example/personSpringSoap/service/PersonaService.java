package com.example.personSpringSoap.service;

import java.util.Collection;

import com.example.personSpringSoap.entity.Person;

public interface PersonaService {
	
	Collection<Person> getall() throws Exception;
	
	void addPerson(Person person) throws Exception;
	 
	Person getPersonById(long id) throws Exception;
	 
    void updatePerson(Person person) throws Exception;
    
    void deletePerson(long id) throws Exception;
}
