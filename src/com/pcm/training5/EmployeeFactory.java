package com.pcm.training5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EmployeeFactory <T extends Employee>{

	
	private static EmployeeFactory employeeFactory;
	
	private EmployeeFactory(){
		
	}
	
	public static EmployeeFactory getInstance(){
        if (employeeFactory == null) {
        	employeeFactory = new EmployeeFactory<>();
        }	
        return employeeFactory;

	}

	public T createEmployee(String firstName, String lastName, int age,
			String salary, Class<T> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<T> constructor = clazz.getDeclaredConstructor(String.class,String.class,int.class,String.class);
        return constructor.newInstance(firstName,lastName,age,salary);

	}
	
}
