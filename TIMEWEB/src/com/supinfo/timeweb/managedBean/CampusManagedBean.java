package com.supinfo.timeweb.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Role;
import com.supinfo.timetableasy.entity.User;
import com.supinfo.timetableasy.facade.FacadeRemote;

public class CampusManagedBean {

	public String nom;
	public FacadeRemote facade;
	public List<Campus> allCampus;
	public List<User> allResponsables;
	public List<User> responsables = new ArrayList<User>();
	public List<SelectItem> allResponsablesSL;
	public List<String> responsableSL = new ArrayList<String>();
	public List<User> anciensResponsables;
	public Campus campusTemp;

	public String listCampus() {
		return "listCampus";
	}

	public String persistCampus() {

		Campus newCampus = new Campus();
		newCampus.setNom(nom);
		responsables = getResponsables();
		for (String nomUser : getResponsableSL()) {
			User user = new User();
			user = facade.getUserByName(nomUser);
			responsables.add(user);
		}
		facade.addCampus(newCampus, responsables, null);
		responsables = new ArrayList<User>(); 
		responsableSL = new ArrayList<String>();
		setNom("");
		return "listCampus";
	}

	public String mergeCampus() {

		try {
			facade = getFacade();
			Campus campus = facade.getCampusByNom(campusTemp.getNom());
			campus.setNom(nom);
			for (String nomUser : getResponsableSL()) {
				User user = new User();
				user = facade.getUserByName(nomUser);
				responsables.add(user);
			}
			facade.updateCampus(campus, responsables, campus.getEspaces());
			responsables = new ArrayList<User>();
			responsableSL = new ArrayList<String>();
			setNom("");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "listCampus";
	}

	public String removeCampus() {
		try {
			facade = getFacade();
			Campus campus = facade.getCampusByNom(nom);
			facade.removeCampus(campus);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String updateCampus() {

		try {
			facade = getFacade();
			Campus campus = facade.getCampusByNom(nom);
			campusTemp = new Campus();
			campusTemp.setNom(nom);
			setNom(campus.getNom());
			setAnciensResponsables(campus.getUsers());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "updateCampus";
	}

	public List<String> getResponsableSL() {
		return responsableSL;
	}

	public void setResponsableSL(List<String> responsableSL) {
		this.responsableSL = responsableSL;
	}

	public List<SelectItem> getAllResponsablesSL() {
		allResponsablesSL = new ArrayList<SelectItem>();
		for (User user : getAllResponsables()) {
			SelectItem e = new SelectItem(user.getNom(), user.getNom());
			allResponsablesSL.add(e);
		}

		return allResponsablesSL;
	}

	public void setAllResponsablesSL(List<SelectItem> allResponsablesSL) {
		this.allResponsablesSL = allResponsablesSL;
	}

	public List<User> getAllResponsables() {
		try {
			facade = getFacade();
			Role role = facade.findRoleByTitre("campus manager");
			allResponsables = facade.listUsersByRole(role);
			return allResponsables;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void setAllResponsables(List<User> allResponsables) {
		this.allResponsables = allResponsables;
	}

	public List<User> getResponsables() {
		return responsables;
	}

	public void setResponsables(List<User> responsables) {
		this.responsables = responsables;
	}

	public List<Campus> getAllCampus() {
		try {
			facade = getFacade();
			allCampus = facade.listAllCampus();
			return allCampus;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setAllCampus(List<Campus> allCampus) {
		this.allCampus = allCampus;
	}

	public FacadeRemote getFacade() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs",
				"=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");
		InitialContext context = new InitialContext(properties);
		facade = (FacadeRemote) context.lookup("facadeSB/remote");
		return facade;
	}

	public void setFacade(FacadeRemote facade) {
		this.facade = facade;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<User> getAnciensResponsables() {
		return anciensResponsables;
	}

	public void setAnciensResponsables(List<User> anciensResponsables) {
		this.anciensResponsables = anciensResponsables;
	}

	public Campus getCampusTemp() {
		return campusTemp;
	}

	public void setCampusTemp(Campus campusTemp) {
		this.campusTemp = campusTemp;
	}

}
