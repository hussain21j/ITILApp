package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITIL_INSTITUTION_TYPE")
public class InstitutionType {
	@Id
	@Column(name = "INSTITUTION_TYPE_CODE")
	private String instiTypeCode;
	@Column(name = "INSTITUTION_TYPE_CODE_DESC")
	private String institutionTypeCodeDesc;
	public String getInstiTypeCode() {
		return instiTypeCode;
	}
	public void setInstiTypeCode(String instiTypeCode) {
		this.instiTypeCode = instiTypeCode;
	}
	public String getInstitutionTypeCodeDesc() {
		return institutionTypeCodeDesc;
	}
	public void setInstitutionTypeCodeDesc(String institutionTypeCodeDesc) {
		this.institutionTypeCodeDesc = institutionTypeCodeDesc;
	}
	
	
}
