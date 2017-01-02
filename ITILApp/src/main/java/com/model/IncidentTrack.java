package com.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ITIL_INCIDENT_TRACK_DTLS")
public class IncidentTrack {
	@EmbeddedId
	private IncidentDetailId incidentDetailId;
	@Transient
	//@Column(name = "STATUS")
	private String status;
	@Column(name = "ACTION_TIMESTAMP")
	private Timestamp actionTimestamp;
	@Column(name = "LOG_DESCRIPTION")
	private String logDescription;
	//@Column(name = "ACTION_TAKEN_BY")
	@Transient
	private int actionTakenBy;
	
	@ManyToOne
	@JoinColumn(name = "ACTION_TAKEN_BY")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "STATUS")
	private IncidentStatus incidentStatus;
	

	//mapping with the incident details
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "INCIDENT_ID"),
		@JoinColumn(name = "SERIAL_NO")
	})
	private IncidentDetails incidentDetails;
	
	
	//mapping with the incident sla log
		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinColumns({
			@JoinColumn(name = "INCIDENT_ID"),
			@JoinColumn(name = "SERIAL_NO")
		})
	private IncidentSLALog incidentSLALog;
		
	
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
	public Timestamp getActionTimestamp() {
		return actionTimestamp;
	}
	public void setActionTimestamp(Timestamp actionTimestamp) {
		this.actionTimestamp = actionTimestamp;
	}
	public String getLogDescription() {
		return logDescription;
	}
	public void setLogDescription(String logDescription) {
		this.logDescription = logDescription;
	}
	public IncidentDetails getIncidentDetails() {
		return incidentDetails;
	}
	public void setIncidentDetails(IncidentDetails incidentDetails) {
		this.incidentDetails = incidentDetails;
	}
	public IncidentSLALog getIncidentSLALog() {
		return incidentSLALog;
	}
	public void setIncidentSLALog(IncidentSLALog incidentSLALog) {
		this.incidentSLALog = incidentSLALog;
	}
	
	public int getActionTakenBy() {
		return actionTakenBy;
	}
	public void setActionTakenBy(int actionTakenBy) {
		this.actionTakenBy = actionTakenBy;
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
