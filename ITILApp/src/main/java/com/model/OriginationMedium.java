package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is the model class for the origination medium
 * @author user
 *
 */
@Entity
@Table(name = "itil_origination_medium")
public class OriginationMedium {
	@Id
	@Column(name = "ORIGINATION_CODE")
	private String originationMediumCode;
	@Column(name = "ORIGINATION_DESC")
	private String originationMediumDesc;
	
	
	public String getOriginationMediumCode() {
		return originationMediumCode;
	}
	public void setOriginationMediumCode(String originationMediumCode) {
		this.originationMediumCode = originationMediumCode;
	}
	public String getOriginationMediumDesc() {
		return originationMediumDesc;
	}
	public void setOriginationMediumDesc(String originationMediumDesc) {
		this.originationMediumDesc = originationMediumDesc;
	}
	
	
}
