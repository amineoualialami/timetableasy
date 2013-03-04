package com.supinfo.timeweb.managedBean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.myfaces.custom.schedule.model.DefaultScheduleEntry;
import org.apache.myfaces.custom.schedule.model.ScheduleModel;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Evenement;
import com.supinfo.timetableasy.entity.User;
import com.supinfo.timetableasy.facade.FacadeRemote;

public class ScheduleEtudiantManagedBean {

	public FacadeRemote facade;
	public ScheduleModel model;
	public User etudiant;
	public Classe classe;

	public String listScheduleE() {

		String navig = "etudiantError";
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);
		etudiant = (User) session.getAttribute("AuthentifiedUser");
		System.out.println("le user authentifier" + etudiant.getNom());
		facade = getFacade();

		classe = new Classe();
		List<Classe> allClasse = facade.listClasses();
		for (Classe classeTemp : allClasse) {
			for (User etudiantTemp : classeTemp.getEtudiants()) {
				if (etudiantTemp.getNom().equals(etudiant.getNom())) {
					classe = classeTemp;
					navig = "listScheduleEtudiant";
				}
			}
		}
		initializModel();
		return navig;
	}

	public String initializModel() {
		setModel(new ScheduleEtudiant());
		/**
		 * Calendar calendar = Calendar.getInstance(); calendar.setTime(new
		 * Date()); DefaultScheduleEntry entry2 = new DefaultScheduleEntry();
		 * entry2.setId(RandomStringUtils.randomNumeric(32));
		 * entry2.setStartTime(calendar.getTime()); calendar.add(Calendar.HOUR,
		 * 2); entry2.setEndTime(calendar.getTime());
		 * 
		 * entry2.setTitle("title"); entry2.setSubtitle("subtitle");
		 * model.addEntry(entry2);
		 */

		System.out.println("debut initialisation");
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
		return "listScheduleEtudiant";
	}

	public String deleteSelectedEntry(ActionEvent event) {
		if (model == null)
			return "listSchedule";
		model.removeSelectedEntry();
		return "listSchedule";
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

	public ScheduleModel getModel() {
		return model;
	}

	public void setModel(ScheduleModel model) {
		this.model = model;
	}

}
