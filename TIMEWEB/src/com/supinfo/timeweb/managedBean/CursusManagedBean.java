package com.supinfo.timeweb.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Cursus;
import com.supinfo.timetableasy.entity.Role;
import com.supinfo.timetableasy.entity.User;
import com.supinfo.timetableasy.facade.FacadeRemote;

public class CursusManagedBean {

	public FacadeRemote facade;
	public List<Cursus> allCursus;
	public String nom;
	public List<User> allResponsables;
	public List<User> responsables = new ArrayList<User>();
	public List<SelectItem> allResponsablesSL;
	public List<String> responsableSL = new ArrayList<String>();
	public List<User> anciensResponsables;
	public Cursus cursusTemp;

	public String listCursus() {
		return "listCursus";
	}

	public String persistCursus() {

		Cursus newcursus = new Cursus();
		newcursus.setNom(nom);
		responsables = getResponsables();
		for (String nomUser : getResponsableSL()) {
			User user = new User();
			user = facade.getUserByName(nomUser);
			responsables.add(user);
		}
		facade.addCursus(newcursus, responsables, null);
		responsables = new ArrayList<User>();
		responsableSL = new ArrayList<String>();
		initializList();
		setNom("");
		// setAllResponsables(new ArrayList<User>());
		// setAllResponsablesSL(new ArrayList<SelectItem>());

		return "listCursus";
	}

	public String mergeCursus() {

		Cursus newcursus = new Cursus();
		newcursus = facade.getCursusByName(cursusTemp.getNom());
		newcursus.setNom(nom);
		for (String nomUser : getResponsableSL()) {
			User user = new User();
			user = facade.getUserByName(nomUser);
			responsables.add(user);
		}
		facade.updateCursus(newcursus, responsables, newcursus
				.getPeriodeDetudes());
		responsables = new ArrayList<User>();
		responsableSL = new ArrayList<String>();
		setNom("");
		return "listCursus";
	}

	public String removeCursus() {
		try {
			facade = getFacade();
			Cursus cursus = facade.getCursusByName(nom);
			facade.removeCursus(cursus);
			setNom("");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String updateCursus() {
		try {
			cursusTemp = new Cursus();
			cursusTemp.setNom(nom);
			facade = getFacade();
			Cursus cursus = facade.getCursusByName(nom);
			setNom(cursus.getNom());
			setAnciensResponsables(cursus.getUsers());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "updateCursus";
	}

	public List<Cursus> getAllCursus() {

		try {
			facade = getFacade();
			allCursus = facade.listCursus();
			return allCursus;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setAllCursus(List<Cursus> allCursus) {
		this.allCursus = allCursus;
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

	public List<User> getResponsables() {
		return responsables;
	}

	public void setResponsables(List<User> responsables) {
		this.responsables = responsables;
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

	public List<String> getResponsableSL() {
		return responsableSL;
	}

	public void setResponsableSL(List<String> responsableSL) {
		this.responsableSL = responsableSL;
	}

	public List<User> getAnciensResponsables() {
		return anciensResponsables;
	}

	public void setAnciensResponsables(List<User> anciensResponsables) {
		this.anciensResponsables = anciensResponsables;
	}

	public List<User> getAllResponsables() {
		try {
			facade = getFacade();
			Role role = facade.findRoleByTitre("intervenant");
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

	public void initializList() {
		setAllResponsables(new ArrayList<User>());
		setAllResponsablesSL(new ArrayList<SelectItem>());
		setResponsables(new ArrayList<User>());
		setResponsableSL(new ArrayList<String>());
	}

	public Cursus getCursusTemp() {
		return cursusTemp;
	}

	public void setCursusTemp(Cursus cursusTemp) {
		this.cursusTemp = cursusTemp;
	}

}
