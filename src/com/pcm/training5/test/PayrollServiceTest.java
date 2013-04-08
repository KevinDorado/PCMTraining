package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

import com.pcm.training5.Employee;
import com.pcm.training5.PayrollService;
import com.pcm.training5.President;
import com.pcm.training5.PresidentException;

public class PayrollServiceTest {
	
	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final int AGE = 22;
	private static final String SALARY = "25000";
	private static final short YEARS_OF_SERVICE = 3;
	private static final BigDecimal EXPECTED_PENSION_FUND = BigDecimal.valueOf(90750);
	
	@Test
	public void testPayrollServiceSingleton(){
		PayrollService payrollService = PayrollService.getInstance();
		PayrollService payrollService1= PayrollService.getInstance();
		assertEquals("PayrollService should have the same instance", payrollService, payrollService1);
	}
	
	@Test
	public void shouldReturnComputedPensionFund(){
		PayrollService payrollService = PayrollService.getInstance();
		Employee e = new Employee(FIRST_NAME, LAST_NAME, AGE, SALARY);
		BigDecimal pensionFundAmount = payrollService.computePensionFund(e, YEARS_OF_SERVICE);
		assertEquals("Pension fund must be equal to EXPECTED PENSION FUND", pensionFundAmount.compareTo(EXPECTED_PENSION_FUND), 0);
	}
	
	@Test
	public void shouldReceiveGoldenParachuteAmount() throws PresidentException{
		PayrollService payrollService = PayrollService.getInstance();
		President p = new President(FIRST_NAME, LAST_NAME, AGE, "150000");
		assertTrue("Must be an instance of president", p instanceof President);
		p.fire();
		BigDecimal goldenParachuteAmount = payrollService.computeGoldenParachuteAmount(p);
		assertEquals("Golden Parachute Amount must be equal to 2700000", goldenParachuteAmount.compareTo(BigDecimal.valueOf(2700000)), 0);
	}
	
	@Test
	public void presidentIsNotYetFired(){
		PayrollService payrollService = PayrollService.getInstance();
		President e1 = new President(FIRST_NAME, LAST_NAME, AGE, "150000");
		e1.hire();
		try{
			payrollService.computeGoldenParachuteAmount(e1);
			fail("Should throw and exception that president is not yet fired");
		}catch(PresidentException e){
			assertEquals("President is not yet fired", e.getMessage(), "The president is not yet fired");
		}
	}
	
	@Test
	public void shouldReturnEmployeeSalary(){
		Employee e = new Employee(FIRST_NAME, LAST_NAME, AGE, "10000");
		e.getPayroll().setEmployee(e);
		assertEquals("employee in payroll must be the same with e", e, e.getPayroll().getEmployee());
		BigDecimal bonusAmount = new BigDecimal(e.getPayroll().getSalary());
		assertEquals("Compute the bonus of the employee",bonusAmount.compareTo(BigDecimal.valueOf(10000)), 0);
	}
	
	@Test
	public void shouldReturnEmployeeAnnualSalary(){
		Employee e = new Employee(FIRST_NAME, LAST_NAME, AGE, "10000");
		e.getPayroll().setEmployee(e);
		assertEquals("employee in payroll must be the same with e", e, e.getPayroll().getEmployee());
		BigDecimal annualSalary = new BigDecimal(e.getPayroll().getAnnualSalary(e));
		assertEquals("Compute the bonus of the employee", annualSalary.compareTo(BigDecimal.valueOf(120000)), 0);
	}
}
