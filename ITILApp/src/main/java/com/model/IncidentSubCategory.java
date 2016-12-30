package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITIL_INCIDENT_SUB_CATEGORY")
public class IncidentSubCategory {
	@Id
	@Column(name = "CATAEGORY_CODE")
	private String subCategoryCode;
	@Column(name = "CATEGORY_CODE_DESC")
	private String subCategoryCodeDesc;
	public String getSubCategoryCode() {
		return subCategoryCode;
	}
	public void setSubCategoryCode(String subCategoryCode) {
		this.subCategoryCode = subCategoryCode;
	}
	public String getSubCategoryCodeDesc() {
		return subCategoryCodeDesc;
	}
	public void setSubCategoryCodeDesc(String subCategoryCodeDesc) {
		this.subCategoryCodeDesc = subCategoryCodeDesc;
	}
	
	
}
