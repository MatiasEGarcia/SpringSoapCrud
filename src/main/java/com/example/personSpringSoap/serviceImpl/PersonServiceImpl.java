package com.example.personSpringSoap.serviceImpl;

import java.util.Collection;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.personSpringSoap.dao.PersonDao;
import com.example.personSpringSoap.entity.Person;
import com.example.personSpringSoap.service.PersonaService;

@Service
public class PersonServiceImpl implements PersonaService{

	@Autowired
	private PersonDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public Collection<Person> getall() {
		return dao.findAll();
	}
	
	@Override
	@Transactional(readOnly = false)
	public void addPerson(Person person) {
		dao.save(person);
	}

	@Override
	@Transactional(readOnly = true)
	public Person getPersonById(long id) {
		Optional<Person> obj = dao.findById(id);
      
		if(obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void updatePerson(Person person) {
		dao.save(person);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePerson(long id) {
		dao.delete(getPersonById(id));
	}

	

}
