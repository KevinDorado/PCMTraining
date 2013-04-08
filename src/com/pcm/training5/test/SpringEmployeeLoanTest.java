package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcm.dao.LoanDAO;
import com.pcm.service.EmployeeLoanService;
import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeDAO;
import com.pcm.training5.EmployeeLoan;
import com.pcm.training5.Loan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class SpringEmployeeLoanTest {
	
	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final int AGE = 22;
	private static final String SALARY = "100000";
	private static final String LOAN_AMOUNT = "100000";
	
	@Autowired
	private EmployeeLoanService employeeLoanService;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private LoanDAO loanDAO;
	
	@Test
	public void testEmployeeLoanService(){
		assertNotNull("employee service should not be null", employeeLoanService);
		assertNotNull("employeeDAO should not be null", employeeDAO);
		assertNotNull("loan DAO cannot be null", loanDAO);
	}
	
	@Test
	public void employeeLoanTest(){
		Loan loan = new Loan("SSS Loan");
		loanDAO.persist(loan);
		
		List<Loan> loanList = loanDAO.findAll();
		assertEquals("loan should have 1 record", loanList.size(), 1);
		
		Employee employee = new Employee(FIRST_NAME, LAST_NAME, AGE, SALARY);
		employeeDAO.persist(employee);
		
		EmployeeLoan employeeLoan = new EmployeeLoan(employee, loan, LOAN_AMOUNT);
		
		employeeLoanService.saveLoan(employeeLoan);
		
		int id = employee.getId();

		List<Employee> employeeWithLoan = employeeLoanService.getEmployee(id);
		
		BigDecimal loanAmount = new BigDecimal(LOAN_AMOUNT);
		assertTrue("Employee Loan amount should be equal ", employeeLoan.getAmount().equals(loanAmount));
		assertEquals("employeeWithLoan should have one record", employeeWithLoan.size(), 1);
	}
}
