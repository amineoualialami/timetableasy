package com.supinfo.timetableasy.sessionBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Cursus;
import com.supinfo.timetableasy.entity.PeriodeDetude;
import com.supinfo.timetableasy.entity.User;

/**
 * Session Bean implementation class CursusSessionBean
 */
@Stateless(name="cursusSB")
public class CursusSessionBean implements CursusSessionBeanRemote {

   
	
	  @PersistenceContext(unitName="timetableasyPU")
	    EntityManager em;  
	
    public CursusSessionBean() {
        // TODO Auto-generated constructor stub
    }

	public void addCursus(Cursus cursus, List<User> users,List<PeriodeDetude> periodeDetudes) {

		cursus.setUsers(users);
		cursus.setPeriodeDetudes(periodeDetudes);
		em.persist(cursus);
		
	}

	public List<Cursus> listCursus() {

		Query query = em.createQuery("select c from Cursus c");		
		List<Cursus> allCursus = query.getResultList();
		for (Cursus cursus : allCursus) {
			cursus.getUsers().size();
			cursus.getPeriodeDetudes().size();
		}
		
		return allCursus;
	}

	public List<PeriodeDetude> listPeriodeDetudesByCursus(Cursus cursus) {
		
		Query query = em.createQuery("select c from Cursus c");		
		List<Cursus> allCursus = query.getResultList();
		List<PeriodeDetude> periodeDetudesByCursus = new ArrayList<PeriodeDetude>();
		for (Cursus cursus2 : allCursus) {
			cursus2.getUsers().size();
			cursus2.getPeriodeDetudes().size();
		}
		for (Cursus cursusTemp : allCursus) {
			if(cursusTemp.getNom().equals(cursus.getNom())){
				periodeDetudesByCursus = cursusTemp.getPeriodeDetudes();
			}
		}
		return periodeDetudesByCursus;
	}

	public List<User> listResponsableCursus(Cursus cursus) {
		
		Query query = em.createQuery("select c from Cursus c");		
		List<Cursus> allCursus = query.getResultList();
		List<User> usersByCursus = new ArrayList<User>();
		for (Cursus cursusTemp : allCursus) {
			if(cursusTemp==cursus){
				usersByCursus = cursusTemp.getUsers();
			}
		}
		return usersByCursus;
		
	}

	public void removeCursus(Cursus cursus) {

		em.remove(em.merge(cursus));
	}

	public void updateCursus(Cursus cursus, List<User> users, List<PeriodeDetude> periodeDetudes) {

		cursus.setUsers(users);
		cursus.setPeriodeDetudes(periodeDetudes);
		em.merge(cursus);
		
	}

	public Cursus getCursusByName(String nom) {
	        
	    List<Cursus> listCursus = new ArrayList<Cursus>();
	    Query query = em.createQuery("select c from Cursus c");
	    listCursus = query.getResultList();
	    for (Cursus cursus : listCursus) {
			cursus.getUsers().size();
			cursus.getPeriodeDetudes().size();
		}
	    for (Cursus cursus : listCursus) {
			if(cursus.getNom().equals(nom)){
				return cursus;
			}
		}
		return null;
	}

	public Cursus FindCursus(Long id) {
		Cursus cursus = em.find(Cursus.class, id);
		cursus.getPeriodeDetudes().size();
		return cursus;
	}

	public PeriodeDetude findPeriodeDetude(Long id) {
		PeriodeDetude pd = em.find(PeriodeDetude.class, id);
		pd.getCours().size();
		return pd;
	}

}
