package com.pcm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.pcm.training5.Department;


public class DepartmentDAO {
	private EntityManager em;
	private EntityManagerFactory factory;
	
	public void init(){
		em = factory.createEntityManager();
	}
	
	public DepartmentDAO(){
		em = Persistence.createEntityManagerFactory("pu").createEntityManager();
	}
	
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public void persist(Department department) {
		em.getTransaction().begin();
		try{
			em.persist(department);
			em.flush();
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Department> findAll() {
		Query query = em.createQuery("FROM " + Department.class.getName());
		return query.getResultList();
	}
}
