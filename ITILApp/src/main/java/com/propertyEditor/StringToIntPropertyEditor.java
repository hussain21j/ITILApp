package com.propertyEditor;

import java.beans.PropertyEditorSupport;

public class StringToIntPropertyEditor extends PropertyEditorSupport{
	@Override
	public void setAsText(String inputText) throws IllegalArgumentException {
		System.out.println("inputText :"+inputText);
		int intputInt;
		if(inputText != null){
			if((inputText.equalsIgnoreCase("null")) ||
					(inputText.equalsIgnoreCase("none"))){
				intputInt = 0;
			}
			else{
				try{
					intputInt = Integer.parseInt(inputText);
				}
				catch (Exception e) {
					intputInt = 0;
					System.out.println("Exception in convertint string  :"+inputText +" to integer");
				}
			}
		}
		else{
			intputInt = 0;
		}
		System.out.println("final value of drop down :"+intputInt);
		setValue(intputInt);
	}
	
	public static void main(String[] args) {
		String inputStr =  "123";
		int inputInt;
		inputInt = 0;
		System.out.println("input int :"+inputInt);
		
		
	}
}
