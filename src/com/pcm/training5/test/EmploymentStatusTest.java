package com.pcm.training5.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.pcm.training5.Employee;
import com.pcm.training5.EmploymentStatus;
import com.pcm.training5.President;

public class EmploymentStatusTest {

	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final int AGE = 22;
	private static final String SALARY = "100000";
	private static final BigDecimal GOLDEN_PARACHUTE_AMOUNT = new BigDecimal(8000000);
	private static final BigDecimal SEVERANCE_PAY = new BigDecimal(500000);
	
	
	@Test
	public void employmentStatusTest(){
		Employee p = new President(FIRST_NAME, LAST_NAME, AGE, SALARY);
		Assert.assertTrue("Presidet gets a lot", p.fire().equals(GOLDEN_PARACHUTE_AMOUNT));
		Assert.assertEquals(p.getStatus(), EmploymentStatus.FIRED);
		
		Employee e = new Employee(FIRST_NAME, LAST_NAME, AGE, SALARY);
		BigDecimal severancePay = e.fire();
		
		assertEquals("Employee doesn't get much but something",severancePay, SEVERANCE_PAY);
		hireAnEmployee();
	}

	private void hireAnEmployee() {
		Employee p = new President(FIRST_NAME, LAST_NAME, AGE, SALARY);
		p.hire();
		Assert.assertEquals(EmploymentStatus.HIRED, p.getStatus());
	}

}
