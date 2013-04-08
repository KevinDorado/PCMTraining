package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeDAO;
import com.pcm.training5.PayrollService;
import com.pcm.training5.President;

public class BonusTest {

	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final String SALARY = "100000";
	private static final String EMPLOYEE_BONUS = "24000";
	private static final String PRESIDENT_BONUS = "600000";
	private static final int AGE = 22;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Test
	public void shouldReturnEmployeeBonusAmount(){
		PayrollService p = PayrollService.getInstance();
		Assert.assertNotNull("Payroll Service cannot be null", p);
		Employee e = new Employee(FIRST_NAME, LAST_NAME, AGE, "10000");
		String bonusAmount = p.getBonus(e);
		assertEquals("Compute the bonuse of the employee",Double.valueOf(bonusAmount), Double.valueOf(EMPLOYEE_BONUS), 0.0000001);
	}
	
	@Test
	public void shouldReturnPresidentBonusAmount(){
		PayrollService p = PayrollService.getInstance();
		Assert.assertNotNull("Payroll Service cannot be null", p);
		President president = new President(FIRST_NAME, LAST_NAME, AGE, SALARY);
		String bonusAmount = p.getBonus(president);
		assertEquals("Compute the bonuse of the employee",Double.valueOf(bonusAmount), Double.valueOf(PRESIDENT_BONUS), 0.0000001);
	}

	
	
}
