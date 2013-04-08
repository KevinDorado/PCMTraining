package com.pcm.training5;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT")
@SequenceGenerator(name = "department_seq", initialValue = 1, allocationSize = 1000)
public class Department {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "department_seq")
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "FloorNumber")
	private String floorNumber;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="department", cascade = CascadeType.ALL)
	private List<Employee> employee;
	
	public Department(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}
	public int getId() {
		return this.id;
	}
}
