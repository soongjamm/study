package lambda;

import java.time.LocalDate;

public class Person {
	public enum Sex {
		Male, Female
	}

	String name;
	LocalDate birthDate;
	Sex gender;
	String emailAddress;

	public int getAge(){
		// not implemented
		return 11;
	}

	public void printPerson() {
		System.out.println(this.name + " " + this.gender);
	}

	public Person(String name, LocalDate birthDate, Sex gender, String emailAddress) {
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.emailAddress = emailAddress;
	}
}
