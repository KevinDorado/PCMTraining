package com.pcm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.pcm.training5.EmployeeDepartment;

public class EmployeeDepartmentDAO {
	private EntityManager em;
	private EntityManagerFactory factory;
	
	public void init(){
		em = factory.createEntityManager();
	}
	
	public EmployeeDepartmentDAO(){
		em = Persistence.createEntityManagerFactory("pu").createEntityManager();
	}
	
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public void persist(EmployeeDepartment employeeDepartment) {
		em.getTransaction().begin();
		try{
			em.persist(employeeDepartment);
			em.flush();
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeDepartment> findDepartmentsOfEmployee(int id) {
		Query query = em.createQuery("SELECT ed FROM " + EmployeeDepartment.class.getName() + " ed WHERE ed.employee.id = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
