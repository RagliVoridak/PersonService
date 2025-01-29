package telran.java55.person.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java55.person.dao.PersonRepository;
import telran.java55.person.dto.AddressDto;
import telran.java55.person.dto.ChildDto;
import telran.java55.person.dto.EmployeeDto;
import telran.java55.person.dto.PersonDto;
import telran.java55.person.model.Employee;
import telran.java55.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
	final PersonService personService;
	
	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}
	
	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}
	
	@PutMapping("/{id}/name/{name}")
	public boolean upadteName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updateName(id, name);
	}
	
	@GetMapping("/city/{city}")
	public List<PersonDto> findByCity(@PathVariable String city){
		return personService.findByCity(city);
	}
	
	@GetMapping("/ages/{min}/{max}")
	public List<PersonDto> findByAges(@PathVariable int min, @PathVariable int max){
		return personService.findByAges(min, max);
	}
	
	@GetMapping("/name/{name}")
	public List<PersonDto> findByName(@PathVariable String name){
		return personService.findByName(name);
	}
	
	@PutMapping
	public boolean updateAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
		return personService.updateAddress(id, addressDto);
	}
	
	@DeleteMapping
	public boolean deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}
	
	@PostMapping("/employee")
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        return personService.addEmployee(employeeDto);
    }
	
	@GetMapping("/salary/{min}/{max}")
	public Iterable<EmployeeDto> findEmployeeBySalary(@PathVariable int min, @PathVariable int max) {
		return personService.findEmployeeBySalary(min, max);
	}

	@PostMapping("/child")
    public ChildDto addChild(@RequestBody ChildDto childDto) {
        return personService.addChild(childDto);
    }
	
	
	@GetMapping("/childrens")
	public Iterable<ChildDto> findAllChildrens() {
		return personService.findAllChildrens();
	}
}
