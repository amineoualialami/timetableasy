package com.supinfo.timetableasy.sessionBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supinfo.timetableasy.entity.Cour;
import com.supinfo.timetableasy.entity.MPedagogique;
import com.supinfo.timetableasy.entity.Modalite;

/**
 * Session Bean implementation class CourSessionBean
 */
@Stateless(name = "courSB")
public class CourSessionBean implements CourSessionBeanRemote {

	
	@PersistenceContext(unitName="timetableasyPU")
    EntityManager em; 
	
	
    /**
     * Default constructor. 
     */
    public CourSessionBean() {

    	
    }

	public List<Cour> listCours() {

		Query query = em.createQuery("select c from Cour c");
		List<Cour> listCours = query.getResultList();
		for (Cour cour : listCours) {
			cour.getMPedagogiques().size();
		}
		return listCours;
	}

	public List<MPedagogique> ListMPedagogiquesByCour(Cour cour) {

		Query query = em.createQuery("select c from Cour c");
		List<Cour> listCours = query.getResultList();
		List<MPedagogique> listMPedagogiques = new ArrayList<MPedagogique>();
		for (Cour cour2 : listCours) {
			if(cour2==cour){
				listMPedagogiques= cour2.getMPedagogiques();
			}
		}
	  return listMPedagogiques;
	}

	public void addCour(Cour cour, List<MPedagogique> pedagogiques) {
		cour.setMPedagogiques(pedagogiques);
	    em.persist(cour);
		
	}

	public List<Modalite> listModalites() {

		Query query;
		List<Modalite> modalites;
		query = em.createQuery("select m from Modalite m");
		modalites = query.getResultList();		
		return modalites;
	
	}

	public void removeCour(Cour cour) {

		em.remove(em.merge(cour));
	}

	public void updateCour(Cour cour, List<MPedagogique> pedagogiques) {

		cour.setMPedagogiques(pedagogiques);
		em.merge(cour);
	}

	public Cour listCourByName(String nom) {
		
		Query query = em.createQuery("select c from Cour c");
		List<Cour> listCours = query.getResultList();
		for (Cour cour : listCours) {
			cour.getMPedagogiques().size();
		}
		for (Cour cour : listCours) {
			if(cour.getNom().equals(nom)){
				return cour;
			}
		}
		return null;
	}

	public Cour findCour(Long id) {
		Cour cour = em.find(Cour.class, id);
		cour.getMPedagogiques().size();
		for (MPedagogique mp : cour.getMPedagogiques()) {
			mp.getModalite().getNom();
		}
		return cour;
	}

	

}
