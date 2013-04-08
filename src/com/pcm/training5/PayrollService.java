package com.pcm.training5;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PayrollService {

	private static final BigDecimal SALARY_INCREASE_PERCENTAGE = BigDecimal.valueOf(1.1);
	private static final BigDecimal PENSION_FUND_PERCENTAGE_PER_YEAR = new BigDecimal(0.25);
	private static PayrollService payrollService;
	private static short NUMBER_OF_MONTHS = 12;
		
	@Autowired
	private EmployeeDAO employeeDAO;
	
	private PayrollService(){
		
	}
	
	public static PayrollService getInstance() {
		if(payrollService == null){
			payrollService = new PayrollService();
		}
		return payrollService;
	}

	public BigDecimal computePensionFund(Employee employee, short yearsOfService) {
		employee.getPayroll().setEmployee(employee);
		BigDecimal totalSalary = computeTotalSalary(employee, yearsOfService);
		BigDecimal pensionFund = totalSalary.multiply(PENSION_FUND_PERCENTAGE_PER_YEAR).multiply(BigDecimal.valueOf(NUMBER_OF_MONTHS));
		return pensionFund;
	}

	private BigDecimal computeTotalSalary(Employee e, short yearsOfService) {
		BigDecimal totalSalary = new BigDecimal(e.getPayroll().getSalary());
		if(yearsOfService < 1){
			return totalSalary;
		}	
		for(int counter = 2; counter <= yearsOfService; counter++){
			totalSalary = totalSalary.multiply(SALARY_INCREASE_PERCENTAGE);
		}
		return totalSalary;
	}

	public BigDecimal computeGoldenParachuteAmount(President p) throws PresidentException {
		BigDecimal goldenParachuteAmount = BigDecimal.valueOf(0);
		
		if(!(p.getStatus() == EmploymentStatus.FIRED)){
			throw new PresidentException("The president is not yet fired");
		}
		
		goldenParachuteAmount = computeServerancePay(p);
		return goldenParachuteAmount;
		
	}

	private BigDecimal computeServerancePay(Employee president) {
		BigDecimal severancePay = BigDecimal.valueOf(0);
		president.getPayroll().setEmployee(president);
		BigDecimal bonus = new BigDecimal(getBonus(president));
		BigDecimal annualSalary = new BigDecimal(president.getPayroll().getAnnualSalary(president));
		severancePay = annualSalary.add(bonus);
		return severancePay;
	}

	public String getBonus(Employee employee) {
		employee.getPayroll().setEmployee(employee);
		BigDecimal annualSalary = new BigDecimal(employee.getPayroll().getAnnualSalary(employee));
		BigDecimal bonusPercentage = employee.getBonusPercentage();
		BigDecimal totalBonus = annualSalary.multiply(bonusPercentage);
		employee.getPayroll().setBonus(totalBonus);
		return totalBonus.toString();
	}

	public void addEmployee(Employee employee) {
		employeeDAO.persist(employee);
	}

	public List<Employee> getAllEmployees() {
		return employeeDAO.findAll();
	}

	public Employee findByFirstAndLastName(String fName, String lName) {
		return employeeDAO.findByFirstAndLastName(fName, lName);
	}
}
