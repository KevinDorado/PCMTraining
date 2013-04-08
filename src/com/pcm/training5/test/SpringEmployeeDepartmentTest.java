package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcm.dao.DepartmentDAO;
import com.pcm.service.EmployeeDepartmentService;
import com.pcm.training5.Department;
import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeDAO;
import com.pcm.training5.EmployeeDepartment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class SpringEmployeeDepartmentTest {

	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final int AGE = 22;
	private static final String SALARY = "100000";
	
	@Autowired
	private EmployeeDepartmentService employeeDepartmentService;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Test
	public void testEmployeeDAO(){
		assertNotNull("EmployeeDepartmentDAO should not be null", employeeDepartmentService);
		assertNotNull("EmployeeDAO should not be null", employeeDAO);
		assertNotNull("DepartmentDAO should not be null", departmentDAO);
	}
	
	@Test
	public void saveEmployeeWithDeparment() {
		Employee employee = new Employee(FIRST_NAME, LAST_NAME, AGE, SALARY);
		employeeDAO.persist(employee);
		
		Department department = new Department("IT");
		Department department2 = new Department("SALES");
		Department department3 = new Department("MARKETING");
		
		departmentDAO.persist(department);
		departmentDAO.persist(department2);
		departmentDAO.persist(department3);
		
		List<Department> departmentList = departmentDAO.findAll();
		
		assertEquals("Department should have 3 records", departmentList.size(), 3);
		
		employeeDepartmentService.setEmployeeDepartments(employee, department, department2, department3);
		
		List<EmployeeDepartment> employeeDepartmentList = employeeDepartmentService.getDepartmentsOfEmployee(employee.getId());
		
		assertEquals("Employee should have 3 departments on EmployeeDepartmentTable", employeeDepartmentList.size(), 3);
		
	}

}
