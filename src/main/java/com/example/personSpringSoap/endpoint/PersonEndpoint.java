package com.example.personSpringSoap.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.example.person.AddPersonRequest;
import org.example.person.AddPersonResponse;
import org.example.person.DeletePersonRequest;
import org.example.person.DeletePersonResponse;
import org.example.person.GetAllPersonResponse;
import org.example.person.GetPersonByIdRequest;
import org.example.person.GetPersonByIdResponse;
import org.example.person.PersonInfo;
import org.example.person.Status;
import org.example.person.UpdatePersonRequest;
import org.example.person.UpdatePersonResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.personSpringSoap.entity.Person;
import com.example.personSpringSoap.service.PersonaService;

@Endpoint
public class PersonEndpoint {

	private static final String NAMESPACE_URI = "http://www.example.org/person";
	
	@Autowired
	private PersonaService perService;
	
	
	@PayloadRoot(namespace = NAMESPACE_URI ,localPart = "addPersonRequest")
	@ResponsePayload
	public AddPersonResponse addPerson(@RequestPayload AddPersonRequest request) throws Exception {
		AddPersonResponse response = new AddPersonResponse();
		Status status= new Status();
		Person newPerson = new Person();
		BeanUtils.copyProperties(request.getPersonInfo(), newPerson);
		newPerson.setDateOfBirth(request.getPersonInfo().getDateOfBirth().toGregorianCalendar().getTime());
		perService.addPerson(newPerson);
		status.setStatus("SUCCESS");
		status.setMessage("Person Added Successfully");
		response.setStatus(status);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI , localPart = "getPersonByIdRequest")
	@ResponsePayload
	public GetPersonByIdResponse getPersonById(@RequestPayload GetPersonByIdRequest request) throws Exception {
		GetPersonByIdResponse response = new GetPersonByIdResponse();
		PersonInfo personInfo = new PersonInfo();
		BeanUtils.copyProperties(perService.getPersonById(request.getPersonId()), personInfo);
		response.setPersonInfo(personInfo);
		return response;
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI,localPart = "updatePersonRequest")
	@ResponsePayload
	public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) throws Exception {
		UpdatePersonResponse response = new UpdatePersonResponse();
		Person person = new Person();
		Status status = new Status();
		
		BeanUtils.copyProperties(request.getPersonInfo(), person);
		person.setDateOfBirth(request.getPersonInfo().getDateOfBirth().toGregorianCalendar().getTime());
		perService.updatePerson(person);
		status.setStatus("SUCCESS");
		status.setMessage("Person Updated Successfully");
		response.setStatus(status);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI , localPart = "deletePersonRequest")
	@ResponsePayload
	public DeletePersonResponse deletePerson(@RequestPayload DeletePersonRequest request) throws Exception {
		DeletePersonResponse response = new DeletePersonResponse();
		Status status = new Status();
		
		perService.deletePerson(request.getPersonId());
		status.setStatus("SUCCESS");
		status.setMessage("Person Deleted Successfully");
		response.setStatus(status);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI , localPart = "getAllPersonRequest")
	@ResponsePayload
	public GetAllPersonResponse getAllPerson() throws Exception {
		GetAllPersonResponse response = new GetAllPersonResponse();
		List<PersonInfo> personInfoList = new ArrayList<>();
		List<Person> personList = (List<Person>) perService.getall();
		for(int i=0; i<personList.size();i++) {
			PersonInfo perInfo = new PersonInfo();
			BeanUtils.copyProperties(personList.get(i),perInfo);
			personInfoList.add(perInfo);
		}
		response.getPersonInfo().addAll(personInfoList); //I first retrieve the list inside peopleInfo and then add
		return response;
		
	}
	
	
	
}
