package com.pcm.training5;

import java.util.ArrayList;
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
@Table(name = "Loan")
@SequenceGenerator(name = "loan_seq", initialValue = 1, allocationSize = 1000)
public class Loan {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_seq")
	private int id;

	@Column(name = "Name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loan", cascade = CascadeType.ALL)
	private List<EmployeeLoan> employeeLoan = new ArrayList<EmployeeLoan>();
	
	public Loan(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
