package com.pcm.training5.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeDAO;
import com.pcm.training5.President;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class PresidentPersistenceTest {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Test
	public void persistPresidentTest() {
		President p = new President("Kevin", "Dorado", 22, "10000");
		employeeDAO.persist(p);
		
		President resultP = (President) employeeDAO.find(p.getId());
		//assertEquals(resultP.getFirstName(), "Kevin");
		assertNotNull(resultP);
		
		Employee e = new President("Kevin", "Dorado", 22, "10000");
		employeeDAO.persist(e);
		
		Employee resultE = (Employee) employeeDAO.find(p.getId());
		assertNotNull(resultE);
		
	}

}
