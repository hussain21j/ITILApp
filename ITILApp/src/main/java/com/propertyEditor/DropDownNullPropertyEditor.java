package com.propertyEditor;

import java.beans.PropertyEditorSupport;

/**
 * This property editor will screen the drop down value of
 * id it is NULL, null of none then it will set its value as null, which will be picked by data binding process
 * @author user
 */
public class DropDownNullPropertyEditor extends PropertyEditorSupport{
	@Override
	public void setAsText(String dropdownValue) throws IllegalArgumentException {
		if(dropdownValue != null){
			if((dropdownValue.equalsIgnoreCase("null")) ||
					(dropdownValue.equalsIgnoreCase("none"))){
				dropdownValue = null;
			}
		}
		else{
			dropdownValue = null;
		}
		System.out.println("final value of drop down :"+dropdownValue);
		setValue(dropdownValue);
	}
}
