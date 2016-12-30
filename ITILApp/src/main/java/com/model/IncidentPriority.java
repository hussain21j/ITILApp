package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itil_priority")
public class IncidentPriority {
	@Id
	@Column(name = "PRIORITY_CODE")
	private String priorityCode;
	@Column(name = "PRIORITY_DESC")
	private String prirityDesc;
	
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	public String getPrirityDesc() {
		return prirityDesc;
	}
	public void setPrirityDesc(String prirityDesc) {
		this.prirityDesc = prirityDesc;
	}
	
	
	
}
