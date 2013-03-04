package com.supinfo.timetableasy.sessionBeanTest;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.supinfo.timetableasy.entity.Cour;
import com.supinfo.timetableasy.entity.Modalite;
import com.supinfo.timetableasy.entity.Role;
import com.supinfo.timetableasy.entity.User;

/**
 * Session Bean implementation class UserSessionBean
 */
@Stateless(name="UserSBB")
	
public class UserSessionBeanExemple implements UserSessionBeanLocalExemple {

    /**
     * Default constructor. 
     */

    public UserSessionBeanExemple() {
        // TODO Auto-generated constructor stub
    }
    
	
    @PersistenceContext(unitName="timetableasyPU")
    EntityManager em;


	public void addUser(User user) {

		em.persist(user);
	}


	public List<Role> listRoles() {
		
		
		Query query;
		List<Role> roles;
		query = em.createQuery("select r from Role r");
		roles = query.getResultList();
		
		return roles;
	}


	public void addCour(Cour cour) {

		em.persist(cour);
	}


	public List<Modalite> modalites() {

		Query query;
		List<Modalite> modalites;
		query = em.createQuery("select m from Modalite m");
		modalites = query.getResultList();
		
		return modalites;
	}


	public List<Cour> ListCour() {
		Query query;
		List<Cour> cours;
		query = em.createQuery("select c from Cour c");
		cours = query.getResultList();
		
		return cours;
	}


	public Cour findCour(Long courId) {
		
		Cour cour;
		cour = em.find(Cour.class, courId);
		return cour;
		
	}


	public void removeCour(Cour cour) {

		em.remove(em.merge(cour));
		
	}
	
	





}
