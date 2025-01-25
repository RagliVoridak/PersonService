package telran.java55.person.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java55.person.dao.PersonRepository;
import telran.java55.person.dto.AddressDto;
import telran.java55.person.dto.PersonDto;
import telran.java55.person.dto.exception.PersonNotFoundException;
import telran.java55.person.model.Address;
import telran.java55.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
	final PersonRepository personRepository;
	final ModelMapper modelMapper;
	
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

	@Override
	public boolean updateName(Integer id, String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<PersonDto> findByAges(int min, int max){
		LocalDate minDate = LocalDate.now().minusYears(max);
		LocalDate maxDate = LocalDate.now().minusYears(min);
		return personRepository.findByBirthDateBetween(minDate, maxDate)
				.stream()
				.map(person -> modelMapper.map(person, PersonDto.class))
				.toList();
	}

	@Override
	public List<PersonDto> findByCity(String city) {
		return personRepository.findByAddressCity(city)
				.stream()
				.map(person -> modelMapper.map(person, PersonDto.class))
				.toList();
	}

	@Override
	public List<PersonDto> findByName(String name) {
		return personRepository.findByName(name)
				.stream()
				.map(person -> modelMapper.map(person, PersonDto.class))
				.toList();
	}

	@Override
	public boolean updateAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow();
		Address address = modelMapper.map(addressDto, Address.class);
		person.setAddress(address);
		personRepository.save(person);
		return true;
	}

	@Override
	public boolean deletePerson(Integer id) {
		if(!personRepository.existsById(id)) {
			return false;
		}
		personRepository.deleteById(id);
		return true;
	}
}
