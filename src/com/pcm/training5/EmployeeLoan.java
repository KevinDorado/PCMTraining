package com.pcm.training5;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeLoan")
@SequenceGenerator(name = "employee_loan_seq", initialValue = 1, allocationSize = 1000)
@NamedQuery(name = "findEmployeeByEmployeeLoan", query = "SELECT el FROM EmployeeLoan el WHERE el.employee.id = :id")
public class EmployeeLoan {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_loan_seq")
	private int id;

	@Column(name = "LoanAmount")
	private BigDecimal amount;

	@ManyToOne
	@JoinColumn(name = "Employee_Id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "Loan_Id")
	private Loan loan;
	
	public EmployeeLoan(Employee employee, Loan loan, String loanAmount) {
		this.employee = employee;
		this.loan = loan;
		setLoanAmountToBigDecimal(loanAmount);
	}

	private void setLoanAmountToBigDecimal(String loanAmount) {
		this.amount = new BigDecimal(loanAmount);
	}

	public int getId() {
		return this.id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	
}
