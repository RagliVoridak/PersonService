package telran.java55.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Person {
	@Id
	Integer id;
	@Setter
	String name;
	LocalDate birthDate;
	@Setter
	@Embedded
	Address address;
}
