package com.supinfo.timeweb.managedBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.myfaces.custom.schedule.model.DefaultScheduleEntry;
import org.apache.myfaces.custom.schedule.model.ScheduleModel;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Cour;
import com.supinfo.timetableasy.entity.Cursus;
import com.supinfo.timetableasy.entity.Espace;
import com.supinfo.timetableasy.entity.Evenement;
import com.supinfo.timetableasy.entity.MPedagogique;
import com.supinfo.timetableasy.entity.PeriodeDetude;
import com.supinfo.timetableasy.entity.Role;
import com.supinfo.timetableasy.entity.User;
import com.supinfo.timetableasy.facade.FacadeRemote;

public class ScheduleManagedBean {

	public FacadeRemote facade;
	public ScheduleModel model;
	public Long campusSelected;
	public Long classeSelected;
	public List<Campus> listCampus;
	public List<SelectItem> listCampusSL;
	public List<Classe> listClasse;
	public List<SelectItem> listClasseSL;
	public User responsable;
	public String cursusSelected;
	public List<PeriodeDetude> listPd;
	public List<SelectItem> listPdSL;
	public Long pdSelected;
	public List<Cour> listCours;
	public List<SelectItem> listCoursSL;
	public Long courSelected;
	public Cursus cursus2;
	public String nom;
	public List<MPedagogique> listModalitesP;
	public Date from;
	public Date until;
	public List<User> listIntervenants;
	public List<Espace> listEspaces;
	public Long intervenantSelected;
	public Long espaceSelected;
	public List<SelectItem> listIntervenantsSL;
	public List<SelectItem> listEspacesSL;
	public String modaliteSelected;
	public Evenement evenement;
	public List<Evenement> listEvenements;
	public List<MPedagogique> listMP;

	public String addScheduleClasse() {

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
					navig = "addScheduleClasse";
				}
			}
		}
		return navig;
	}

	public String listSchedule() {
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
					navig = "listSchedule";
				}
			}
		}
		model.refresh();
		initializModel();
		model.refresh();
		return navig;
	}

	public ScheduleModel getModel() {
		return model;
	}

	public void setModel(ScheduleModel model) {
		this.model = model;
	}

	public String deleteSelectedEntry(ActionEvent event) {
		if (model == null)
			return "listSchedule";
		DefaultScheduleEntry entry = new DefaultScheduleEntry();
		entry = (DefaultScheduleEntry) model.getSelectedEntry();
		facade = getFacade();
		List<Evenement> listEvenements = facade.listEvenements();
		Evenement evenementDel = new Evenement();
		for (Evenement evenement : listEvenements) {
			String title = evenement.getType() + " "
					+ evenement.getCour().getNom();
			if (title.equals(entry.getTitle())) {
				evenementDel = evenement;
			}
		}
		facade.removeEvenement(evenementDel);
		model.removeSelectedEntry();
		return "listSchedule";
	}

	public String initializModel() {
		setModel(new Schedule());
		System.out.println("debut initialisation");
		Campus campus = new Campus();
		if (campusSelected == null) {
			campus = facade.findCampus(getListCampus().get(0).getIdCampus());
		} else {
			campus = facade.findCampus(campusSelected);
		}
		List<Classe> listClasses = facade.listClassesByCampus(campus);
		Classe classe = new Classe();
		if (classeSelected == null) {
			for (Classe classe2 : listClasses) {
				if (classe2.getIdClasse().equals(
						getListClasse().get(0).getIdClasse())) {
					classe = classe2;
				}
			}
		} else {
			for (Classe classe2 : listClasses) {
				if (classe2.getIdClasse().equals(classeSelected)) {
					classe = classe2;
				}
			}
		}

		List<Evenement> listEvenements = facade.listEvenementByClasse(classe
				.getNom());
		System.out.println(classe.getNom());
		// model = getModel();
		for (Evenement evenement : listEvenements) {
			System.out.println("-------" + evenement.getClasse().getNom());
			DefaultScheduleEntry entry = new DefaultScheduleEntry();
			entry.setId(RandomStringUtils.randomNumeric(32));
			entry.setStartTime(evenement.getDateDebut());
			entry.setEndTime(evenement.getDateFin());
			entry.setTitle(evenement.getType() + " "
					+ evenement.getCour().getNom());
			entry.setSubtitle(evenement.getIntervenant().getNom() + " "
					+ evenement.getEspace().getNom());
			model.addEntry(entry);
		}
		setModel(model);
		model.refresh();
		return "listSchedule";
	}

	public String addEntry() {

		if (test1() == false) {
			return "failedIntervenant";
		}
		if (test2() == false) {
			return "failedEspace";
		}
		if (test3() == false) {
			return "failedClasse";
		}
		if (test4() == false) {
			return "failedPeriode";
		}
		if (test5() == false) {
			return "failedHorraire";
		}

		evenement = new Evenement();
		evenement.setClasse(facade.findClasse(classeSelected));
		evenement.setCour(facade.findCour(courSelected));
		evenement.setType(getModaliteSelected());
		evenement.setDateDebut(from);
		evenement.setDateFin(until);
		evenement.setEspace(facade.findEspace(espaceSelected));
		evenement.setIntervenant(facade.findUser(intervenantSelected));

		facade.addEvenement(evenement);

		/**
		 * DefaultScheduleEntry entry = new DefaultScheduleEntry();
		 * entry.setId(RandomStringUtils.randomNumeric(32));
		 * entry.setStartTime(getFrom()); entry.setEndTime(getUntil());
		 * entry.setTitle(getModaliteSelected() + " " +
		 * facade.findCour(getCourSelected()).getNom());
		 * entry.setSubtitle(facade.findUser(getIntervenantSelected()).getNom()
		 * + " " + facade.findEspace(getEspaceSelected()).getNom());
		 * model.addEntry(entry); model.refresh();
		 */

		return "listSchedule";
	}

	public Boolean test1() {
		// disponibilité intervenant
		facade = getFacade();
		List<Evenement> listEvenements = facade.listEvenements();
		for (Evenement evenement : listEvenements) {
			if (evenement.getDateDebut() == from
					&& evenement.getIntervenant().getIdUser().equals(
							intervenantSelected)) {
				return false;
			}
			if (evenement.getDateFin() == until
					&& evenement.getIntervenant().getIdUser().equals(
							intervenantSelected)) {
				return false;
			}
			if (from.after(evenement.getDateDebut())
					&& from.before(evenement.getDateFin())
					&& evenement.getIntervenant().getIdUser().equals(
							intervenantSelected)) {
				System.out.println("test1");
				return false;
			}

			if (until.after(evenement.getDateDebut())
					&& until.before(evenement.getDateFin())
					&& evenement.getIntervenant().getIdUser().equals(
							intervenantSelected)) {
				System.out.println("test2");
				return false;
			}
		}
		return true;
	}

	public Boolean test2() {
		// disponibilité espace ( same campus )
		facade = getFacade();
		List<Evenement> listEvenementsEspace = new ArrayList<Evenement>();
		List<Evenement> listEvenements = facade.listEvenements();
		for (Evenement evenement : listEvenements) {
			if (evenement.getClasse().getCampus().getIdCampus().equals(
					campusSelected)) {
				listEvenementsEspace.add(evenement);
			}
		}
		for (Evenement evenement : listEvenementsEspace) {
			if (evenement.getDateDebut() == from
					&& evenement.getEspace().getIdEspace().equals(
							espaceSelected)) {
				return false;
			}
			if (evenement.getDateFin() == until
					&& evenement.getEspace().getIdEspace().equals(
							espaceSelected)) {
				return false;
			}
			if (from.after(evenement.getDateDebut())
					&& from.before(evenement.getDateFin())
					&& evenement.getEspace().getIdEspace().equals(
							espaceSelected)) {
				System.out.println("test1");
				return false;
			}

			if (until.after(evenement.getDateDebut())
					&& until.before(evenement.getDateFin())
					&& evenement.getEspace().getIdEspace().equals(
							espaceSelected)) {
				System.out.println("test2");
				return false;
			}
		}

		return true;
	}

	public Boolean test3() {
		// une meme classe ne peut avoir deux evenement en meme temps
		facade = getFacade();
		List<Evenement> listEvenementsEspace = new ArrayList<Evenement>();
		List<Evenement> listEvenements = facade.listEvenements();
		for (Evenement evenement : listEvenements) {
			if (evenement.getClasse().getCampus().getIdCampus().equals(
					campusSelected)) {
				listEvenementsEspace.add(evenement);
			}
		}
		for (Evenement evenement : listEvenementsEspace) {
			if (evenement.getDateDebut() == from
					&& evenement.getClasse().getIdClasse().equals(
							classeSelected)) {
				return false;
			}
			if (evenement.getDateFin() == until
					&& evenement.getClasse().getIdClasse().equals(
							classeSelected)) {
				return false;
			}
			if (from.after(evenement.getDateDebut())
					&& from.before(evenement.getDateFin())
					&& evenement.getClasse().getIdClasse().equals(
							classeSelected)) {
				System.out.println("test1");
				return false;
			}

			if (until.after(evenement.getDateDebut())
					&& until.before(evenement.getDateFin())
					&& evenement.getClasse().getIdClasse().equals(
							classeSelected)) {
				System.out.println("test2");
				return false;
			}
		}

		return true;
	}

	public Boolean test4() {
		// l'evenement ne peut etre planifier que dans la période d'étude
		facade = getFacade();
		PeriodeDetude pd = facade.findPeriodeDetude(pdSelected);
		if (from.before(pd.getDateDebut()) || from.after(pd.getDateFin())) {
			return false;
		}
		return true;
	}

	public Boolean test5() {
		// test sur les horraires des cours (modalite)
		List<MPedagogique> mp = getListModalitesP();
		int horraireModalite = 0;
		for (MPedagogique pedagogique : mp) {
			if (pedagogique.getModalite().getNom().equals(modaliteSelected)) {
				horraireModalite = pedagogique.getHorraireModalite();
			}
		}
		List<MPedagogique> mp2 = getListMP();
		int horrairePlanifie = 0;
		for (MPedagogique pedagogique : mp2) {
			if (pedagogique.getModalite().getNom().equals(modaliteSelected)) {
				horrairePlanifie = pedagogique.getHorraireModalite();
			}
		}
		Long dureeL = until.getTime() - from.getTime();
		int duree = dureeL.intValue();
		duree = duree / 3600000;

		if (horrairePlanifie + duree > horraireModalite) {
			return false;
		}

		return true;
	}

	public void test6() {
		setModaliteSelected(modaliteSelected);
		System.out.println(getModaliteSelected());
	}

	public Long getCampusSelected() {
		return campusSelected;
	}

	public void setCampusSelected(Long campusSelected) {
		this.campusSelected = campusSelected;
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

	public User getResponsable() {
		return responsable;
	}

	public void setResponsable(User responsable) {
		this.responsable = responsable;
	}

	public List<Classe> getListClasse() {
		if (getCampusSelected() == null) {
			facade = getFacade();
			listClasse = facade.listClassesByCampus(getListCampus().get(0));
			return listClasse;
		} else {
			listClasse = facade.listClassesByCampus(facade
					.findCampus(getCampusSelected()));
			return listClasse;
		}
	}

	public void setListClasse(List<Classe> listClasse) {
		this.listClasse = listClasse;
	}

	public List<SelectItem> getListClasseSL() {
		listClasseSL = new ArrayList<SelectItem>();
		for (Classe classe : getListClasse()) {
			SelectItem e = new SelectItem(classe.getIdClasse(), classe.getNom());
			listClasseSL.add(e);
		}
		return listClasseSL;
	}

	public void setListClasseSL(List<SelectItem> listClasseSL) {
		this.listClasseSL = listClasseSL;
	}

	public Long getClasseSelected() {
		return classeSelected;
	}

	public void setClasseSelected(Long classeSelected) {
		this.classeSelected = classeSelected;
	}

	public String getCursusSelected() {

		if (getClasseSelected() == null) {
			facade = getFacade();
			Classe classe = facade.findClasse(getListClasse().get(0)
					.getIdClasse());
			return classe.getCursus().getNom();
		} else {
			facade = getFacade();
			Classe classe = facade.findClasse(getClasseSelected());
			return classe.getCursus().getNom();
		}
	}

	public void setCursusSelected(String cursusSelected) {
		this.cursusSelected = cursusSelected;
	}

	public List<PeriodeDetude> getListPd() {
		if (getCursusSelected() == null) {
			facade = getFacade();
			List<Cursus> cursuss = facade.listCursus();
			cursus2 = new Cursus();
			for (Cursus cursus : cursuss) {
				if (cursus.getNom().equals(getCursusSelected())) {
					cursus2 = cursus;
				}
			}
			listPd = cursus2.getPeriodeDetudes();
			return listPd;
		} else {
			facade = getFacade();
			List<Cursus> cursuss = facade.listCursus();
			cursus2 = new Cursus();
			for (Cursus cursus : cursuss) {
				if (cursus.getNom().equals(getCursusSelected())) {
					cursus2 = cursus;
				}
			}
			listPd = cursus2.getPeriodeDetudes();
			return listPd;
		}

	}

	public void setListPd(List<PeriodeDetude> listPd) {
		this.listPd = listPd;
	}

	public List<SelectItem> getListPdSL() {

		listPdSL = new ArrayList<SelectItem>();
		for (PeriodeDetude pd : getListPd()) {
			SelectItem e = new SelectItem(pd.getIdPeriodeDetude(), pd.getNom());
			listPdSL.add(e);
		}

		return listPdSL;
	}

	public void setListPdSL(List<SelectItem> listPdSL) {
		this.listPdSL = listPdSL;
	}

	public Long getPdSelected() {
		return pdSelected;
	}

	public void setPdSelected(Long pdSelected) {
		this.pdSelected = pdSelected;
	}

	public List<Cour> getListCours() {

		if (getPdSelected() == null) {
			facade = getFacade();
			PeriodeDetude pd = facade.findPeriodeDetude(listPd.get(0)
					.getIdPeriodeDetude());
			listCours = pd.getCours();
			return listCours;

		} else {
			facade = getFacade();
			PeriodeDetude pd = facade.findPeriodeDetude(pdSelected);
			listCours = pd.getCours();
			return listCours;
		}

	}

	public void setListCours(List<Cour> listCours) {
		this.listCours = listCours;
	}

	public List<SelectItem> getListCoursSL() {
		listCoursSL = new ArrayList<SelectItem>();
		for (Cour cour : getListCours()) {
			SelectItem e = new SelectItem(cour.getIdCour(), cour.getNom());
			listCoursSL.add(e);
		}

		return listCoursSL;
	}

	public void setListCoursSL(List<SelectItem> listCoursSL) {
		this.listCoursSL = listCoursSL;
	}

	public Long getCourSelected() {
		return courSelected;
	}

	public void setCourSelected(Long courSelected) {
		this.courSelected = courSelected;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<MPedagogique> getListModalitesP() {

		if (getCourSelected() == null) {
			facade = getFacade();
			Cour cour = facade.findCour(getListCours().get(0).getIdCour());
			listModalitesP = cour.getMPedagogiques();
			return listModalitesP;
		} else {
			facade = getFacade();
			Cour cour = facade.findCour(getCourSelected());
			listModalitesP = cour.getMPedagogiques();
			return listModalitesP;

		}
	}

	public void setListModalitesP(List<MPedagogique> listModalitesP) {
		this.listModalitesP = listModalitesP;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

	public List<User> getListIntervenants() {
		facade = getFacade();
		Role role = facade.findRoleByTitre("intervenant");
		listIntervenants = facade.listUsersByRole(role);
		return listIntervenants;
	}

	public void setListIntervenants(List<User> listIntervenants) {
		this.listIntervenants = listIntervenants;
	}

	public List<Espace> getListEspaces() {
		if (getCampusSelected() == null) {
			facade = getFacade();
			Campus campus = facade.findCampus(getListCampus().get(0)
					.getIdCampus());
			listEspaces = campus.getEspaces();
			return listEspaces;
		} else {
			facade = getFacade();
			Campus campus = facade.findCampus(getCampusSelected());
			listEspaces = campus.getEspaces();
			return listEspaces;
		}
	}

	public void setListEspaces(List<Espace> listEspaces) {
		this.listEspaces = listEspaces;
	}

	public Long getIntervenantSelected() {
		return intervenantSelected;
	}

	public void setIntervenantSelected(Long intervenantSelected) {
		this.intervenantSelected = intervenantSelected;
	}

	public Long getEspaceSelected() {
		return espaceSelected;
	}

	public void setEspaceSelected(Long espaceSelected) {
		this.espaceSelected = espaceSelected;
	}

	public List<SelectItem> getListIntervenantsSL() {

		listIntervenantsSL = new ArrayList<SelectItem>();
		for (User user : getListIntervenants()) {
			SelectItem e = new SelectItem(user.getIdUser(), user.getNom());
			listIntervenantsSL.add(e);
		}
		return listIntervenantsSL;
	}

	public void setListIntervenantsSL(List<SelectItem> listIntervenantsSL) {
		this.listIntervenantsSL = listIntervenantsSL;
	}

	public List<SelectItem> getListEspacesSL() {
		listEspacesSL = new ArrayList<SelectItem>();
		for (Espace espace : getListEspaces()) {
			SelectItem e = new SelectItem(espace.getIdEspace(), espace.getNom());
			listEspacesSL.add(e);
		}
		return listEspacesSL;
	}

	public void setListEspacesSL(List<SelectItem> listEspacesSL) {
		this.listEspacesSL = listEspacesSL;
	}

	public String getModaliteSelected() {
		return modaliteSelected;
	}

	public void setModaliteSelected(String modaliteSelected) {
		this.modaliteSelected = modaliteSelected;
	}

	public List<Evenement> getListEvenements() {

		listEvenements = new ArrayList<Evenement>();

		if (getClasseSelected() == null && getCourSelected() == null) {
			facade = getFacade();
			Classe classe = facade.findClasse(getListClasse().get(0)
					.getIdClasse());
			Cour cour = facade.findCour(getListCours().get(0).getIdCour());
			List<Evenement> listEvenementClasse = facade
					.listEvenementByClasse(classe.getNom());
			for (Evenement evenement : listEvenementClasse) {
				if (evenement.getCour().getNom().equals(cour.getNom())) {
					listEvenements.add(evenement);
				}
			}
			return listEvenements;
		}
		if (getClasseSelected() == null) {
			facade = getFacade();
			Classe classe = facade.findClasse(getListClasse().get(0)
					.getIdClasse());
			Cour cour = facade.findCour(courSelected);
			List<Evenement> listEvenementClasse = facade
					.listEvenementByClasse(classe.getNom());
			for (Evenement evenement : listEvenementClasse) {
				if (evenement.getCour().getNom().equals(cour.getNom())) {
					listEvenements.add(evenement);
				}
			}
			return listEvenements;
		}
		if (getCourSelected() == null) {
			facade = getFacade();
			Classe classe = facade.findClasse(classeSelected);
			Cour cour = facade.findCour(getListCours().get(0).getIdCour());
			List<Evenement> listEvenementClasse = facade
					.listEvenementByClasse(classe.getNom());
			for (Evenement evenement : listEvenementClasse) {
				if (evenement.getCour().getNom().equals(cour.getNom())) {
					listEvenements.add(evenement);
				}
			}
			return listEvenements;
		} else {
			facade = getFacade();
			Classe classe = facade.findClasse(classeSelected);
			Cour cour = facade.findCour(courSelected);
			List<Evenement> listEvenementClasse = facade
					.listEvenementByClasse(classe.getNom());
			for (Evenement evenement : listEvenementClasse) {
				if (evenement.getCour().getNom().equals(cour.getNom())) {
					listEvenements.add(evenement);
				}
			}
			return listEvenements;
		}

	}

	public void setListEvenements(List<Evenement> listEvenements) {
		this.listEvenements = listEvenements;
	}

	public List<MPedagogique> getListMP() {
		facade = getFacade();
		MPedagogique mpedagogique1 = new MPedagogique();
		mpedagogique1.setModalite(facade.listModalites().get(0));
		mpedagogique1.setHorraireModalite(0);

		MPedagogique mpedagogique2 = new MPedagogique();
		mpedagogique2.setModalite(facade.listModalites().get(1));
		mpedagogique2.setHorraireModalite(0);

		MPedagogique mpedagogique3 = new MPedagogique();
		mpedagogique3.setModalite(facade.listModalites().get(2));
		mpedagogique3.setHorraireModalite(0);

		List<MPedagogique> mpedagogiques = new ArrayList<MPedagogique>();
		mpedagogiques.add(mpedagogique1);
		mpedagogiques.add(mpedagogique2);
		mpedagogiques.add(mpedagogique3);

		for (Evenement evenement : getListEvenements()) {
			if (evenement.getType().equals("Cour")) {
				mpedagogiques.remove(mpedagogique1);
				mpedagogique1.setHorraireModalite((int) (mpedagogique1
						.getHorraireModalite() + evenement.getDuree()));
				mpedagogiques.add(mpedagogique1);
			}
			if (evenement.getType().equals("E-Learning")) {
				mpedagogiques.remove(mpedagogique2);
				mpedagogique2.setHorraireModalite((int) (mpedagogique2
						.getHorraireModalite() + evenement.getDuree()));
				mpedagogiques.add(mpedagogique2);
			}
			if (evenement.getType().equals("TD")) {
				mpedagogiques.remove(mpedagogique3);
				mpedagogique3.setHorraireModalite((int) (mpedagogique3
						.getHorraireModalite() + evenement.getDuree()));
				mpedagogiques.add(mpedagogique3);
			}
		}
		return mpedagogiques;
	}

	public void setListMP(List<MPedagogique> listMP) {
		this.listMP = listMP;
	}
	
	public String rifrichi(){
		return "listSchedule";
	}

}
