package telran.java55.person.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java55.person.model.Address;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
	Integer id;
	String name;
	LocalDate birthDate;
	Address address;
	
}
