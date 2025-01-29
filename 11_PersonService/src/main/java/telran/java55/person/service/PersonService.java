package telran.java55.person.service;

import java.util.List;

import telran.java55.person.dto.AddressDto;
import telran.java55.person.dto.ChildDto;
import telran.java55.person.dto.CityPopulationDto;
import telran.java55.person.dto.EmployeeDto;
import telran.java55.person.dto.PersonDto;

public interface PersonService {

	boolean addPerson(PersonDto personDto); 

	PersonDto findPersonById(Integer id);
	
	List<PersonDto> findByCity(String city);
	
	List<PersonDto> findByAges(int min, int max);

	boolean updateName(Integer id, String name);

	List<PersonDto> findByName(String name);

	boolean updateAddress(Integer id, AddressDto addressDto);

	boolean deletePerson(Integer id);

	Iterable<CityPopulationDto> getCitiesPopulation();

	Iterable<EmployeeDto> findEmployeeBySalary(int min, int max);

	Iterable<ChildDto> findAllChildrens();

	EmployeeDto addEmployee(EmployeeDto employeeDto);

	ChildDto addChild(ChildDto childDto);

	

	
}
