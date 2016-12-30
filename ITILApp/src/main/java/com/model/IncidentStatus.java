package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITIL_INCIDENT_STATUS")
public class IncidentStatus {
	@Id
	@Column(name = "INCIDENT_STATUS_CODE")
	private String statusCode;
	@Column(name = "INCIDENT_STATUS_CODE_DESC")
	private String statusCodeDesc;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusCodeDesc() {
		return statusCodeDesc;
	}
	public void setStatusCodeDesc(String statusCodeDesc) {
		this.statusCodeDesc = statusCodeDesc;
	}
	
	
}
