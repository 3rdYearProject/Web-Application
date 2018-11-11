package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.Journey;

@ManagedBean
@SessionScoped
public class JourneyController {

	private List<Journey> journeys;
	private JourneyDAO journeyDAO;
	
	
	public JourneyController() throws Exception {
		journeys = new ArrayList<>();
		
		journeyDAO = JourneyDAO.getInstance();
	}
	
	public List<Journey> getJourneys() {
		return journeys;
	}
	
	/**
	 * Method to get all Journeys
	 */
	public void loadJourneys() {
		journeys.clear();
		try {
			// get all journeys from DB
			journeys = journeyDAO.getJourneys();
		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
		}
	}
	
	/**
	 * Method to add a new Journey
	 * @param newJourney
	 * @return
	 */
	public String addJourney(Journey journey) {
		try {
			// add journey to the database
			journeyDAO.addJourney(journey);

		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
			return null;
		}

		return "journey-table?faces-redirect=true";
	}
	
	public String loadJourney(int journeyID) {
		
		try {
			// get journey from DB
			Journey journey = journeyDAO.getJourney(journeyID);
			
			// request attributefor form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("journey", journey);	
			
		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-journey-form.xhtml";
	}	
	
	/**
	 * Method to update Journey
	 * @param journey Journey to update
	 * @return
	 */
	public String updateJourney(Journey journey) {

		try {
			
			// update journey in the database
			journeyDAO.updateJourney(journey);
			
		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "journey_table?faces-redirect=true";		
	}
	
	/**
	 * Method to delete journey from DB
	 * @param journeyID id of journey to delete
	 * @return journey_table
	 */
	public String deleteJourney(int journeyID) {
		try {
			// delete journey from DB
			journeyDAO.deleteJourney(journeyID);
			
		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
			return null;
		}
		return "journey_table";	
	}	
		
	/**
	 * Error message
	 * @param exc
	 */
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	
}
