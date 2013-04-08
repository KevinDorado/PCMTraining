package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.pcm.training5.Employee;
import com.pcm.training5.President;
import com.pcm.training5.PresidentException;

public class PresidentExceptionTest {

	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final int AGE = 22;
	private static final String SALARY = "100000";

	@Test
	public void presidentExceptionSubordinatesTest(){
		
		President boss1 = new President("Malou", "Jopillo", 50, "100000");
		Employee e1 = new Employee("Mark", "Aban", AGE, "35000");
		Employee e2 = new Employee("Ray Vincent", "Trinidad", AGE, "20000");
		Employee e3 = new Employee("Patrick", "Ceniza", AGE, "15000");
		Employee e4 = new Employee("Aiko", "Agapito", AGE, SALARY);
		Employee e5 = new Employee("James", "Aquino", AGE, "35000");
		Employee e6 = new Employee("Marize", "Reodica", AGE, "20000");
		Employee e7 = new Employee("Troy", "Taguiang", AGE, "15000");
		Employee e8 = new Employee(FIRST_NAME, LAST_NAME, AGE, SALARY);
		Employee e9 = new Employee("Dominique", "Aquino", AGE, SALARY);
		Employee e10 = new Employee("Jaycee", "Napa", AGE, SALARY);
		Employee e11 = new Employee("Arnold", "Reyes", AGE, SALARY);
		
		
		assertEquals("boss1 should not have subordinates yet", boss1.getSubordinates().size(), 0);
		try{
			boss1.setBossOfEmployees(boss1, e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11);
			fail("Should fail.. Subordinates of boss cannot be greater than 10");
		}catch(PresidentException e){
			assertEquals("Subordinates cannot be greater than 10",e.getMessage(), "Subordinates cannot be greater than 10");
		}	
	}
	
	@Test
	public void presidentSalaryOverflowTest() {
		
		President boss1 = new President("Malou", "Jopillo", 50, "150000");
		Employee e1 = new Employee("Mark", "Aban", AGE, "10000");
		Employee e2 = new Employee("Ray Vincent", "Trinidad", AGE, "10000");
		Employee e3 = new Employee("Patrick", "Ceniza", AGE, "10000");
		Employee e4 = new Employee("Aiko", "Agapito", AGE, "10000");
		Employee e5 = new Employee("James", "Aquino", AGE, "10000");
		Employee e6 = new Employee("Marize", "Reodica", AGE, "10000");
		assertEquals("boss1 should not have subordinates yet", boss1.getSubordinates().size(), 0);
		try{
			boss1.setBossOfEmployees(boss1, e1,e2,e3,e4,e5,e6);
			fail("Should fail.. Subordinates of boss cannot be greater than 10");
		}catch(PresidentException e){
			assertEquals("Salary of the president is too high",e.getMessage(), "Salary of the president is too high");
		}	
	}
}
