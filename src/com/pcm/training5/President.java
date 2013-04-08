package com.pcm.training5;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class President extends Employee {

	private static final BigDecimal BONUS_PERCENTAGE = BigDecimal.valueOf(0.50);
	
	public President(String firstName, String lastName, int age, String salary) {
		super(firstName, lastName, age, salary);
	}
	

	@Override
	public BigDecimal getBonusPercentage() {
		return BONUS_PERCENTAGE;
	}
	
	@Override
	public BigDecimal fire() {
		super.fire();
		return applyGoldenParachute();
	}

	private BigDecimal applyGoldenParachute() {
		return new BigDecimal(8000000);
	}
	
}
