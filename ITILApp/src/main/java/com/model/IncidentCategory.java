package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITIL_INCIDENT_CATEGORY")
public class IncidentCategory {
	@Id
	@Column(name = "CATAEGORY_CODE")
	private String categoryCode;
	@Column(name = "CATEGORY_CODE_DESC")
	private String categoryCodeDesc;
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryCodeDesc() {
		return categoryCodeDesc;
	}
	public void setCategoryCodeDesc(String categoryCodeDesc) {
		this.categoryCodeDesc = categoryCodeDesc;
	}
	
}
