package telran.java55.person.dto;

import java.time.LocalDate;



import lombok.Getter;
import telran.java55.person.model.Address;

@Getter
public class PersonDto {
	Integer id;
	String name;
	LocalDate birthDate;
	Address address;
	
}
