package com.client;

import java.util.HashMap;

public class Employee implements Comparable<Employee>{
	private int employeeId;
	private String employeeName;
	
	
	public Employee(int employeeId, String employeeName) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.employeeId ==  ((Employee)obj).getEmployeeId() &&
				this.employeeName.equalsIgnoreCase(((Employee)obj).getEmployeeName())){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return employeeId + employeeName.hashCode();
	}
	

	
	public static void main(String[] args) {
		System.out.println("***************First we created two objects with same values as employee id and name*******************");
		Employee e1  = new Employee(1, "Janardhan");
		Employee e2 = new Employee(1, "Janardhan");
		Employee e3 = new Employee(3, "Anchal");
		
		System.out.println("***************compare two object using equls method************************");
		if(e1.equals(e2)){
			System.out.println("Both objects are equal");
		}
		else{
			System.out.println("objects are not equal");
		}
		System.out.println("\n\n*********Now Create a hash map and put these objects into hashmap with key as the object********");
		
		HashMap<Object, String> hashMap = new HashMap<Object, String>();
		hashMap.put(e1, "Employee1");
		hashMap.put(e2, "Employee2");
		hashMap.put(e3, "Employee3");
		
		if(hashMap.containsKey(new Employee(1, "Janardhan"))){
			System.out.println("Object found");
		}
		else{
			System.out.println("Not found  !!! Although we have already defined that objects are equal if there values are same ");
		}
		
		System.out.println("****************Now We implemented the hashcode we are overriding the default hashcode method of java**********");
		
	}

	public int compareTo(Employee anotherEmpployee) {
		return this.employeeName.compareTo(anotherEmpployee.employeeName);
	}
	
	@Override
	public String toString() {
		return employeeName+" : "+employeeId;
	}
	
}
