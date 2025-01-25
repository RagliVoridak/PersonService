package telran.java55.person.dao;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java55.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	Optional<Person> findByBirthDateBetween(LocalDate minDate, LocalDate maxDate);

	Optional<Person> findByAddressCity(String city);

	Optional<Person> findByName(String name);

}
