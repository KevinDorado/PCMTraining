package com.pcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pcm.dao.EmployeeDepartmentDAO;
import com.pcm.training5.Department;
import com.pcm.training5.Employee;
import com.pcm.training5.EmployeeDepartment;

public class EmployeeDepartmentService {

	@Autowired
	private EmployeeDepartmentDAO employeeDepartmentDAO;
	
	public void setEmployeeDepartments(Employee employee, Department... departments) {
		for(Department department: departments){
			EmployeeDepartment employeeDepartment = new EmployeeDepartment(employee, department);
			employeeDepartmentDAO.persist(employeeDepartment);
		}
	}

	public List<EmployeeDepartment> getDepartmentsOfEmployee(int id) {
		return employeeDepartmentDAO.findDepartmentsOfEmployee(id);
	}

}
