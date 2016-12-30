package com.model;





import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;  
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "ITIL_INCIDENT_DETAILS")
public class IncidentDetails {
	@EmbeddedId
	private IncidentDetailId incidentDetailId;
	//@Column(name = "INCIDENT_CATEGORY")
	@Transient
	private int category;
	//@Column(name = "INCIDENT_SUB_CATAGORY")
	@Transient
	private int subCategory;
	//@Column(name = "ORGINATION_MEDIUM")
	@Transient
	private int origMedium;
	//@Column(name = "INSTITUTION_TYPE")
	@Transient
	@NotNull(message =  "This property is mandatory")
	private String institutionType;
	//@Column(name = "INSTITUTION_CODE")
	@NotNull(message =  "This property is mandatory")
	@NotEmpty (message =  "This property can not be empty")
	@Transient
	private String institutionCode;
	//@Column(name = "INSTITUTION_NAME")
	@Transient
	private String institutionName;
	@Column(name = "USER_NAME")
	@Size(min = 3, max = 100)
	private String userName;
	@Column(name = "REGISTERED_USER_ID")
	@Size(min = 3, max = 20)
	private String registeredUserId;
	@Column(name = "USER_EMAIL_ID")
	private String userEmailId;
	@Column(name = "USER_CONTACT_NO")
	@NotNull(message =  "This property is mandatory")
	//@Range(min=1, max=10, message = "Contact number is not correct")
	private int userContactNumber;
	@Column(name = "INCIDENT_SUMMARY")
	private String incidentSumnmary;
	@Column(name = "REMARKS")
	private String incidentRemarks;
	@Column(name = "INCIDENT_SOLUTION")
	private String incidentSolution;
	
	@NotNull(message =  "This property is mandatory")
	@Past(message = "Date can not be future date")
	@Column(name = "incident_raised_on")
	private Date incidentRaisedOn;
	@Past(message = "Date can not be future date")
	@Column(name = "incident_closed_on")
	private Date incidentClosedOn;
	@NotNull(message =  "This property is mandatory")
	@Transient
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@NotNull(message =  "This property is mandatory")
	//@Column(name = "PRIORITY")
	@Transient
	private String priority;
	@NotNull(message =  "This property is mandatory")
	//@Column(name = "SEVERITY")
	@Transient
	private String severity;
	
	@ManyToOne
	@JoinColumn(name = "INCIDENT_CATEGORY") //to which column of incidentCategory table this column is matched is identified by the foreign key defined in the db
	private IncidentCategory incidentCategory;
	
	@ManyToOne
	@JoinColumn(name = "INCIDENT_SUB_CATAGORY")
	private IncidentSubCategory incidentSubCategory;
	
	@ManyToOne
	@JoinColumn(name = "ORGINATION_MEDIUM")
	private OriginationMedium originationMedium;
	
	@ManyToOne
	@JoinColumn(name = "INSTITUTION_TYPE")
	private InstitutionType instituteType;
	
	@ManyToOne
	@JoinColumn(name = "INSTITUTION_CODE")
	private InstitutionMapping institutionMapping;
	
	@ManyToOne
	@JoinColumn(name = "PRIORITY")
	private IncidentPriority incidentPriority;
	
	@ManyToOne
	@JoinColumn(name = "SEVERITY")
	private IncidentSeverity incidentSeverity;
	
	
	
	public InstitutionMapping getInstitutionMapping() {
		return institutionMapping;
	}
	public void setInstitutionMapping(InstitutionMapping institutionMapping) {
		this.institutionMapping = institutionMapping;
	}
	public IncidentDetailId getIncidentDetailId() {
		return incidentDetailId;
	}
	public void setIncidentDetailId(IncidentDetailId incidentDetailId) {
		this.incidentDetailId = incidentDetailId;
	}
	public String getInstitutionType() {
		return institutionType;
	}
	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}
	public String getInstitutionCode() {
		return institutionCode;
	}
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRegisteredUserId() {
		return registeredUserId;
	}
	public void setRegisteredUserId(String registeredUserId) {
		this.registeredUserId = registeredUserId;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public int getUserContactNumber() {
		return userContactNumber;
	}
	public void setUserContactNumber(int userContactNumber) {
		this.userContactNumber = userContactNumber;
	}
	public String getIncidentSumnmary() {
		return incidentSumnmary;
	}
	public void setIncidentSumnmary(String incidentSumnmary) {
		this.incidentSumnmary = incidentSumnmary;
	}
	public String getIncidentRemarks() {
		return incidentRemarks;
	}
	public void setIncidentRemarks(String incidentRemarks) {
		this.incidentRemarks = incidentRemarks;
	}
	public String getIncidentSolution() {
		return incidentSolution;
	}
	public void setIncidentSolution(String incidentSolution) {
		this.incidentSolution = incidentSolution;
	}
	public Date getIncidentRaisedOn() {
		return incidentRaisedOn;
	}
	public void setIncidentRaisedOn(Date incidentRaisedOn) {
		this.incidentRaisedOn = incidentRaisedOn;
	}
	public Date getIncidentClosedOn() {
		return incidentClosedOn;
	}
	public void setIncidentClosedOn(Date incidentClosedOn) {
		this.incidentClosedOn = incidentClosedOn;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	
	public IncidentCategory getIncidentCategory() {
		return incidentCategory;
	}
	public void setIncidentCategory(IncidentCategory incidentCategory) {
		this.incidentCategory = incidentCategory;
	}
	public int getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}
	public IncidentSubCategory getIncidentSubCategory() {
		return incidentSubCategory;
	}
	public void setIncidentSubCategory(IncidentSubCategory incidentSubCategory) {
		this.incidentSubCategory = incidentSubCategory;
	}
	public int getOrigMedium() {
		return origMedium;
	}
	public void setOrigMedium(int origMedium) {
		this.origMedium = origMedium;
	}
	public OriginationMedium getOriginationMedium() {
		return originationMedium;
	}
	public void setOriginationMedium(OriginationMedium originationMedium) {
		this.originationMedium = originationMedium;
	}
	public InstitutionType getInstituteType() {
		return instituteType;
	}
	public void setInstituteType(InstitutionType instituteType) {
		this.instituteType = instituteType;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public IncidentPriority getIncidentPriority() {
		return incidentPriority;
	}
	public void setIncidentPriority(IncidentPriority incidentPriority) {
		this.incidentPriority = incidentPriority;
	}
	public IncidentSeverity getIncidentSeverity() {
		return incidentSeverity;
	}
	public void setIncidentSeverity(IncidentSeverity incidentSeverity) {
		this.incidentSeverity = incidentSeverity;
	}

	
}
