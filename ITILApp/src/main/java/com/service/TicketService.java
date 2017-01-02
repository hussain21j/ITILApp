package com.service;

import java.util.List;

import com.model.IncidentCategory;
import com.model.IncidentDetailId;
import com.model.IncidentDetails;
import com.model.IncidentMaster;
import com.model.IncidentPriority;
import com.model.IncidentSeverity;
import com.model.IncidentStatus;
import com.model.IncidentSubCategory;
import com.model.IncidentTrack;
import com.model.InstitutionMapping;
import com.model.InstitutionType;
import com.model.OriginationMedium;

public interface TicketService {
	public List<OriginationMedium> getListOriginationMedium();
	public List<IncidentCategory> getListOfIncidentCategory();
	public List<IncidentSubCategory> getListOfIncidentSubCategory();
	public List<InstitutionType> getListOfInstitutionType();
	public List<InstitutionMapping> getListOfInstitution(String institutionTypeCode);
	public InstitutionMapping getInstitution(String institutionId);
	public List<IncidentStatus> getListOfIncidentStatus();
	public List<IncidentPriority> getListOfIncidenPriority();
	public List<IncidentSeverity> getListOfIncidenSeverity();
	public int registerNewIncident(IncidentDetails incidentDetails, String loggedInUser) throws Throwable;
	public List<IncidentDetails> getUserWorkList(int loggedInUser, String incidentStatus);
	public IncidentDetails getIncidentDetails(String incidentId) throws Throwable;
	public List<IncidentTrack> getIncidentTrackDetails(IncidentDetailId incidentDetailId) throws Throwable;
}
