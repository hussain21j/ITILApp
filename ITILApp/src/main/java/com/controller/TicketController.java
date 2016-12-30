package com.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.mapping.Value;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.model.IncidentDetailId;
import com.model.IncidentDetails;
import com.model.IncidentMaster;
import com.model.IncidentSeverity;
import com.model.IncidentTrack;
import com.model.InstitutionMapping;
import com.model.OriginationMedium;
import com.propertyEditor.DropDownNullPropertyEditor;
import com.propertyEditor.StringToIntPropertyEditor;
import com.service.IncidentService;
import com.service.StudentService;
import com.service.TicketService;


@Controller
@RequestMapping(value = "/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	/**
	 * Init binder going to take care of type conversion etc
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    webDataBinder.registerCustomEditor(Date.class, "incidentRaisedOn", new CustomDateEditor(dateFormat, true));
	    webDataBinder.registerCustomEditor(Date.class, "incidentClosedOn", new CustomDateEditor(dateFormat, true));
	    webDataBinder.registerCustomEditor(String.class,"institutionType", new DropDownNullPropertyEditor());
	    webDataBinder.registerCustomEditor(String.class,"institutionName", new DropDownNullPropertyEditor());
	    webDataBinder.registerCustomEditor(String.class,"incidentStatus", new DropDownNullPropertyEditor());
	    webDataBinder.registerCustomEditor(String.class,"incidentPriority", new DropDownNullPropertyEditor()); //priority
	    webDataBinder.registerCustomEditor(String.class,"incidentSeverity", new DropDownNullPropertyEditor()); //severity
	    webDataBinder.registerCustomEditor(Integer.class,"userContactNumber", new StringToIntPropertyEditor());
	}
	
	@RequestMapping(value = "/getNewTicket", method = RequestMethod.GET)
	public String getNewTicket(Map<String, Object> map, Principal principal){
		System.out.println("starting method getNewTicket handller method");
		IncidentDetails incidentDetails = new IncidentDetails();
		
		map.put("originationMediumList", ticketService.getListOriginationMedium());
		map.put("incidentCategoryList", ticketService.getListOfIncidentCategory());
		map.put("incidentSubCategoryList", ticketService.getListOfIncidentSubCategory());
		map.put("institutionTypeList", ticketService.getListOfInstitutionType());
		map.put("incidentStatusList", ticketService.getListOfIncidentStatus());
		map.put("incidentPriorityList", ticketService.getListOfIncidenPriority());
		map.put("incidentSeverityList", ticketService.getListOfIncidenSeverity());
		
		
		map.put("incidentDetails", incidentDetails);
		System.out.println("returning from getNewTicket");
		return "newTicket";
	}
	
	
	@RequestMapping(value = "/institutionListAjax", method = RequestMethod.POST)
	public @ResponseBody String  getInstitutionList(@RequestParam (value = "institutionTypeId", required = true) String institutionTypeId) {
		System.out.println("getInstitutionList handeler method institutionTypeId:"+institutionTypeId);
		//return institutionMapping;
		return new Gson().toJson(ticketService.getListOfInstitution(institutionTypeId));
	}
	
	@RequestMapping(value = "/raiseNewTicket", method = RequestMethod.POST)
	public String raiseNewTicket(@Valid @ModelAttribute("incidentDetails") IncidentDetails incidentDetails, BindingResult bindingResult, Map<String, Object> model){
		int status = 0;
		if(bindingResult.hasErrors()){
			model.put("originationMediumList", ticketService.getListOriginationMedium());
			model.put("incidentCategoryList", ticketService.getListOfIncidentCategory());
			model.put("incidentSubCategoryList", ticketService.getListOfIncidentSubCategory());
			model.put("institutionTypeList", ticketService.getListOfInstitutionType());
			model.put("incidentStatusList", ticketService.getListOfIncidentStatus());
			model.put("incidentPriorityList", ticketService.getListOfIncidenPriority());
			model.put("incidentSeverityList", ticketService.getListOfIncidenSeverity());
			
			model.put("incidentDetails", incidentDetails);
			
			System.out.println("returning from raiseNewTicket");
			return "newTicket";
		}
		else{
			System.out.println("stating method handeller raiseNewTicket");
			try{
				status = ticketService.registerNewIncident(incidentDetails);
				System.out.println("registratino output :"+status);
				model.put("registrationStatus", "successfull");
			}
			catch (Throwable throwable) {
				model.put("registrationStatus", "failure");
				System.out.println("Exception in registering new record");
			}
			
		        
		    return "incidentRegisterResponse";
		}
		
	}
	
	@RequestMapping(value = "/getUserWorkList", method = RequestMethod.GET)
	public String getUserWorkList(Map<String, Object> map, Principal principal){
		System.out.println("principal logged in user :"+principal.getName());
		String openStatus = "1";
		ArrayList<IncidentDetails> listIncidentDetails = null;
		listIncidentDetails = (ArrayList<IncidentDetails>) ticketService.getUserWorkList(Integer.parseInt(principal.getName()), openStatus);
		map.put("listIncidentDetails", listIncidentDetails);
		return "workList";
	}
	
	@RequestMapping(value = "/getTicketDetails", method = RequestMethod.GET)
	public String getTicketDetails(@RequestParam (value = "ticketId", required = true) String ticketId, Map<String, Object> map, Principal principal){
		System.out.println("starting getTicketDetails action");
		System.out.println("ticket id :"+ticketId);
		IncidentDetails incidentDetails =  null;
		List<IncidentTrack> listIncidentTrack = null;
		IncidentDetailId incidentDetailId = null;
		try{
			incidentDetails = ticketService.getIncidentDetails(ticketId);
			System.out.println("incident id :"+incidentDetails.getIncidentDetailId().getIncidentId() + "   :serail no :"+incidentDetails.getIncidentDetailId().getSerialNo());
			//incidentDetails.setOrigMedium(Integer.parseInt(incidentDetails.getOriginationMedium().getOriginationMediumCode()));
			
			//get Incident track list
			listIncidentTrack = ticketService.getIncidentTrackDetails(incidentDetails.getIncidentDetailId());
			
			map.put("originationMediumList", ticketService.getListOriginationMedium());
			map.put("incidentCategoryList", ticketService.getListOfIncidentCategory());
			map.put("incidentSubCategoryList", ticketService.getListOfIncidentSubCategory());
			map.put("institutionTypeList", ticketService.getListOfInstitutionType());
			map.put("incidentStatusList", ticketService.getListOfIncidentStatus());
			map.put("incidentPriorityList", ticketService.getListOfIncidenPriority());
			map.put("incidentSeverityList", ticketService.getListOfIncidenSeverity());
			
			
			map.put("incidentDetails", incidentDetails);
			map.put("listIncidentTrack", listIncidentTrack);
			
			System.out.println("returning from getNewTicket \n" +
					"origination medium : "+incidentDetails.getOriginationMedium().getOriginationMediumCode() + "Desc :"+incidentDetails.getOriginationMedium().getOriginationMediumDesc());
		}
		catch (Throwable throwable) {
			//handle exception here
			System.out.println("Exception in getting incident details");
		}
		//map.put("selectedIncidentDetails", incidentDetails);
		return "ticketDetails";
	}
	
	
	public static void main(String[] args) throws ParseException {
		String sdate =  "2016-01-12";//"2016-01-12";
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
		  Date date =  (Date) dateFormat.parse(sdate);
		 System.out.println("date :"+date);
		 
		
	}
}
