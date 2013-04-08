package com.pcm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.pcm.training5.Loan;

public class LoanDAO {
	private EntityManager em;
	private EntityManagerFactory factory;
	
	public void init(){
		em = factory.createEntityManager();
	}
	
	public LoanDAO(){
		em = Persistence.createEntityManagerFactory("pu").createEntityManager();
	}
	
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public void persist(Loan loan) {
		em.getTransaction().begin();
		try{
			em.persist(loan);
			em.flush();
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Loan> findAll() {
		Query query = em.createQuery("FROM " +  Loan.class.getName());
		return query.getResultList();
	}
	
	
}
