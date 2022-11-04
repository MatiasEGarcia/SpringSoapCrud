package com.example.personSpringSoap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.personSpringSoap.entity.Person;

public interface PersonDao extends JpaRepository<Person, Long> {

}
