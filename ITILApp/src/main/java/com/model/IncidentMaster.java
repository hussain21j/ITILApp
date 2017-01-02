package com.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ITIL_INCIDENT_MASTER")
public class IncidentMaster {
	@EmbeddedId
	private IncidentDetailId incidentDetailId;
	//@Column(name = "STATUS")
	@Transient
	private String status;
	//@Column(name = "ASSIGNED_USER")
	@Transient
	private String assignedUser;
	@Column(name = "RESOLUTION_TIMESTAMP")
	private Timestamp resolutionTimestamp;
	@Column(name = "CLOSE_TIMESTAMP")
	private Timestamp closeTimestamp;
	@Column(name = "TOTAL_TIME_TAKEN")
	private int totalTimeTaken;
	
	@ManyToOne
	@JoinColumn(name ="STATUS")
	private IncidentStatus incidentStatus;
	
	@ManyToOne
	@JoinColumn(name = "ASSIGNED_USER")
	private User user;
	
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
	public IncidentStatus getIncidentStatus() {
		return incidentStatus;
	}
	public void setIncidentStatus(IncidentStatus incidentStatus) {
		this.incidentStatus = incidentStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
