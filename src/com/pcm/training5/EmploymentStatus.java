package com.pcm.training5;

public enum EmploymentStatus {
    
    NOT_HIRED('N'),
    
    FIRED ('F'),
    
    HIRED('H');
    
    private char statusValue;

    private EmploymentStatus(char stringValue) {
           this.statusValue = stringValue;
    }
    
    public static EmploymentStatus getByValue(char strValue) {
           for (EmploymentStatus value : EmploymentStatus.values()) {
                  if (value.statusValue == strValue) {
                        return value;
                  }
           }
           throw new IllegalArgumentException("Wrong employment status provided: " + strValue);
    }
}