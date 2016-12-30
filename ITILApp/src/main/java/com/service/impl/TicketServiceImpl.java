package com.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.IndirectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.dao.TicketDao;
import com.model.IncidentCategory;
import com.model.IncidentDetailId;
import com.model.IncidentDetails;
import com.model.IncidentMaster;
import com.model.IncidentPriority;
import com.model.IncidentSLALog;
import com.model.IncidentSeverity;
import com.model.IncidentStatus;
import com.model.IncidentSubCategory;
import com.model.IncidentTrack;
import com.model.InstitutionMapping;
import com.model.InstitutionType;
import com.model.OriginationMedium;
import com.service.TicketService;
import com.util.ITILConstantUtil;
import com.util.TimeUtil;

@Service
public class TicketServiceImpl implements TicketService{
	@Autowired
	private TicketDao ticketDao;
	
	@Transactional
	public List<OriginationMedium> getListOriginationMedium() {
		return ticketDao.listOriginationMedium();
	}

	@Transactional
	public List<IncidentCategory> getListOfIncidentCategory() {
		return ticketDao.listIncidentCategory();
	}
	
	@Transactional
	public List<IncidentSubCategory> getListOfIncidentSubCategory() {
		return ticketDao.listIncidentSubCategory();
	}
	
	@Transactional
	public List<InstitutionType> getListOfInstitutionType() {
		return ticketDao.listInstitutionType();
	}
	
	@Transactional
	public List<InstitutionMapping> getListOfInstitution(String institutionTypeCode) {
		return ticketDao.getListOfInstitution(institutionTypeCode);
	}
	
	@Transactional
	public InstitutionMapping getInstitution(String institutionId) {
		return ticketDao.getInstitution(institutionId);
	}

	@Transactional
	public List<IncidentStatus> getListOfIncidentStatus() {
		return ticketDao.getIncidentStatusList();
	}
	
	@Transactional
	public List<IncidentPriority> getListOfIncidenPriority() {
		return ticketDao.getIncidentPriorityList();
	}

	@Transactional
	public List<IncidentSeverity> getListOfIncidenSeverity() {
		return ticketDao.getIncidentSeverityList();
	}
	
	/**
	 * This method is having all the incident details,
	 * this method will first generate the primary key for the incident table
	 * set data in incidenttrack
	 * set data in incidentmaster
	 * set data in sla log 
	 * insert data in incident track, details and log in one go as these are the mapped as one to  one
	 * insert data in incident master table
	 * once everything is done then return the incident id
	 * @throws Throwable 
	 * 
	 * 
	 */
	@Transactional
	public int registerNewIncident(IncidentDetails incidentDetails) throws Throwable {
		System.out.println("beggingng the registerNewIncident");
		int newIncidentNo = 0;
		IncidentDetailId incidentDetailId = null;
		IncidentTrack incidentTrack = null;
		IncidentSLALog incidentSLALog = null;
		IncidentMaster incidentMaster = null;
		Timestamp actionTimestamp = null;
		boolean processStatus =false;
		IncidentCategory incidentCategory = null;
		IncidentSubCategory incidentSubCategory = null;
		OriginationMedium originationMedium = null;
		InstitutionType instituteType = null;
		InstitutionMapping institutionMapping = null;
		IncidentPriority incidentPriority = null;
		IncidentSeverity incidentSeverity = null;
		IncidentStatus incidentStatus = null;
		
		String incidentCategoryCode = "";
		String incidentSubCategoryCode = "";
		String origMediumCode = "";
		String institutionType = "";
		String institutionCode = "";
		String priority = "";
		String severity = "";
		String status = "";
		
		try{
			//get the current timestamp
			actionTimestamp = TimeUtil.getTimeStamp();
			System.out.println("actionTimestamp :"+actionTimestamp);
			//get the new incident number
			newIncidentNo = ticketDao.getNewIncidentNo();
			incidentDetailId = new IncidentDetailId();
			incidentSLALog = new IncidentSLALog();
			incidentTrack = new IncidentTrack();
			incidentMaster = new IncidentMaster();
			incidentCategory = new IncidentCategory();
			incidentSubCategory = new IncidentSubCategory();
			originationMedium = new OriginationMedium();
			instituteType = new InstitutionType();
			institutionMapping = new InstitutionMapping();
			incidentPriority = new IncidentPriority();
			incidentSeverity = new IncidentSeverity(); 
			incidentStatus = new IncidentStatus();
			System.out.println("done");
			
			//set the incident detail primary key
			incidentDetailId.setIncidentId(newIncidentNo);
			incidentDetailId.setSerialNo(ITILConstantUtil.INCIDENT_SEQUENCE_NUMBER_ONE);
			incidentCategoryCode = String.valueOf(incidentDetails.getCategory());
			incidentSubCategoryCode = String.valueOf(incidentDetails.getSubCategory());
			origMediumCode = String.valueOf(incidentDetails.getOrigMedium());
			institutionType = String.valueOf(incidentDetails.getInstitutionType());
			institutionCode = String.valueOf(incidentDetails.getInstitutionCode() );
			priority = incidentDetails.getPriority();
			severity = incidentDetails.getSeverity();
			status = incidentDetails.getStatus();
			
			
			//set incident category 
			incidentCategory.setCategoryCode(incidentCategoryCode);
			incidentCategory.setCategoryCodeDesc((ticketDao.getIncidentCategoryCodeDescription(incidentCategoryCode).getCategoryCodeDesc()));
			
			//set incident sub category
			incidentSubCategory.setSubCategoryCode(incidentSubCategoryCode);
			incidentSubCategory.setSubCategoryCodeDesc((ticketDao.getIncidentSubCategoryCodeDescription(incidentSubCategoryCode).getSubCategoryCodeDesc()));
			
			//set origination medium details
			originationMedium.setOriginationMediumCode(origMediumCode);
			originationMedium.setOriginationMediumDesc((ticketDao.getOriginationMediumCodeDescription(origMediumCode).getOriginationMediumDesc()));
			
			//set institution type details
			instituteType.setInstiTypeCode(institutionType);
			System.out.println("institutionType :"+institutionType);
			instituteType.setInstitutionTypeCodeDesc((ticketDao.getInstitutionTypeCodeDescription(institutionType).getInstitutionTypeCodeDesc()));
			
			//set institution mapping details
			institutionMapping.setInstitutionCode(institutionCode);
			institutionMapping.setInstitutionName((ticketDao.getInstitutionMapping(institutionCode).getInstitutionName()));
			institutionMapping.setInstitutionType((ticketDao.getInstitutionMapping(institutionCode).getInstitutionType()));
			
			//set priority object details
			incidentPriority.setPriorityCode(priority);
			incidentPriority.setPrirityDesc(ticketDao.getPriority(priority).getPrirityDesc());

			//set severity object details
			incidentSeverity.setSeverityCode(severity);
			incidentSeverity.setSeverityDesc(ticketDao.getSeverity(severity).getSeverityDesc());
			
			//set incident status
			System.out.println("status passed from screen :"+status);
			incidentStatus.setStatusCode(status);
			incidentStatus.setStatusCodeDesc(ticketDao.getIncidentStatusCodeDesc(status).getStatusCodeDesc());
			
			System.out.println("incident status code desc :"+incidentStatus.getStatusCodeDesc());
			
			
			/*set primary key, incident category, incident sub category, origination medium, institute type, 
			institution mapping in incident details, priority, severity
			*/ 
			incidentDetails.setIncidentDetailId(incidentDetailId);
			incidentDetails.setIncidentCategory(incidentCategory);
			incidentDetails.setIncidentSubCategory(incidentSubCategory);
			incidentDetails.setOriginationMedium(originationMedium);
			incidentDetails.setInstituteType(instituteType);
			incidentDetails.setInstitutionMapping(institutionMapping);
			incidentDetails.setIncidentPriority(incidentPriority);
			incidentDetails.setIncidentSeverity(incidentSeverity);
			
			//set data in incident sla log
			incidentSLALog.setIncidentDetailId(incidentDetailId);
			incidentSLALog.setSlaTotalAllowdTime(240); //dummy value
			
			//set data in incident track
			incidentTrack.setIncidentDetailId(incidentDetailId);
			//incidentTrack.setStatus(ITILConstantUtil.INCIDENT_STATUS_OPEN);
			incidentTrack.setIncidentStatus(incidentStatus);
			incidentTrack.setActionTimestamp(actionTimestamp);
			incidentTrack.setLogDescription(ITILConstantUtil.INCIDENT_LOG_FIRST_DRAFT);
			incidentTrack.setIncidentDetails(incidentDetails);
			incidentTrack.setIncidentSLALog(incidentSLALog);
			incidentTrack.setActionTakenBy(251055);
			incidentTrack.setActionTimestamp(actionTimestamp);
			
			//set data in incident master
			incidentMaster.setIncidentDetailId(incidentDetailId);
			incidentMaster.setStatus(ITILConstantUtil.INCIDENT_STATUS_OPEN);
			incidentMaster.setAssignedUser("251055");
			//incidentMaster.setResolutionTimestamp();
			//incidentMaster.setTotalTimeTaken(210);//dummy value
			
			processStatus = ticketDao.registerNewIncident(incidentTrack);
			processStatus = ticketDao.registerIncidentMaster(incidentMaster);
			
			System.out.println("processStatus :"+processStatus);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			newIncidentNo = -1;
		}
		finally{
			//do nothing
		}
		System.out.println("finally returning");
		return newIncidentNo;
	}

	@Transactional
	public List<IncidentDetails> getUserWorkList(int loggedInUser, String incidentStatus) {
		System.out.println("service impl getUserWorkList");
		ArrayList<IncidentMaster> listIncidentMasters = null;
		ArrayList<IncidentDetails> listIncidentDetails = null;
		
		
		listIncidentDetails = new ArrayList<IncidentDetails>();
		listIncidentMasters = (ArrayList<IncidentMaster>) ticketDao.getIncidentMasterRecordWithStatus(loggedInUser, incidentStatus);
		for(IncidentMaster incidentMaster : listIncidentMasters){
			listIncidentDetails.add(ticketDao.getIncidentDetails(incidentMaster.getIncidentDetailId()));
		}
		return listIncidentDetails;
		
	}
	
	/**
	 * This method fetches the details of the incident 
	 * first it check if the incident exsist
	 * if exist then it finds the maximum serial number and returns the details of the incident
	 * @throws Throwable 
	 * @throws NumberFormatException 
	 */
	@Transactional
	public IncidentDetails getIncidentDetails(String incidentId) throws NumberFormatException, Throwable {
		System.out.println("starting the getIncidentDetails");
		int incidentMaxSerialNumber = 0;
		IncidentDetailId incidentDetailId = null;
		IncidentDetails incidentDetails = null;
		
		try{
			incidentMaxSerialNumber = ticketDao.getMaxSerialNumber(Integer.parseInt(incidentId));
			if(incidentMaxSerialNumber <= 0){
				incidentDetails = null;
			}
			else{
				incidentDetailId = new IncidentDetailId();
				incidentDetailId.setIncidentId(Integer.parseInt(incidentId));
				incidentDetailId.setSerialNo(incidentMaxSerialNumber);
				
				incidentDetails =  ticketDao.getIncidentDetails(incidentDetailId);
				incidentDetails.setStatus(ticketDao.getIncidentStatus(incidentDetailId).getStatusCode());
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("returning the incident details");
		return incidentDetails;
	}

	/**
	 * This method fetches the series of track for an incident
	 */
	@Transactional
	public List<IncidentTrack> getIncidentTrackDetails(
			IncidentDetailId incidentDetailId) throws Throwable {
		List<IncidentTrack> listIncidentTrack = null;
		try{
			listIncidentTrack = ticketDao.getIncidentTrackDetails(incidentDetailId);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return listIncidentTrack;
	}


}
