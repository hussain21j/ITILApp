package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITIL_SEQ_INCIDENT")
public class IncidentSequence {
	@Id
	@Column(name = "NEXT_SEQ_VAL")
	int nextSequenceNumber;

	public int getNextSequenceNumber() {
		return nextSequenceNumber;
	}

	public void setNextSequenceNumber(int nextSequenceNumber) {
		this.nextSequenceNumber = nextSequenceNumber;
	}
	
	
}
