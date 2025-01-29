package telran.java55.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.java55.person.dto.CityPopulationDto;
import telran.java55.person.model.Child;
import telran.java55.person.model.Employee;
import telran.java55.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	Optional<Person> findByBirthDateBetween(LocalDate minDate, LocalDate maxDate);

	Optional<Person> findByAddressCity(String city);

	Optional<Person> findByName(String name);
	
	@Query("SELECT new telran.java55.person.dto.CityPopulationDto(p.address.city, COUNT(p)) FROM Person p GROUP BY p.address.city")
	List<CityPopulationDto> getCitiesPopulaton();
	
	List<Employee> findBySalaryBetween(int min, int max);
	
	@Query("SELECT c FROM Person c WHERE TYPE(c) = Child")
	List<Child> findAllChildren();
}
