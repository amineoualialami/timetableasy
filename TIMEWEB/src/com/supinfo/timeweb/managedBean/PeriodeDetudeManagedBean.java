package com.supinfo.timeweb.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import com.supinfo.timetableasy.entity.Cour;
import com.supinfo.timetableasy.entity.Cursus;
import com.supinfo.timetableasy.entity.PeriodeDetude;
import com.supinfo.timetableasy.entity.User;
import com.supinfo.timetableasy.facade.FacadeRemote;

/**
 * @author Administrateur
 * 
 */
public class PeriodeDetudeManagedBean {

	public String nom;
	public FacadeRemote facade;
	public User responsable;
	public List<Cursus> listCursus;
	public List<SelectItem> listCursusSL;
	public Long cursusSelected;
	public List<PeriodeDetude> listPeriodeDetudes;
	public Date dateDebut;
	public Date dateFin;
	public List<Cour> listCours;
	public List<SelectItem> allCoursSL;
	public List<String> courSL = new ArrayList<String>();
	public List<Cour> cours = new ArrayList<Cour>();
	public List<Cour> coursActuel = new ArrayList<Cour>();

	public String listPeriodeDetude() {

		String navig = "periodeDetudeError";
		courSL = new ArrayList<String>();
		cours = new ArrayList<Cour>();
		setNom("");
		dateDebut = new Date();
		dateFin = new Date();
		try {
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);
			responsable = (User) session.getAttribute("AuthentifiedUser");
			System.out.println("le user authentifier" + responsable.getNom());
			facade = getFacade();
			listCursus = new ArrayList<Cursus>();
			List<Cursus> allcursus = facade.listCursus();

			for (Cursus cursus : allcursus) {
				for (User responsableTemp : cursus.getUsers()) {
					if (responsableTemp.getNom().equals(responsable.getNom())) {
						listCursus.add(cursus);
						navig = "listPeriodeDetude";
					}
				}
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return navig;
	}

	public String persistPeriodeDetude() {
		try {
			Cursus cursus = new Cursus();
			if (cursusSelected == null) {
				cursus = getListCursus().get(0);
			} else {
				facade = getFacade();
				cursus = facade.FindCursus(cursusSelected);
			}
			List<PeriodeDetude> newPeriodeDetudes = cursus.getPeriodeDetudes();
			PeriodeDetude periodeDetudeAdd = new PeriodeDetude();
			periodeDetudeAdd.setNom(nom);
			periodeDetudeAdd.setDateDebut(dateDebut);
			periodeDetudeAdd.setDateFin(dateFin);
			cours = getCours();
			for (String nomCour : getCourSL()) {
				Cour cour = new Cour();
				cour = facade.listCourByName(nomCour);
				cours.add(cour);
			}
			periodeDetudeAdd.setCours(cours);
			newPeriodeDetudes.add(periodeDetudeAdd);
			facade.updateCursus(cursus, cursus.getUsers(), newPeriodeDetudes);
			courSL = new ArrayList<String>();
			cours = new ArrayList<Cour>();
			setNom("");
			dateDebut = new Date();
			dateFin = new Date();
			return "listPeriodeDetude";

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void removePeriodeDetude() {
		try {

			Cursus cursus = new Cursus();
			if (cursusSelected == null) {
				cursus = getListCursus().get(0);
			} else {
				facade = getFacade();
				cursus = facade.FindCursus(cursusSelected);
			}

			List<PeriodeDetude> pd = cursus.getPeriodeDetudes();
			PeriodeDetude pdDelete = new PeriodeDetude();
			for (PeriodeDetude periodeDetude : pd) {
				if (periodeDetude.getNom().equals(nom)) {
					pdDelete = periodeDetude;
				}
			}
			pd.remove(pdDelete);
			facade.updateCursus(cursus, cursus.getUsers(), pd);
			courSL = new ArrayList<String>();
			cours = new ArrayList<Cour>();
			setNom("");
			dateDebut = new Date();
			dateFin = new Date();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String detailsPeriodeDetude() {

		try {
			Cursus cursus = new Cursus();
			if (cursusSelected == null) {
				cursus = getListCursus().get(0);
			} else {
				facade = getFacade();
				cursus = facade.FindCursus(cursusSelected);
			}

			List<PeriodeDetude> pds = facade.listPeriodeDetudesByCursus(cursus);
			PeriodeDetude pd = new PeriodeDetude();
			for (PeriodeDetude periodeDetude : pds) {
				if (periodeDetude.getNom().equals(nom)) {
					pd = periodeDetude;
				}
			}
			setNom(pd.getNom());
			setDateDebut(pd.getDateDebut());
			setDateFin(pd.getDateFin());
			setCoursActuel(pd.getCours());

			return "detailsPeriodeDetude";

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String updatePeriodeDetude(){
		try {
			Cursus cursus = new Cursus();
			if (cursusSelected == null) {
				cursus = getListCursus().get(0);
			} else {
				facade = getFacade();
				cursus = facade.FindCursus(cursusSelected);
			}

			List<PeriodeDetude> pds = facade.listPeriodeDetudesByCursus(cursus);
			PeriodeDetude pd = new PeriodeDetude();
			for (PeriodeDetude periodeDetude : pds) {
				if (periodeDetude.getNom().equals(nom)) {
					pd = periodeDetude;
				}
			}
			setNom(pd.getNom());
			setDateDebut(pd.getDateDebut());
			setDateFin(pd.getDateFin());
			setCoursActuel(pd.getCours());

			return "updatePeriodeDetude";

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String mergePeriodeDetude(){
		try {
			Cursus cursus = new Cursus();
			if (cursusSelected == null) {
				cursus = getListCursus().get(0);
			} else {
				facade = getFacade();
				cursus = facade.FindCursus(cursusSelected);
			}
			List<PeriodeDetude> newPeriodeDetudes = cursus.getPeriodeDetudes();
			PeriodeDetude periodeDetudeAdd = new PeriodeDetude();
			PeriodeDetude periodeDetudeDel = new PeriodeDetude();
			periodeDetudeAdd.setNom(nom);
			periodeDetudeAdd.setDateDebut(dateDebut);
			periodeDetudeAdd.setDateFin(dateFin);
			cours = getCours();
			for (String nomCour : getCourSL()) {
				Cour cour = new Cour();
				cour = facade.listCourByName(nomCour);
				cours.add(cour);
			}
			periodeDetudeAdd.setCours(cours);
			for (PeriodeDetude periodeDetude : newPeriodeDetudes) {
				if(periodeDetude.getNom().equals(periodeDetudeAdd.getNom())){
					periodeDetudeDel=periodeDetude;
				}
			}
			newPeriodeDetudes.remove(periodeDetudeDel);
			newPeriodeDetudes.add(periodeDetudeAdd);
			facade.updateCursus(cursus, cursus.getUsers(), newPeriodeDetudes);
			courSL = new ArrayList<String>();
			cours = new ArrayList<Cour>();
			setNom("");
			dateDebut = new Date();
			dateFin = new Date();
			return "listPeriodeDetude";

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public List<Cursus> getListCursus() {
		return listCursus;
	}

	public void setListCursus(List<Cursus> listCursus) {
		this.listCursus = listCursus;
	}

	public List<SelectItem> getListCursusSL() {

		listCursusSL = new ArrayList<SelectItem>();
		for (Cursus cursus : getListCursus()) {
			SelectItem e = new SelectItem(cursus.getIdCursus(), cursus.getNom());
			listCursusSL.add(e);
		}
		return listCursusSL;
	}

	public void setListCursusSL(List<SelectItem> listCursusSL) {
		this.listCursusSL = listCursusSL;
	}

	public Long getCursusSelected() {
		return cursusSelected;
	}

	public void setCursusSelected(Long cursusSelected) {
		this.cursusSelected = cursusSelected;
	}

	public List<PeriodeDetude> getListPeriodeDetudes() {

		try {
			if (getCursusSelected() == null) {
				System.out.println(1);
				facade = getFacade();
				listPeriodeDetudes = facade
						.listPeriodeDetudesByCursus(getListCursus().get(0));
				return listPeriodeDetudes;
			} else {
				System.out.println(2);
				listPeriodeDetudes = facade.listPeriodeDetudesByCursus(facade
						.FindCursus(getCursusSelected()));
				return listPeriodeDetudes;
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void setListPeriodeDetudes(List<PeriodeDetude> listPeriodeDetudes) {
		this.listPeriodeDetudes = listPeriodeDetudes;
	}

	public List<Cour> getListCours() {
		try {
			facade = getFacade();
			listCours = facade.listCours();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listCours;
	}

	public void setListCours(List<Cour> listCours) {
		this.listCours = listCours;
	}

	public List<SelectItem> getAllCoursSL() {

		allCoursSL = new ArrayList<SelectItem>();
		for (Cour cour : getListCours()) {
			SelectItem e = new SelectItem(cour.getNom(), cour.getNom());
			allCoursSL.add(e);
		}
		return allCoursSL;
	}

	public void setAllCoursSL(List<SelectItem> allCoursSL) {
		this.allCoursSL = allCoursSL;
	}

	public List<String> getCourSL() {
		return courSL;
	}

	public void setCourSL(List<String> courSL) {
		this.courSL = courSL;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public List<Cour> getCours() {
		return cours;
	}

	public void setCours(List<Cour> cours) {
		this.cours = cours;
	}

	public void test() {
		setCursusSelected(cursusSelected);
	}

	public List<Cour> getCoursActuel() {
		return coursActuel;
	}

	public void setCoursActuel(List<Cour> coursActuel) {
		this.coursActuel = coursActuel;
	}

}
