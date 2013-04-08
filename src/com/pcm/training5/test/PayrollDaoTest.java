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
import com.pcm.training5.Payroll;
import com.pcm.training5.PayrollDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class PayrollDaoTest {
	
	@Autowired
	private PayrollDAO payrollDAO;

	@Autowired
	private EmployeeDAO employeeDAO;
		
	@Test
	public void persistPayroll(){

		Payroll payroll = new Payroll("10000");
		Employee e = new Employee("Cathy","Belen",21,"10000.00");
		e.setDepartment("Information Technology");
		employeeDAO.persist(e);
		payroll.setEmployee(e);
		payrollDAO.persist(payroll);
		List<Payroll> payrollList = payrollDAO.findAll();
		assertEquals("Payroll should have 1 record", payrollList.size(), 1);
		
		List<Employee> employeeList = employeeDAO.findAll();
		assertEquals("Employee should also have 1 record", employeeList.size(), 1);
		
	}
	
/*	@Test
	public void checkPayrollWhenEmployeeIsPersist() {
		Employee e = new Employee("Cathy","Belen",21,"10000.00");
		e.setDepartment("Information Technology");
		employeeDAO.persist(e);
		
		List<Employee> employeeList = employeeDAO.findAll();
	 
		assertEquals("Employee should have 1 record", employeeList.size(), 1);
		
		List<Payroll> payrollList = payrollDAO.findAll();
		assertEquals("Payroll should also have 1 record", payrollList.size(), 1);
	}*/
}
