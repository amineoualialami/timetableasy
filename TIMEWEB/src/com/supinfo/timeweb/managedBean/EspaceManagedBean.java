package com.supinfo.timeweb.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Espace;
import com.supinfo.timetableasy.entity.User;
import com.supinfo.timetableasy.facade.FacadeRemote;

public class EspaceManagedBean {

	public String nom;
	public FacadeRemote facade;
	public User responsable;
	public List<Campus> listCampus;
	public List<SelectItem> listCampusSL;
	public Long campusSelected;
	public List<Espace> listEspacess;

	public String listEspaces() {

		String navig = "espacesError";
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);
			responsable = (User) session.getAttribute("AuthentifiedUser");
			System.out.println("le user authentifier" + responsable.getNom());
			facade = getFacade();
			listCampus = new ArrayList<Campus>();
			List<Campus> allcampus = facade.listAllCampus();
			for (Campus campus : allcampus) {
				for (User responsableTemp : campus.getUsers()) {
					if (responsableTemp.getNom().equals(responsable.getNom())) {
						System.out.println(" user trouvé :"
								+ responsableTemp.getNom());
						System.out.println(" campus : " + campus.getNom());
						listCampus.add(campus);
						navig = "listEspaces";
					}
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return navig;
	}

	public void removeEspace() {
		try {
			facade = getFacade();
			Campus campus = facade.findCampus(campusSelected);
			List<Espace> newEspaces = campus.getEspaces();
			Espace espaceDel = new Espace();
			for (Espace espace : newEspaces) {
				if (espace.getNom().equals(nom)) {
					espaceDel = espace;
				}
			}
			newEspaces.remove(espaceDel);
			facade.updateCampus(campus, campus.getUsers(), newEspaces);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String persistEspace() {

		try {
			facade = getFacade();
			Campus campus = facade.findCampus(campusSelected);
			List<Espace> newEspaces = campus.getEspaces();
			Espace espaceAdd = new Espace();
			espaceAdd.setNom(nom);
			newEspaces.add(espaceAdd);
			facade.updateCampus(campus, campus.getUsers(), newEspaces);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listEspaces";
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

	public User getResponsable() {
		return responsable;
	}

	public void setResponsable(User responsable) {
		this.responsable = responsable;
	}

	public List<Campus> getListCampus() {
		return listCampus;
	}

	public void setListCampus(List<Campus> listCampus) {
		this.listCampus = listCampus;
	}

	public List<SelectItem> getListCampusSL() {

		listCampusSL = new ArrayList<SelectItem>();
		for (Campus campus : getListCampus()) {
			SelectItem e = new SelectItem(campus.getIdCampus(), campus.getNom());
			listCampusSL.add(e);
		}

		return listCampusSL;
	}

	public void setListCampusSL(List<SelectItem> listCampusSL) {
		this.listCampusSL = listCampusSL;
	}

	public Long getCampusSelected() {
		return campusSelected;
	}

	public void setCampusSelected(Long campusSelected) {
		this.campusSelected = campusSelected;
	}

	public void test() {
		setCampusSelected(campusSelected);
	}

	public List<Espace> getListEspacess() {

		try {
			if (getCampusSelected() == null) {
				System.out.println("rien selectionné daba");
				facade = getFacade();
				listEspacess = facade.listEspacesByCampus(getListCampus()
						.get(0));
				return listEspacess;
			} else {
				System.out.println("daba un campus selectioné");
				listEspacess = facade.listEspacesByCampus(facade
						.findCampus(getCampusSelected()));
				return listEspacess;
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void setListEspacess(List<Espace> listEspacess) {
		this.listEspacess = listEspacess;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
