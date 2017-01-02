package com.dao.Impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.TicketDao;
import com.model.IncidentCategory;
import com.model.IncidentDetailId;
import com.model.IncidentDetails;
import com.model.IncidentMaster;
import com.model.IncidentPriority;
import com.model.IncidentSequence;
import com.model.IncidentSeverity;
import com.model.IncidentStatus;
import com.model.IncidentSubCategory;
import com.model.IncidentTrack;
import com.model.InstitutionMapping;
import com.model.InstitutionType;
import com.model.OriginationMedium;
import com.util.ITILConstantUtil;

@Repository
public class TicketDaoImpl implements TicketDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<OriginationMedium> listOriginationMedium(){
		return sessionFactory.getCurrentSession().createQuery("from OriginationMedium").list();
	}

	public List<IncidentCategory> listIncidentCategory() {
		return sessionFactory.getCurrentSession().createQuery("from IncidentCategory").list();
	}

	public List<IncidentSubCategory> listIncidentSubCategory() {
		return sessionFactory.getCurrentSession().createQuery("from IncidentSubCategory").list();
	}

	public List<InstitutionType> listInstitutionType() {
		return sessionFactory.getCurrentSession().createQuery("from InstitutionType").list();
	}

	public List<InstitutionMapping> getListOfInstitution(String institutionTypeCode) {
		return sessionFactory.getCurrentSession().createQuery("from InstitutionMapping IM where IM.institutionType = '"+institutionTypeCode+"'").list();
	}

	public InstitutionMapping getInstitution(String institutionId) {
		//return (InstitutionMapping) sessionFactory.getCurrentSession().createQuery("from InstitutionMapping IM where IM.institutionCode = '"+institutionId+"'");
		return (InstitutionMapping) sessionFactory.getCurrentSession().get(InstitutionMapping.class, institutionId);
	}

	public List<IncidentStatus> getIncidentStatusList() {
		return sessionFactory.getCurrentSession().createQuery("from IncidentStatus").list();
	}

	public List<IncidentPriority> getIncidentPriorityList() {
		return sessionFactory.getCurrentSession().createQuery("from IncidentPriority").list();
	}

	public List<IncidentSeverity> getIncidentSeverityList() {
		return sessionFactory.getCurrentSession().createQuery("from IncidentSeverity").list();
	}

	public int getNewIncidentNo() throws Throwable {
		String hQuery = "";
		int newIncidenteNo = 0;;
		IncidentSequence incidentSequence;
		int incidentCount;
		
		hQuery = "SELECT MAX(INCIDENT.nextSequenceNumber) FROM IncidentSequence INCIDENT";
		
		try{
			incidentCount = getIncidentCount();
			if(incidentCount == 0){
				newIncidenteNo  =1;
			}
			else {
				newIncidenteNo = Integer.parseInt(sessionFactory.getCurrentSession().createQuery(hQuery).list().get(0).toString());
				newIncidenteNo ++; //increase the incident number by one
			}
			//persist the new incident number, but commit will be done in parent method
			incidentSequence = new IncidentSequence();
			incidentSequence.setNextSequenceNumber(newIncidenteNo);
			sessionFactory.getCurrentSession().save(incidentSequence);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//do nothing
		}
		return newIncidenteNo;
	}

	public int getIncidentCount() throws Throwable {
		int totalrecords = 0;
		Criteria criteria;
		List list;
		try{
			criteria =  sessionFactory.getCurrentSession().createCriteria(IncidentSequence.class);
			criteria.setProjection(Projections.rowCount()).uniqueResult();
			list =  criteria.list();
			totalrecords = Integer.parseInt(list.get(0).toString());
			System.out.println("total records found :"+totalrecords);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//do nothing  
		}
		return totalrecords;
	}

	public boolean registerNewIncident(IncidentTrack incidentTrack)
			throws Throwable {
		boolean processStatus = false;
		try{
			sessionFactory.getCurrentSession().save(incidentTrack);
			processStatus = true;
		}
		catch (Exception e) {
			processStatus = false;
			e.printStackTrace();
		}
		return processStatus;
	}

	public boolean registerIncidentMaster(IncidentMaster incidentMaster)
			throws Throwable {
		boolean processStatus = false;
		try{
			sessionFactory.getCurrentSession().save(incidentMaster);
			processStatus = true;
		}
		catch (Exception e) {
			processStatus = false;
			e.printStackTrace();
		}
		return processStatus;
	}
	

	public List<IncidentDetails> getUserWorkList(String userId) {
		String hQuery = "";
		ArrayList<IncidentDetails> alIncidentDetails = null;
		
		hQuery = "SELECT ID.IncidentDetailId, ID.incidentCategory, ID.incidentSubCategory " +
				"FROM IncidentDetails ID, ITIL_INCIDENT_MASTER IM FETCH ALL PROPERTIES WHERE IM.assignedUser = :loggedInUser AND IM.status = :incidentstatus";
		try{
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return alIncidentDetails;
	}

	/**
	 * Thsi method fetches all the records from the incident master which are assigned to a specific user
	 */
	public List<IncidentMaster> getIncidentMasterRecordWithStatus(
			 int boundUser, String incidentStatus) {
		System.out.println("dao impl getIncidentMasterRecordWithStatus");
		
		String hQuery = "";
		ArrayList<IncidentMaster> listIncidentMaster = null;
		
		hQuery = "FROM IncidentMaster IM WHERE IM.user.username = '"+boundUser+"' AND IM.incidentStatus.statusCode = '"+incidentStatus+"'";
		try{
			listIncidentMaster = (ArrayList<IncidentMaster>) sessionFactory.getCurrentSession().createQuery(hQuery).list();
			System.out.println("list size : "+listIncidentMaster.size());
			for(IncidentMaster im : listIncidentMaster){
				System.out.println("Incident master details    incident id : "+im.getIncidentDetailId().getIncidentId()+" status : "+im.getStatus()+" bound user : "+im.getAssignedUser());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return listIncidentMaster;
	}
	

	/**
	 * This method fetches the incident category object with a given incident category
	 */
	public IncidentCategory getIncidentCategoryCodeDescription(String incidentcategoryCode) throws Throwable{
		IncidentCategory incidentCategory = null;
		try{
			incidentCategory =  (IncidentCategory) sessionFactory.getCurrentSession().get(IncidentCategory.class, incidentcategoryCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return incidentCategory;
	}

	/**
	 * This method fetches the incident sub category object with a given incident sub category
	 */
	public IncidentSubCategory getIncidentSubCategoryCodeDescription(
			String incidentSubCategoryCode) throws Throwable {
		IncidentSubCategory incidentSubCategory = null;
		try{
			incidentSubCategory =  (IncidentSubCategory) sessionFactory.getCurrentSession().get(IncidentSubCategory.class, incidentSubCategoryCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return incidentSubCategory;
	}

	/**
	 * This method fetches the origination medium object with a given incident sub category
	 */
	public OriginationMedium getOriginationMediumCodeDescription(
			String originationMediumCode) throws Throwable {
		OriginationMedium originationMedium = null;
		try{
			originationMedium =  (OriginationMedium) sessionFactory.getCurrentSession().get(OriginationMedium.class, originationMediumCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return originationMedium;
	}

	/**
	 * This method fetches the institution type object with a given incident sub category
	 */
	public InstitutionType getInstitutionTypeCodeDescription(
			String institutionTypeCode) throws Throwable {
		InstitutionType institutionType = null;
		try{
			institutionType =  (InstitutionType) sessionFactory.getCurrentSession().get(InstitutionType.class, institutionTypeCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return institutionType;
	}

	/**
	 * This method fetches the institution mapping object with a given incident sub category
	 */
	public InstitutionMapping getInstitutionMapping(String institutionCode)
			throws Throwable {
		InstitutionMapping institutionMapping = null;
		try{
			institutionMapping =  (InstitutionMapping) sessionFactory.getCurrentSession().get(InstitutionMapping.class, institutionCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return institutionMapping;
	}

	/**
	 * This method fetches the priority with a given incident
	 */
	public IncidentPriority getPriority(String priorityCode) throws Throwable {
		IncidentPriority incidentPriority = null;
		try{
			incidentPriority =  (IncidentPriority) sessionFactory.getCurrentSession().get(IncidentPriority.class, priorityCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return incidentPriority;
	}

	/**
	 * This method fetches the priority with a given incident
	 */
	public IncidentSeverity getSeverity(String sevirityCode) throws Throwable {
		IncidentSeverity incidentSeverity = null;
		try{
			incidentSeverity =  (IncidentSeverity) sessionFactory.getCurrentSession().get(IncidentSeverity.class, sevirityCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return incidentSeverity;
	}
	
	
	/**
	 * This method fetches the incident details with a given incidentdetailId
	 */
	public IncidentDetails getIncidentDetails(IncidentDetailId incidentDetailId){
		return (IncidentDetails) sessionFactory.getCurrentSession().get(IncidentDetails.class, incidentDetailId);
	}
	
	/**
	 * This method feteches the maximum serial number for a given incident id
	 */
	public int getMaxSerialNumber(int incidentId) throws Throwable {
		IncidentDetailId incidentDetailId = null;
		String hQuery;
		org.hibernate.Query query;
		int status = 0;
		
		hQuery = "SELECT MAX(id.incidentDetailId.serialNo) FROM IncidentDetails id WHERE id.incidentDetailId.incidentId = :INCIDENT_ID";
		
		try{
			incidentDetailId = new IncidentDetailId();
			incidentDetailId.setIncidentId(incidentId);
			incidentDetailId.setSerialNo(ITILConstantUtil.INCIDENT_SEQUENCE_NUMBER_ONE);
			
			if(validateIncidentExist(incidentDetailId)){
				query = sessionFactory.getCurrentSession().createQuery(hQuery);
				query.setInteger("INCIDENT_ID", incidentId);
				status = Integer.parseInt(query.list().get(0).toString());
			}
			else{
				status = -1;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public boolean validateIncidentExist(IncidentDetailId incidentDetailId) throws Throwable{
		boolean status = false;
		IncidentDetails incidentDetails = null;
		try{
			incidentDetails =  (IncidentDetails) sessionFactory.getCurrentSession().get(IncidentDetails.class, incidentDetailId);
			if(null != incidentDetails){
				status = true;
				System.out.println("Incident Id: "
						+ incidentDetails.getIncidentDetailId().getIncidentId()
						+ " Serial no :"
						+ incidentDetails.getIncidentDetailId().getSerialNo());
			}
			else {
				status = false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<IncidentTrack> getIncidentTrackDetails(IncidentDetailId incidentDetailId)
			throws Throwable {
		String hQuery;
		ArrayList<IncidentTrack> listIncidentTrack = null;
		org.hibernate.Query query;
		
		hQuery = "from IncidentTrack IT where IT.incidentDetailId.incidentId =:INCIDENT_ID"; //INCIDENT_ID is a named parameter
		try{
			query = sessionFactory.getCurrentSession().createQuery(hQuery);
			query.setInteger("INCIDENT_ID", incidentDetailId.getIncidentId());
			listIncidentTrack = (ArrayList<IncidentTrack>) query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return listIncidentTrack;
	}

	
	/**
	 * This method gets the incident track status of an incident id and serail no
	 */
	public IncidentStatus getIncidentStatus(
			IncidentDetailId incidentDetailId) throws Throwable {
		IncidentStatus incidentStatus = null;
		IncidentTrack incidentTrack = null;
		try{
			incidentTrack = (IncidentTrack) sessionFactory.getCurrentSession().get(IncidentTrack.class, incidentDetailId);
			incidentStatus = incidentTrack.getIncidentStatus();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return incidentStatus;
	}

	/**
	 * This method returns the incident status provided incident status code is given
	 */
	public IncidentStatus getIncidentStatusCodeDesc(String incidentStatusCode)
			throws Throwable {
		IncidentStatus incidentStatus = null;
		try{
			incidentStatus =  (IncidentStatus) sessionFactory.getCurrentSession().get(IncidentStatus.class, incidentStatusCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return incidentStatus;
	}
}
