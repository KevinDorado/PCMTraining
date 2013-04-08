package com.pcm.training5;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EmployeeDAO {
	private EntityManager em;
	
	private EntityManagerFactory factory;
	
    static {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.hsqldb.jdbcDriver").newInstance());
        } catch (Exception e) {
            System.out.println("Crazy exception: " + e.getMessage());
            System.exit(-1);
        }
    }
    
    public void init(){
    	em = factory.createEntityManager();
    }
    
    public EmployeeDAO() {
		em = Persistence.createEntityManagerFactory("pu").createEntityManager();
	}
    
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}
    
	public void persist(Employee e) {
		em.getTransaction().begin();
		try {
			//em.persist(e.getDepartment());
			em.persist(e);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			em.getTransaction().rollback();
		}
	}
	
	public List<Employee> findByScoreRange(int start, int end) {
		return em.createQuery("SELECT e FROM Employee e WHERE score >= :start AND score <= :stop",Employee.class)
                .setParameter("start",start)
                .setParameter("stop",end)
                .getResultList();
	}

	public Employee find(int id) {
		return em.find(Employee.class,id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {	
		Query query =  em.createQuery("FROM " + Employee.class.getName());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findEmployeeByDepartment(String department) {
		Query query = em.createQuery("SELECT e FROM " + Employee.class.getName() + " e WHERE e.department.name LIKE :department");
		query.setParameter("department", "%" + department + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findSubordinates(int id) {
		Query query = em.createQuery("SELECT e FROM " + Employee.class.getName() + " e WHERE e.boss.id = :bossId");
		query.setParameter("bossId", id);
		return query.getResultList();
	}

	public Employee findByFirstAndLastName(String fName, String lName) {
		try {
			Query query = em.createQuery("SELECT e FROM " + Employee.class.getName() + " e WHERE e.firstName = :fName AND e.lastName = :lName");
			query.setParameter("fName", fName);
			query.setParameter("lName", lName);
			
			return (Employee) query.getSingleResult();
			
		} catch (EntityNotFoundException e) {
			return null;
		}
	}
}
