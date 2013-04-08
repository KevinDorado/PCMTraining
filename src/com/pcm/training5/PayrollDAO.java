package com.pcm.training5;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PayrollDAO {

	private EntityManager em;
	
	private EntityManagerFactory factory;
	
	public void init(){
		em = factory.createEntityManager();
	}
   
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
    public PayrollDAO() {
    	em = Persistence.createEntityManagerFactory("pu").createEntityManager();
	}

	public void persist(Payroll payroll) {
		
		//em.getTransaction().begin();
		//em.persist(payroll.getEmployee());
		em.persist(payroll);
		//em.flush();
		//em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Payroll> findAll() {
		Query query = em.createQuery("SELECT p FROM Payroll p");
		return query.getResultList();
	}
    
    
	
}
