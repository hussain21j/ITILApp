package com.dao;

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

public interface TicketDao {
	public List<OriginationMedium> listOriginationMedium();
	public List<IncidentCategory> listIncidentCategory();
	public List<IncidentSubCategory> listIncidentSubCategory();
	public List<InstitutionType> listInstitutionType();
	public List<InstitutionMapping> getListOfInstitution(String institutionTypeCode);
	public InstitutionMapping getInstitution(String institutionId);
	public List<IncidentStatus> getIncidentStatusList();
	public List<IncidentPriority> getIncidentPriorityList();
	public List<IncidentSeverity> getIncidentSeverityList();
	public int getNewIncidentNo() throws Throwable;
	public int getIncidentCount() throws Throwable;
	public boolean registerNewIncident(IncidentTrack incidentTrack) throws Throwable;
	public boolean registerIncidentMaster(IncidentMaster incidentMaster) throws Throwable;
	public List<IncidentMaster> getIncidentMasterRecordWithStatus(int boundUserId, String incidentStatus);
	public List<IncidentDetails> getUserWorkList(String userId);
	public IncidentDetails getIncidentDetails(IncidentDetailId incidentDetailId);
	public IncidentCategory getIncidentCategoryCodeDescription(String incidentcategoryCode) throws Throwable;
	public IncidentSubCategory getIncidentSubCategoryCodeDescription(String incidentSubCategoryCode) throws Throwable;
	public OriginationMedium getOriginationMediumCodeDescription(String originationMediumCode) throws Throwable;
	public InstitutionType getInstitutionTypeCodeDescription(String institutionTypeCode) throws Throwable;
	public InstitutionMapping getInstitutionMapping(String institutionCode) throws Throwable;
	public IncidentPriority getPriority(String priorityCode) throws Throwable;
	public IncidentSeverity getSeverity(String sevirityCode) throws Throwable;
	public IncidentStatus getIncidentStatus(IncidentDetailId incidentDetailId) throws Throwable;
	public int getMaxSerialNumber(int incidentId) throws Throwable;
	public boolean validateIncidentExist(IncidentDetailId incidentDetailId) throws Throwable;
	public List<IncidentTrack> getIncidentTrackDetails(IncidentDetailId incidentDetailId) throws Throwable;
	public IncidentStatus getIncidentStatusCodeDesc(String incidentStatusCode) throws Throwable;
}
