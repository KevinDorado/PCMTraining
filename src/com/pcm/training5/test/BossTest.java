package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.pcm.training5.Employee;
import com.pcm.training5.President;
import com.pcm.training5.PresidentException;

public class BossTest {

	private static final int AGE = 22;
	
	@Test
	public void shouldReturnBossOfAnEmployee() throws PresidentException{
		
		President boss1 = new President("Malou", "Jopillo", 50, "150000");
		assertEquals("Boss has no subordinates",boss1.getSubordinates().size(),0);
		Employee e1 = new Employee("Mark", "Aban", AGE, "35000");
		Employee e2 = new Employee("Ray Vincent", "Trinidad", AGE, "20000");
		
		e2.setBossOfEmployees(boss1, e1, e2);
		assertEquals("Employee e1 should have boss Malout", boss1, e1.getBoss());
		Assert.assertNotSame("Employee e1 is not the boss of Ray Vincent", e2.getBoss(), e1);
		assertEquals("Malou should have 2 subordinate", boss1.getSubordinates().size(), 2);

	}
	
}
