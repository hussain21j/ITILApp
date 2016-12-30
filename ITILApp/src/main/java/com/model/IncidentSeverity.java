package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itil_severity")
public class IncidentSeverity {
	
	@Id
	@Column(name = "SEVERITY_CODE")
	private String severityCode;
	@Column(name = "SEVERITY_DESC")
	private String severityDesc;
	
	public String getSeverityCode() {
		return severityCode;
	}
	public void setSeverityCode(String severityCode) {
		this.severityCode = severityCode;
	}
	public String getSeverityDesc() {
		return severityDesc;
	}
	public void setSeverityDesc(String severityDesc) {
		this.severityDesc = severityDesc;
	}
	
}
