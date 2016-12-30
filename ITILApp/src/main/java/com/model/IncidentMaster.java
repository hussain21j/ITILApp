package com.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ITIL_INCIDENT_MASTER")
public class IncidentMaster {
	@EmbeddedId
	private IncidentDetailId incidentDetailId;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "ASSIGNED_USER")
	private String assignedUser;
	@Column(name = "RESOLUTION_TIMESTAMP")
	private Timestamp resolutionTimestamp;
	@Column(name = "CLOSE_TIMESTAMP")
	private Timestamp closeTimestamp;
	@Column(name = "TOTAL_TIME_TAKEN")
	private int totalTimeTaken;
	public IncidentDetailId getIncidentDetailId() {
		return incidentDetailId;
	}
	public void setIncidentDetailId(IncidentDetailId incidentDetailId) {
		this.incidentDetailId = incidentDetailId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}
	public Timestamp getResolutionTimestamp() {
		return resolutionTimestamp;
	}
	public void setResolutionTimestamp(Timestamp resolutionTimestamp) {
		this.resolutionTimestamp = resolutionTimestamp;
	}
	public Timestamp getCloseTimestamp() {
		return closeTimestamp;
	}
	public void setCloseTimestamp(Timestamp closeTimestamp) {
		this.closeTimestamp = closeTimestamp;
	}
	public int getTotalTimeTaken() {
		return totalTimeTaken;
	}
	public void setTotalTimeTaken(int totalTimeTaken) {
		this.totalTimeTaken = totalTimeTaken;
	}
	
	
}
