package com.pcm.training5;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PAYROLL")
@SequenceGenerator(name = "payroll_seq", initialValue = 1, allocationSize = 1000)
public class Payroll {
	
	private static final short NUMBER_OF_MONTHS = 12;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "payroll_seq")
	private int id;
	
	@Transient
	private BigDecimal bonus;
	
	@Column(name = "Salary")
	private BigDecimal salary;
	
	@ManyToOne
	@JoinColumn(name = "Employee_Id")
	private Employee employee;
	
	@Transient
	private short yearsOfService;
	
	public Payroll() {
	
	}
	
	public Payroll(String salary) {
		setSalary(salary);
	}

	public void setSalary(String salary) {
		this.salary = new BigDecimal(salary);
	}

	public String getSalary() {
		return this.salary.toString();
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getBonus() {
		return this.bonus.toString();
	}

	public BigDecimal getTotalAnnualIncome(Employee employee) {
		BigDecimal totalAnnualSalary = new BigDecimal(getAnnualSalary(employee));
		calculateBonus(employee);
		totalAnnualSalary = totalAnnualSalary.add(new BigDecimal(employee.getPayroll().getBonus()));
		return totalAnnualSalary;
	}

	private void calculateBonus(Employee employee) {
		BigDecimal bonus = new BigDecimal(getAnnualSalary(employee)).multiply(employee.getBonusPercentage());
		setBonus(bonus);
	}

	public String getAnnualSalary(Employee employee) {
		BigDecimal annualSalary =  new BigDecimal(employee.getPayroll().getSalary()).multiply(BigDecimal.valueOf(NUMBER_OF_MONTHS));
		return annualSalary.toString();
	}

	public int getId() {
		return this.id;
	}
	
	public void setYearsOfService(short yearsOfService) {
		this.yearsOfService = yearsOfService;
	}
	
	public short getYearsOfService() {
		return yearsOfService;
	}
}
