package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcm.training5.Employee;
import com.pcm.training5.PayrollService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class SpringPayrollServiceTest {

	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final int AGE = 22;
	private static final String SALARY = "25000";
	private static final short YEARS_OF_SERVICE = 3;
	private static final BigDecimal EXPECTED_PENSION_FUND = BigDecimal.valueOf(90750);
	
	@Autowired
	private PayrollService payrollService;
	
	@Test
	public void payrollServiceInstanceTest(){
		assertNotNull("PayrollService test should not be empty", payrollService);
	}
	
	@Test
	public void payrollServiceUsingSpringTest() {
		Employee e = new Employee(FIRST_NAME, LAST_NAME, AGE, SALARY);
		BigDecimal pensionFundAmount = payrollService.computePensionFund(e, YEARS_OF_SERVICE);
		assertEquals("Pension fund must be equal to EXPECTED PENSION FUND", pensionFundAmount.compareTo(EXPECTED_PENSION_FUND), 0);
	}
	
	@Test
	public void saveEmployeeUsingPayrollService(){
		Employee e = new Employee("Patrick","Dorado",26,"40000.00");
		e.setDepartment("Information Technology");
		payrollService.addEmployee(e);
		
		Employee e1 = new Employee("Jennifer","Mactal",21,"25000.00");
		e1.setDepartment("Sales");
		payrollService.addEmployee(e1);
		
		List<Employee> employeeList = payrollService.getAllEmployees();
		assertEquals("EmployeeList should have 2 records", employeeList.size(), 2);
	}
	
}
