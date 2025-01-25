package telran.java55.person.service;

import java.util.List;

import telran.java55.person.dto.AddressDto;
import telran.java55.person.dto.PersonDto;

public interface PersonService {

	public boolean addPerson(PersonDto personDto); 

	public PersonDto findPersonById(Integer id);
	
	public List<PersonDto> findByCity(String city);
	
	List<PersonDto> findByAges(int min, int max);

	public boolean updateName(Integer id, String name);

	public List<PersonDto> findByName(String name);

	public boolean updateAddress(Integer id, AddressDto addressDto);

	public boolean deletePerson(Integer id);

	

	
}
