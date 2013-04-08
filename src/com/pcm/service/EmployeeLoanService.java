package com.pcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pcm.dao.EmployeeLoanDAO;
import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeLoan;

public class EmployeeLoanService {
	
	@Autowired
	private EmployeeLoanDAO employeeLoanDAO;
	
	public void saveLoan(EmployeeLoan employeeLoan) {
		employeeLoanDAO.persist(employeeLoan);
	}

	public List<Employee> getEmployee(int id) {
		return employeeLoanDAO.findEmployeesWithLoan(id);
	}

	
	
}
