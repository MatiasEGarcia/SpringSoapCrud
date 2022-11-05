package com.example.personSpringSoap.endpoint;

import org.example.person.AddPersonRequest;
import org.example.person.AddPersonResponse;
import org.example.person.DeletePersonRequest;
import org.example.person.DeletePersonResponse;
import org.example.person.GetPersonByIdRequest;
import org.example.person.GetPersonResponse;
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
	public AddPersonResponse addPerson(@RequestPayload AddPersonRequest request) {
		AddPersonResponse response = new AddPersonResponse();
		Status status= new Status();
		Person newPerson = new Person();
		
		BeanUtils.copyProperties(request.getPersonInfo(), newPerson);
		perService.addPerson(newPerson);
		status.setStatus("SUCCESS");
		status.setMessage("Person Added Successfully");
		response.setStatus(status);
		return response;
	}
	
	@PayloadRoot(namespace = "NAMESPACE_URI" , localPart = "getPersonByIdRequest")
	@ResponsePayload
	public GetPersonResponse getPerson(@RequestPayload GetPersonByIdRequest request) {
		GetPersonResponse response = new GetPersonResponse();
		PersonInfo personInfo = new PersonInfo();
		BeanUtils.copyProperties(perService.getPersonById(request.getPersonId()), personInfo);
		response.setPersonInfo(personInfo);
		return response;
		
	}
	
	@PayloadRoot(namespace = "NAMESPACE_URI",localPart = "updatePersonRequest")
	@ResponsePayload
	public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) {
		UpdatePersonResponse response = new UpdatePersonResponse();
		Person person = new Person();
		Status status = new Status();
		
		BeanUtils.copyProperties(request.getPersonInfo(), person);
		perService.updatePerson(person);
		status.setStatus("SUCCESS");
		status.setMessage("Person Updated Successfully");
		response.setStatus(status);
		return response;
	}
	
	@PayloadRoot(namespace = "NAMESPACE_URI" , localPart = "deletePersonRequest")
	@ResponsePayload
	public DeletePersonResponse deletePerson(@RequestPayload DeletePersonRequest request) {
		DeletePersonResponse response = new DeletePersonResponse();
		Status status = new Status();
		
		perService.deletePerson(request.getPersonId());
		status.setStatus("SUCCESS");
		status.setMessage("Person Deleted Successfully");
		response.setStatus(status);
		
		return response;
	}
	
	
	
}
