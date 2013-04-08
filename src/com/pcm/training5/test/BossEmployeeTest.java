package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeDAO;
import com.pcm.training5.PresidentException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class BossEmployeeTest {

	private static final int AGE = 22;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Test
	public void employeeBossRelationshipTest() throws PresidentException {
		
		Employee boss1 = new Employee("Malou", "Jopillo", 50, "150000");
		assertEquals("Boss has no subordinates",boss1.getSubordinates().size(),0);
		Employee e1 = new Employee("Mark", "Aban", AGE, "35000");
		Employee e2 = new Employee("Ray Vincent", "Trinidad", AGE, "20000");
		
		e2.setBossOfEmployees(boss1, e1, e2);
		
		//just checking if the subordinates are under the same boss
		assertEquals("Employee e1 should have boss Malout", boss1, e1.getBoss());
		Assert.assertNotSame("Employee e1 is not the boss of Ray Vincent", e2.getBoss(), e1);
		assertEquals("Malou should have 2 subordinate", boss1.getSubordinates().size(), 2);
		
		//start persisting boss and his/her subordinates by persisting only the boss
		employeeDAO.persist(boss1);
		
		
		Employee find1stEmp = employeeDAO.find(e1.getId());
		Employee find2ndEmp = employeeDAO.find(e2.getId());
		assertEquals("Boss id must be equal to the boss id of e1", boss1.getId(), find1stEmp.getBoss().getId());
		assertEquals("the boss id of e1 should be the same with the boss id of e2", find1stEmp.getBoss().getId(), find2ndEmp.getBoss().getId()); 
		assertNull("Boss boss1 doesn't have any boss", boss1.getBoss());
			
		List<Employee> findSubordinate = employeeDAO.findSubordinates(boss1.getId());
		
		assertEquals("Boss1 should have 2 subordinates", findSubordinate.size(), 2);
	}

}
