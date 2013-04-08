package com.pcm.training5.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.Assert;

import org.junit.Test;

import com.pcm.training5.Employee;
import com.pcm.training5.PayrollService;
import com.pcm.training5.Person;
import com.pcm.training5.President;

public class EmployeeTest {

	private static final String FIRST_NAME = "Kevin";
	private static final String LAST_NAME = "Dorado";
	private static final int AGE = 22;
	private static final String SALARY = "100000";
	private static final String EMPLOYEE_BONUS = "24000";
	private static final String PRESIDENT_BONUS = "600000";
	
	@Test
	public void basicEmployeeCreationTest() {
		Employee p = new Employee(FIRST_NAME, LAST_NAME, AGE, SALARY);
		Assert.assertEquals(p.getFirstName(), FIRST_NAME);
		Assert.assertEquals(p.getLastName(), LAST_NAME);
		Assert.assertEquals(p.getAge(), AGE);
		Assert.assertTrue(SALARY.compareTo(p.getPayroll().getSalary()) == 0);
	}
	
	@Test
	public void shouldReturnEmployeeBonusAmount(){
		PayrollService p = PayrollService.getInstance();
		Employee e = new Employee(FIRST_NAME, LAST_NAME, AGE, "10000");
		Assert.assertEquals(Double.valueOf(p.getBonus(e)), Double.valueOf(EMPLOYEE_BONUS), 0.0000001);
	}
	
	@Test
	public void shouldReturnPresidentBonusAmount(){
		PayrollService p = PayrollService.getInstance();
		President president = new President(FIRST_NAME, LAST_NAME, AGE, SALARY);
		Assert.assertEquals(Double.valueOf(p.getBonus(president)), Double.valueOf(PRESIDENT_BONUS), 0.0000001);
	}
	
	@Test
	public void setCollectionTest(){
		Set<Employee> employee = new HashSet<Employee>();

		Employee p = new Employee(FIRST_NAME, LAST_NAME, AGE, SALARY);
		Employee p1 = new Employee("Mark", "Aban", AGE, "35000");
		Employee p2 = new Employee("Ray Vincent", "Trinidad", AGE, "20000");
		Employee p3 = new Employee("Patrick", "Ceniza", AGE, "15000");
		Employee p4 = new Employee("Patrick", "Ceniza", AGE, "15000");
		
		employee.add(p);
		employee.add(p1);
		employee.add(p2);
		employee.add(p3);
		Assert.assertEquals(employee.size(), 4);
		employee.add(p4);//this one is the same as p3, so it must not be included
		Assert.assertEquals(employee.size(), 4);
	}

	@Test
    public void compareTest() {
           Person p = new President("Kelvin", "Te", AGE, SALARY);
           Person p1 = new President("Cathy","Belen", AGE, SALARY);
           Person p2 = new President("Kevin", "Dorado", AGE, SALARY);
           Person p3 = new President("Kel", "Calayang", AGE, SALARY);
           
           SortedSet<Person> s = new TreeSet<Person>(new Comparator<Person>() {

                  @Override
                  public int compare(Person o1, Person o2) {
                        return o1.getLastName().compareTo(o2.getLastName());
                  }
           });
           s.add(p1);
           s.add(p2);
           s.add(p3);
           s.add(p);
           
           List<Person> list = new ArrayList<Person>(s);
           Assert.assertEquals(list.get(0).getLastName(),"Belen");

           Assert.assertEquals(list.get(1).getLastName(),"Calayang");

           Assert.assertEquals(list.get(2).getLastName(),"Dorado");

          assertEquals(list.get(3).getLastName(),"Te");

	}	
}
