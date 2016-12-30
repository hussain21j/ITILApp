package com.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ITIL_SLA_LOG")
public class IncidentSLALog {
	@EmbeddedId
	private IncidentDetailId incidentDetailId;
	@Column(name = "SLA_TOTAL_ALLOWED_TIME")
	private int slaTotalAllowdTime;
	@Column(name = "HALT_REASON")
	private String haltReason;
	@Column(name = "HALT_START_TIME")
	private Timestamp haltStartTimestamp;
	@Column(name = "HALT_END_TIME")
	private Timestamp haltCloseTimestamp;
	public IncidentDetailId getIncidentDetailId() {
		return incidentDetailId;
	}
	public void setIncidentDetailId(IncidentDetailId incidentDetailId) {
		this.incidentDetailId = incidentDetailId;
	}
	public int getSlaTotalAllowdTime() {
		return slaTotalAllowdTime;
	}
	public void setSlaTotalAllowdTime(int slaTotalAllowdTime) {
		this.slaTotalAllowdTime = slaTotalAllowdTime;
	}
	public String getHaltReason() {
		return haltReason;
	}
	public void setHaltReason(String haltReason) {
		this.haltReason = haltReason;
	}
	public Timestamp getHaltStartTimestamp() {
		return haltStartTimestamp;
	}
	public void setHaltStartTimestamp(Timestamp haltStartTimestamp) {
		this.haltStartTimestamp = haltStartTimestamp;
	}
	public Timestamp getHaltCloseTimestamp() {
		return haltCloseTimestamp;
	}
	public void setHaltCloseTimestamp(Timestamp haltCloseTimestamp) {
		this.haltCloseTimestamp = haltCloseTimestamp;
	}
	
	
	
	

}
