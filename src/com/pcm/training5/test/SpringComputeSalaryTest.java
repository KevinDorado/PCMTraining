package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcm.training5.Employee;
import com.pcm.training5.PayrollService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class SpringComputeSalaryTest {

	private static final short YEARS_OF_SERVICE = 3;
	private static final BigDecimal EXPECTED_PENSION_FUND = BigDecimal.valueOf(90750);
	
	@Autowired
	private PayrollService payrollService;
	
	@Test
	public void computeSalaryTest(){
		Employee e = new Employee("Kevin","Dorado",21,"25000.00");
		e.setDepartment("Information Technology");
		payrollService.addEmployee(e);
		
		Employee employee = payrollService.findByFirstAndLastName("Kevin", "Dorado");
		assertNotNull("employee should not be null", employee);
		assertEquals("Employee firstName should be Kevin", employee.getFirstName(), "Kevin");
		BigDecimal pensionFundAmount = payrollService.computePensionFund(employee, YEARS_OF_SERVICE);
		assertEquals("Pension fund must be equal to EXPECTED PENSION FUND", pensionFundAmount.compareTo(EXPECTED_PENSION_FUND), 0);
		
		
		//changing the salary of employee
		employee.getPayroll().setSalary("30000");
		payrollService.addEmployee(employee);
		employee = payrollService.findByFirstAndLastName("Kevin", "Dorado");
		
		BigDecimal newSalary = new BigDecimal(employee.getPayroll().getSalary());
		
		assertEquals("The salary of the employee should now be 30000", newSalary.compareTo(BigDecimal.valueOf(30000)), 0);
		
	}
}
