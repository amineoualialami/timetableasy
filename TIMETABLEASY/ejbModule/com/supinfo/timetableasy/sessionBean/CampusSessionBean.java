package com.supinfo.timetableasy.sessionBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Espace;
import com.supinfo.timetableasy.entity.User;

/**
 * Session Bean implementation class CampusSessionBean
 */
@Stateless(name = "campusSB")
public class CampusSessionBean implements CampusSessionBeanRemote {

	
	@PersistenceContext(unitName="timetableasyPU")
    EntityManager em;  
	
    /**
     * Default constructor. 
     */
    public CampusSessionBean() {
        // TODO Auto-generated constructor stub
    }

	public void addCampus(Campus campus, List<User> users,List<Espace> espaces) {
		campus.setUsers(users);
		campus.setEspaces(espaces);
		em.persist(campus);	
	}

	public List<Campus> listAllCampus() {
		
		Query query = em.createQuery("select c from Campus c");		
		List<Campus> campus = query.getResultList();
		for (Campus campus2 : campus) {
			campus2.getUsers().size();
		}
		return campus;
	
	}

	

	public List<Espace> listEspacesByCampus(Campus campus) {

		Query query = em.createQuery("select c from Campus c");		
		List<Campus> allCampus = query.getResultList();
		List<Espace> espacesByCampus = new ArrayList<Espace>();
		for (Campus campus3 : allCampus) {
			campus3.getEspaces().size();
		}
		for (Campus campus2 : allCampus) {
			if (campus2.getNom().equals(campus.getNom())){
				espacesByCampus=campus2.getEspaces();
			}
		}
		return espacesByCampus;
	}

	public void removeCampus(Campus campus) {
		em.remove(em.merge(campus));
		
	}

	public void updateCampus(Campus campus, List<User> users, List<Espace> espaces) {
		campus.setUsers(users);
		campus.setEspaces(espaces);
		em.merge(campus);	
		
	}

	public Campus getCampusByNom(String nom) {
	
        List<Campus> listCampus = new ArrayList<Campus>();
        Query query = em.createQuery("select c from Campus c");
        listCampus = query.getResultList();
        for (Campus campus2 : listCampus) {
			campus2.getUsers().size();
		}
        for (Campus campus : listCampus) {
			if(campus.getNom().equals(nom)){
				return campus;
			}
		}
		return null;
	}

	public Campus findCampus(Long id) {
		Campus campus = em.find(Campus.class, id);
		campus.getUsers().size();
		campus.getEspaces().size();
		return campus;
	}

	public Espace findEspace(Long id) {
		Espace espace = em.find(Espace.class, id);
		return espace;
	}


	

}
