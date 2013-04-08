package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class JPATest {
	
	@Autowired
	private EmployeeDAO dao;
	
	@Test
	public void employeeScoreTest() {
		
		Employee e = new Employee("Cathy","Belen",21,"10000.00");
		Employee e1 = new Employee("Cathy","Belen",21,"10000.00");
		Employee e2 = new Employee("Cathy","Belen",21,"10000.00");
		Employee e3 = new Employee("Cathy","Belen",21,"10000.00");
		
		//added since required to set the department... april 8 2013
		e.setDepartment("IT");
		e1.setDepartment("IT");
		e2.setDepartment("IT");
		e3.setDepartment("IT");
		//end
		
		e.setScore(100);
		e1.setScore(90);
		e2.setScore(80);
		e3.setScore(70);
		
		dao.persist(e);
		dao.persist(e1);
		dao.persist(e2);
		dao.persist(e3);
		
		List<Employee> above80 = dao.findByScoreRange(81,100);
		assertEquals(above80.size(),2);
	}
	
	@Test
	public void findByDepartmentTest(){
		
		Employee e = new Employee("Cathy","Belen",21,"10000.00");
		e.setDepartment("Information Technology");
		dao.persist(e);
		
		Employee e1 = new Employee("Cathy","Belen",21,"10000.00");
		e1.setDepartment("Sales");
		dao.persist(e1);
		
		Employee e2 = new Employee("Cathy","Belen",21,"10000.00");
		e2.setDepartment("Infrastracture");
		dao.persist(e2);
		
		Employee e3 = new Employee("Cathy","Belen",21,"10000.00");
		e3.setDepartment("Marketing");
		dao.persist(e3);
		
		String department = "In";
		List<Employee> inEmployees = dao.findEmployeeByDepartment(department);
		assertEquals("The department that starts with In should have 2 employees", inEmployees.size(), 2);	
	}
}
