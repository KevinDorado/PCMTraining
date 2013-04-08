package com.pcm.training5.test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeFactory;

public class EmployeeFactoryTest {

	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final int AGE = 22;
	private static final String SALARY = "100000";
	
	@Test
	public void employeeFactorySingletonTest() {
		EmployeeFactory<?> e = EmployeeFactory.getInstance();
		EmployeeFactory<?> e1 = EmployeeFactory.getInstance();
		assertEquals("e and e1 should be equal", e, e1);
	}
	
	@Test
	public void employeeCreationTest() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		@SuppressWarnings("unchecked")
		EmployeeFactory<Employee> e = EmployeeFactory.getInstance();
		Employee employee = e.createEmployee(FIRST_NAME, LAST_NAME, AGE, SALARY, Employee.class);
		assertNotNull("employee should not be null", employee);
	}
	

}
