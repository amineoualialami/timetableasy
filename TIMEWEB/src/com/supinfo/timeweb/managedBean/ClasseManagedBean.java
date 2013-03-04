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
import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Cursus;
import com.supinfo.timetableasy.entity.Espace;
import com.supinfo.timetableasy.entity.Role;
import com.supinfo.timetableasy.entity.User;
import com.supinfo.timetableasy.facade.FacadeRemote;

public class ClasseManagedBean {

	public FacadeRemote facade;
	public String nom;
	public List<Cursus> listCursus;
	public List<SelectItem> listCursusSL;
	public List<Campus> listCampus;
	public List<SelectItem> listCampusSL;
	public List<User> responsables;
	public List<SelectItem> listResponsablesSL;
	public List<String> listAddResponsablesSL;
	public List<User> etudiants;
	public List<User> listAddEtudiants= new ArrayList<User>();;
	public List<SelectItem> listEtudiantsSL;
	public List<String> listAddEtudiantsSL = new ArrayList<String>();;
	public User responsable;
	public Long campusSelected;
	public Long cursusSelected;
	public List<Classe> listClassess;
	public Campus campus1;
	public Cursus cursus1;
	public List<User> etudiantsActuel;
	

	public String listClasses() {

		String navig = "espacesError";
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);
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
					navig = "listClasses";
				}
			}
		}
		return navig;
	}
	
	public String persistClasse(){
		
		facade = getFacade();
		Campus campus = facade.findCampus(campusSelected);
		Cursus cursus = facade.FindCursus(cursusSelected);
		Classe classe = new Classe();
		classe.setNom(nom);
		listAddEtudiants = getListAddEtudiants();
		System.out.println(getListAddEtudiantsSL().size());
		for (String etudiantTemp : getListAddEtudiantsSL()) {
			User user= new User();
			user = facade.getUserByName(etudiantTemp);
			System.out.println("**********"+user.getNom());
			listAddEtudiants.add(user);
		}
		
		
		facade.addClasse(classe, listAddEtudiants, cursus, campus);
		listAddEtudiantsSL = new ArrayList<String>();
		setNom("");
		return "listClasses";
	}
	
	public void removeClasse(){
		
		facade=  getFacade();
		Campus campus = facade.findCampus(campusSelected);
		List<Classe> classes = facade.listClassesByCampus(campus);
		Classe classeDel = new Classe();
		for (Classe classe : classes) {
			if(classe.getNom().equals(nom)){
				classeDel = classe;
			}
		}
		facade.removeClasse(classeDel);
	}
	
	public String detailsClasse(){
		facade = getFacade();
		Classe classe1 = facade.listClasseByName(nom);
		campus1 = classe1.getCampus();
		cursus1 = classe1.getCursus();
		setEtudiantsActuel(classe1.getEtudiants());
		return "detailsClasse";
	}
	
	public String updateClasse(){
		facade = getFacade();
		Classe classe1 = facade.listClasseByName(nom);
		setEtudiantsActuel(classe1.getEtudiants());
		return "updateClasse";
	}
	
	public String mergeClasse(){
		facade = getFacade();
		Classe classe = facade.listClasseByName(nom);
		listAddEtudiants = getListAddEtudiants();
		listAddEtudiants.clear();
		System.out.println(getListAddEtudiantsSL().size());
		for (String etudiantTemp : getListAddEtudiantsSL()) {
			User user= new User();
			user = facade.getUserByName(etudiantTemp);
			System.out.println("**********"+user.getNom());
			listAddEtudiants.add(user);
		}
		
		facade.updateClasse(classe, listAddEtudiants, classe.getCursus(), classe.getCampus());
		listAddEtudiantsSL = new ArrayList<String>();
		setNom("");
		return "listClasses";
	}

	public FacadeRemote getFacade() {
		try {
			Properties properties = new Properties();
			properties.put("java.naming.factory.initial",
					"org.jnp.interfaces.NamingContextFactory");
			properties.put("java.naming.factory.url.pkgs",
					"=org.jboss.naming:org.jnp.interfaces");
			properties.put("java.naming.provider.url", "localhost:1099");
			InitialContext context = new InitialContext(properties);
			facade = (FacadeRemote) context.lookup("facadeSB/remote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
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
		facade = getFacade();
		Campus campus = facade.findCampus(campusSelected);
		responsables = campus.getUsers();
		return responsables;
	}

	public void setResponsables(List<User> responsables) {
		this.responsables = responsables;
	}

	public List<User> getEtudiants() {
		facade = getFacade();
		Role role = facade.findRoleByTitre("etudiant");
		etudiants = facade.listUsersByRole(role);
		return etudiants;
	}

	public void setEtudiants(List<User> etudiants) {
		this.etudiants = etudiants;
	}

	public List<Cursus> getListCursus() {
		facade = getFacade();
		listCursus = facade.listCursus();
		return listCursus;
	}

	public void setListCursus(List<Cursus> listCursus) {
		this.listCursus = listCursus;
	}

	public List<Campus> getListCampus() {
		return listCampus;
	}

	public void setListCampus(List<Campus> listCampus) {
		this.listCampus = listCampus;
	}

	public User getResponsable() {
		return responsable;
	}

	public void setResponsable(User responsable) {
		this.responsable = responsable;
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

	public List<SelectItem> getListEtudiantsSL() {
		listEtudiantsSL = new ArrayList<SelectItem>();
		for (User user : getEtudiants()) {
			SelectItem e = new SelectItem(user.getNom(), user.getNom());
			listEtudiantsSL.add(e);
		}
		return listEtudiantsSL;
	}

	public void setListEtudiantsSL(List<SelectItem> listEtudiantsSL) {
		this.listEtudiantsSL = listEtudiantsSL;
	}

	public List<String> getListAddEtudiantsSL() {
		return listAddEtudiantsSL;
	}

	public void setListAddEtudiantsSL(List<String> listAddEtudiantsSL) {
		this.listAddEtudiantsSL = listAddEtudiantsSL;
	}

	

	public Long getCampusSelected() {
		return campusSelected;
	}

	public void setCampusSelected(Long campusSelected) {
		this.campusSelected = campusSelected;
	}

	public Long getCursusSelected() {
		return cursusSelected;
	}

	public void setCursusSelected(Long cursusSelected) {
		this.cursusSelected = cursusSelected;
	}

	public List<SelectItem> getListResponsablesSL() {
		listResponsablesSL = new ArrayList<SelectItem>();
		for (User user : getResponsables()) {
			SelectItem e = new SelectItem(user.getNom(), user.getNom());
			listResponsablesSL.add(e);
		}
		
		return listResponsablesSL;
	}

	public void setListResponsablesSL(List<SelectItem> listResponsablesSL) {
		this.listResponsablesSL = listResponsablesSL;
	}

	public List<String> getListAddResponsablesSL() {
		return listAddResponsablesSL;
	}

	public void setListAddResponsablesSL(List<String> listAddResponsablesSL) {
		this.listAddResponsablesSL = listAddResponsablesSL;
	}

	public List<User> getListAddEtudiants() {
		return listAddEtudiants;
	}

	public void setListAddEtudiants(List<User> listAddEtudiants) {
		this.listAddEtudiants = listAddEtudiants;
	}
	
	public void test() {
		setCampusSelected(campusSelected);
	}

	public List<Classe> getListClassess() {
		
		if(getCampusSelected() ==null){
			facade = getFacade();
			listClassess = facade.listClassesByCampus(getListCampus().get(0));
			return listClassess;
		}
		else{
			listClassess = facade.listClassesByCampus(facade.findCampus(getCampusSelected()));
			return listClassess;
		}
	}

	public void setListClassess(List<Classe> listClassess) {
		this.listClassess = listClassess;
	}

	public Campus getCampus1() {
		return campus1;
	}

	public void setCampus1(Campus campus1) {
		this.campus1 = campus1;
	}

	public Cursus getCursus1() {
		return cursus1;
	}

	public void setCursus1(Cursus cursus1) {
		this.cursus1 = cursus1;
	}

	public List<User> getEtudiantsActuel() {
		return etudiantsActuel;
	}

	public void setEtudiantsActuel(List<User> etudiantsActuel) {
		this.etudiantsActuel = etudiantsActuel;
	}
	
	
	

}
