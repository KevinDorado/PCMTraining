package com.pcm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeLoan;

public class EmployeeLoanDAO {
	private EntityManager em;
	
	private EntityManagerFactory factory;
	
	public void init(){
		em = factory.createEntityManager();
	}
	
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	public EmployeeLoanDAO() {
		em = Persistence.createEntityManagerFactory("pu").createEntityManager();
	}

	public void persist(EmployeeLoan employeeLoan) {
		em.getTransaction().begin();
		try{
			em.persist(employeeLoan);
			em.flush();
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findEmployeesWithLoan(int id) {
		Query query = em.createNamedQuery("findEmployeeByEmployeeLoan");
		query.setParameter("id", id);
		return query.getResultList();
		//findEmployeeByEmployeeLoan
	}
}
