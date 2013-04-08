package com.pcm.training5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EmployeeDepartment")
@SequenceGenerator(name = "employee_department_seq", initialValue = 1, allocationSize = 1000)
public class EmployeeDepartment {
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_department_seq")
	private int id;

	@ManyToOne
	@JoinColumn(name = "Employee_Id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "Department_Id")
	private Department department;
	
	
	public EmployeeDepartment(Employee employee, Department department) {
		this.department = department;
		this.employee = employee;
	}

	
}
