package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the composite key for many of the incident orm classes
 * @author user
 *
 */

@Embeddable
public class IncidentDetailId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "INCIDENT_ID")
	private int incidentId;
	@Column(name = "SERIAL_NO")
	private int serialNo;
	public int getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	
	
}
