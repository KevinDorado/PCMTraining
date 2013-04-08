package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class SpringEmployeeFactoryTest {

	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final int AGE = 22;
	private static final String SALARY = "100000";
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private EmployeeFactory employeeFactory;
	
	@Test
	public void test() {
		assertNotNull("EmployeeFactory should not be null", employeeFactory);
	}
	
	@Test
	public void employeeCreationTest() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		@SuppressWarnings("unchecked")
		Employee employee = employeeFactory.createEmployee(FIRST_NAME, LAST_NAME, AGE, SALARY, Employee.class);
		assertNotNull("employee should not be null", employee);
		assertEquals("First name should be equal ", employee.getFirstName(), "Kevin");
	}
	

}
