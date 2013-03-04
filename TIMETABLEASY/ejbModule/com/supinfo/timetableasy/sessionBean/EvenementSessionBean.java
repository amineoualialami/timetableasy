package com.supinfo.timetableasy.sessionBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supinfo.timetableasy.entity.Evenement;

/**
 * Session Bean implementation class EvenementSessionBean
 */
@Stateless(name = "evenementSB")
public class EvenementSessionBean implements EvenementSessionBeanRemote {

	@PersistenceContext(unitName="timetableasyPU")
    EntityManager em; 
	
    public EvenementSessionBean() {
        // TODO Auto-generated constructor stub
    }

	public void addEvenement(Evenement evenement) {
		em.persist(evenement);
	}

	public void removeEvenement(Evenement evenement) {
		em.remove(em.merge(evenement));
	}

	public List<Evenement> listEvenementByClasse(String nomClasse) {
        List<Evenement> listEvenementByClasse = new ArrayList<Evenement>();
		List<Evenement> listEvenements = new ArrayList<Evenement>();
		Query query = em.createQuery("select e from Evenement e");
		listEvenements = query.getResultList();
		for (Evenement evenement : listEvenements) {
			if(evenement.getClasse().getNom().equals(nomClasse)){
				listEvenementByClasse.add(evenement);
			}
		}
		for (Evenement evenement : listEvenementByClasse) {
			evenement.getCour().getNom();
			evenement.getClasse().getNom();
			evenement.getEspace().getNom();
			evenement.getIntervenant().getNom();
		}
		return listEvenementByClasse;
	}

	public List<Evenement> listEvenements() {
		List<Evenement> listEvenements = new ArrayList<Evenement>();
		Query query = em.createQuery("select e from Evenement e");
		listEvenements = query.getResultList();
		for (Evenement evenement : listEvenements) {
			evenement.getCour().getNom();
			evenement.getClasse().getNom();
			evenement.getEspace().getNom();
			evenement.getIntervenant().getNom();
		}
		return listEvenements;
	}

}
