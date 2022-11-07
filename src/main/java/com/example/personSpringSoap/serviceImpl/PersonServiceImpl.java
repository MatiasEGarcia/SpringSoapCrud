package com.example.personSpringSoap.serviceImpl;

import java.util.Collection;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public Collection<Person> getall() throws Exception{
		try {
			return dao.findAll();
		}  catch (DataAccessException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}
	
	@Override
	@Transactional(readOnly = false)
	public void addPerson(Person person) throws Exception{
		try {
			dao.save(person);
		}  catch (DataAccessException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}

	@Override
	@Transactional(readOnly = true)
	public Person getPersonById(long id) throws Exception{
		Optional<Person> obj;
		try {
			obj = dao.findById(id);
		}  catch (DataAccessException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }

		if(obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void updatePerson(Person person) throws Exception {
		try {
			dao.save(person);
		}  catch (DataAccessException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
		
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePerson(long id) throws Exception {
		try {
			dao.delete(getPersonById(id));
		}  catch (DataAccessException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}

	

}
