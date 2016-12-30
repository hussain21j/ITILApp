package com.client;

import java.util.Comparator;

public class Animal implements Comparator {
	String animaCategory;
	int  animalClassification;
	public String getAnimaCategory() {
		return animaCategory;
	}
	public void setAnimaCategory(String animaCategory) {
		this.animaCategory = animaCategory;
	}
	public int getAnimalClassification() {
		return animalClassification;
	}
	public void setAnimalClassification(int animalClassification) {
		this.animalClassification = animalClassification;
	}
	public int compare(Object o1, Object o2) {
		Employee employee = (Employee)o1;
		Animal animal = (Animal)o2;
		int result = 0;
		result =  (new Integer(employee.getEmployeeId())).compareTo(new Integer(animal.getAnimalClassification()));
		if(result == 0){
			result = employee.getEmployeeName().compareTo(animal.getAnimaCategory());
		}
		return result;
	}
}
