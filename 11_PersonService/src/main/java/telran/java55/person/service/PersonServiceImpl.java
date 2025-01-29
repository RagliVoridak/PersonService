package telran.java55.person.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java55.person.dao.PersonRepository;
import telran.java55.person.dto.AddressDto;
import telran.java55.person.dto.ChildDto;
import telran.java55.person.dto.CityPopulationDto;
import telran.java55.person.dto.EmployeeDto;
import telran.java55.person.dto.PersonDto;
import telran.java55.person.dto.exception.PersonNotFoundException;
import telran.java55.person.model.Address;
import telran.java55.person.model.Child;
import telran.java55.person.model.Employee;
import telran.java55.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
	final PersonRepository personRepository;
	final ModelMapper modelMapper;
	
	@Transactional
    @Override
    public boolean addPerson(PersonDto personDto) {
    	if(personRepository.existsById(personDto.getId())) {
    		return false;
    	}
    	personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }
	
    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional
	@Override
	public boolean updateName(Integer id, String name) {
			Person person = personRepository.findById(id).orElseThrow(() -> 
			new PersonNotFoundException());
			person.setName(name);
//			personRepository.save(person);
			
			return true;
	}
    
    @Transactional(readOnly = true)
	@Override
	public List<PersonDto> findByAges(int min, int max){
		LocalDate minDate = LocalDate.now().minusYears(max);
		LocalDate maxDate = LocalDate.now().minusYears(min);
		return personRepository.findByBirthDateBetween(minDate, maxDate)
				.stream()
				.map(person -> modelMapper.map(person, PersonDto.class))
				.toList();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<PersonDto> findByCity(String city) {
		return personRepository.findByAddressCity(city)
				.stream()
				.map(person -> {
					if(person instanceof Child) {
						return modelMapper.map(person, ChildDto.class);
					} else if(person instanceof Employee) {
						return modelMapper.map(person, EmployeeDto.class);
					} else {
						return modelMapper.map(person, PersonDto.class);
					}
				})
				.toList();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<PersonDto> findByName(String name) {
		return personRepository.findByName(name)
				.stream()
				.map(person -> modelMapper.map(person, PersonDto.class))
				.toList();
	}
	
	@Transactional
	@Override
	public boolean updateAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow();
		Address address = modelMapper.map(addressDto, Address.class);
		person.setAddress(address);
		personRepository.save(person);
		return true;
	}
	
	@Transactional
	@Override
	public boolean deletePerson(Integer id) {
		if(!personRepository.existsById(id)) {
			return false;
		}
		personRepository.deleteById(id);
		return true;
	}
	
	@Override
	public Iterable<CityPopulationDto> getCitiesPopulation(){
		return personRepository.getCitiesPopulaton();
	}

	@Override
	public void run(String... args) throws Exception {
		if(personRepository.count() == 0) {
			Person person = new Person(1000, "John", LocalDate.of(1988, 3, 13), 
					new Address("Tel Aviv", "Ben Gvirol", 81));
			Child child = new Child(2000, "Mosche", LocalDate.of(2022, 1, 27),
					new Address("Ashkelon", "Bar KOhva", 21), "SHalom");
			Employee employee = new Employee(3000, "Sarah", LocalDate.of(2000, 11, 23),
					new Address("Rehovot", "Herzl", 2), "Motorola", 25_000);
			personRepository.save(person);
			personRepository.save(child);
			personRepository.save(employee);
		}
	}

	@Override
	public Iterable<EmployeeDto> findEmployeeBySalary(int min, int max) {
		return personRepository.findBySalaryBetween(min, max)
				.stream()
				.map(employee -> modelMapper.map(employee,  EmployeeDto.class))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	@Override
	public Iterable<ChildDto> findAllChildrens() {
		return personRepository.findAllChildren()
				.stream()
				.map(child -> modelMapper.map(child, ChildDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
        personRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public ChildDto addChild(ChildDto childDto) {
		Child child = modelMapper.map(childDto, Child.class);
		personRepository.save(child);
		return modelMapper.map(child, ChildDto.class);
	}
}
