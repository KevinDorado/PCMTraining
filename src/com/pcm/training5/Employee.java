package com.pcm.training5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EMPLOYEE")
@SequenceGenerator(name = "employee_seq", initialValue = 1, allocationSize = 1000)
public class Employee extends Person {

	private static final BigDecimal BONUS_PERCENTAGE = BigDecimal.valueOf(0.20);
	private static final short MAXIMUM_SUBORDINATES = 10;	
	private static final short SUBORDINATES = 5;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "employee_seq")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Department_Id")
	private Department department;
	
	@Transient
	private EmploymentStatus status;
	
	@ManyToOne
	@JoinColumn(name = "Boss_Id")
	private Employee boss;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "boss")
	private Set<Employee> subordinates;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Payroll> payroll = new ArrayList<Payroll>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeLoan> employeeLoan = new ArrayList<EmployeeLoan>();

	@Column (name = "SCORE")
	private int score;
	
	public Employee(String firstName, String lastName, int age, String salary) {
		super(firstName, lastName, age);
		setPayroll(new Payroll(salary));
		subordinates = new HashSet<Employee>();
	}	
	
	private void setPayroll(Payroll payroll) {
		this.payroll.add(payroll);
	}


	public void setDepartment(String departmentName) {
		this.department = new Department(departmentName);
	}
	
	public EmploymentStatus getStatus() {
		return status;
	}

	public BigDecimal fire() {
		setStatus(EmploymentStatus.FIRED);
		return getAmount();
	}
	
	private BigDecimal getAmount() {
		return new BigDecimal(500000);
	}
	
	public void hire() {
		setStatus(EmploymentStatus.HIRED);
	}
	
	protected void setStatus(EmploymentStatus employmentStatus) {
		this.status = employmentStatus;
	}
	
	public void setBossOfEmployees(Employee boss, Employee... subordinates) throws PresidentException {
		for(Employee subordinate: subordinates){
			setBossOfEmployee(boss, subordinate);
			checkIfSubordinatesExceed();
		}
		checkBossTotalCompensation(boss);
		
	}

	private void setBossOfEmployee(Employee boss, Employee subordinate) {
		subordinate.setBoss(boss);
		boss.getSubordinates().add(subordinate);
	}

	private void checkIfSubordinatesExceed() throws PresidentException {
		if(getSubordinates().size() > MAXIMUM_SUBORDINATES){
			throw new PresidentException("Subordinates cannot be greater than 10");
		}
	}
	
	private void checkBossTotalCompensation(Employee boss) throws PresidentException {
		if(getSubordinates().size() > SUBORDINATES){
			
			if(boss.getPayroll().getTotalAnnualIncome(boss).compareTo(getSubordinatesTotalAnnualIncome(boss)) == 1){
				throw new PresidentException("Salary of the president is too high");
			}
		}
	}

	private BigDecimal getSubordinatesTotalAnnualIncome(Employee boss) {
		BigDecimal totalBonusOfSubordinates = BigDecimal.valueOf(0);
		for(Employee subordinate: boss.getSubordinates()){
			BigDecimal subordinateTotalAnnualIncome = subordinate.getPayroll().getTotalAnnualIncome(subordinate);
			totalBonusOfSubordinates = totalBonusOfSubordinates.add(subordinateTotalAnnualIncome);
		}
		
		return totalBonusOfSubordinates;
	}
	
	private void setBoss(Employee boss) {
		this.boss = boss;
	}
	
	public Set<Employee> getSubordinates() {
		return this.subordinates;
	}

	public Employee getBoss() {
		return this.boss;
	}

	public Payroll getPayroll() {
		return this.payroll.isEmpty() ? null : payroll.get(0);
	}
	
	public BigDecimal getBonusPercentage(){
		return BONUS_PERCENTAGE;
	}

	public void setScore(int score) {
		this.score = score;
		
	}
	public int getId() {
		return this.id;
	}

	public Department getDepartment() {
		return this.department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
