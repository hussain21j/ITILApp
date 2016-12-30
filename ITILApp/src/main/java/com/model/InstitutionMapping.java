package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITIL_INSTITUTION_MAPPING")
public class InstitutionMapping {
	@Id
	@Column(name = "INSTITUTION_CODE")
	private String institutionCode;
	@Column(name = "INSTITUTION_NAME")
	private String institutionName;
	@Column(name = "INSTTUTION_TYPE")
	private String institutionType;
	
	
	
	public String getInstitutionCode() {
		return institutionCode;
	}
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getInstitutionType() {
		return institutionType;
	}
	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}
	
	
	
}
