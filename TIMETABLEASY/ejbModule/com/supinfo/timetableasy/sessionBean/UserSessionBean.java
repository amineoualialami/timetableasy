package com.supinfo.timetableasy.sessionBean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.quartz.xml.ValidationException;

import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Modalite;
import com.supinfo.timetableasy.entity.Role;
import com.supinfo.timetableasy.entity.User;



@Stateless (name = "userSB")
public class UserSessionBean implements UserSessionBeanRemote {
		
    public UserSessionBean() {
    	
    }
    
    
    @PersistenceContext(unitName="timetableasyPU")
    EntityManager em;      
    
    
    public void addUser(User user, List<Role> roles) {
		user.setRoles(roles);  
		em.persist(user);      
		}

	public void updateUser(User user, List<Role> roles) {
		user.setRoles(roles);		
		em.merge(user);		
	}
	
	public void removeUser(User user) {
		if(user != null){
      			em.remove(em.merge(user));
		}
	}
	
	

	
	public User getUserByName(String nom) {		
		List<User> users = new ArrayList<User>();
		Query query = em.createQuery("select u from User u");		
		users = query.getResultList();
        for (User currentUser : users) {              
        if (currentUser.getNom().equals(nom)) {			  
        	return currentUser;
        	}
        }
		return null;        
	}

	
	
	public void inscrireEtudiant(User user, Classe classe) {  
		User user1 = getUserByName(user.getNom());
		Classe classe1 = em.find(Classe.class, classe.getIdClasse());
	    List<User> listUsers = classe1.getEtudiants();
	    listUsers.add(user1);	    
	    classe1.setEtudiants(listUsers);
        if (classe1 != null) {
            em.merge(classe1);
        }
	}


	
	public List<User> listAllUsers() {
		Query query = em.createQuery("select u from User u");		
		List<User> listUser = query.getResultList();		
		return listUser;	
	}


	public List<Role> listRoles() {
		Query query = em.createQuery("select r from Role r");
		List<Role> listRoles = query.getResultList();
		return listRoles;
	}


	public List<User> listUsersByRole(Role role) {
		Query query = em.createQuery("select u from User u");		
		List<User> listUser = query.getResultList();
		List<User> usersByRole = new ArrayList<User>();
		for (User user : listUser) {
			for (Role userRole : user.getRoles()) {
				if(userRole.getTitreRole().equals(role.getTitreRole())){
					usersByRole.add(user);
				}
			}
		}
				
		return usersByRole;	
	}

	public User authentification(String login, String password) throws ValidationException, javax.xml.bind.ValidationException {
		
		if (login ==null || "".equals(login))
			throw new ValidationException("Invalid Login");
		
		Query query;
		User user=null; 
		query = em.createQuery("select u from User u where  u.login=:login");
		query.setParameter("login", login);
		try{
			user =  (User) query.getSingleResult();
			}
			catch (javax.persistence.NoResultException e){
				e.printStackTrace();
			}
			catch(javax.ejb.EJBException e){
				e.printStackTrace();
			}
			
			if (user != null){
				if(user.matchPassword(password)==false){
				return null;	
				}
				return user;
			}
		return null;
	}

	public Role findRole(Long id) {
		
		Role role = em.find(Role.class, id);
		
		return role;
	}

	public Role findRoleByTitre(String titre) {

		Query query = em.createQuery("select r from Role r");
		List<Role> listRoles = query.getResultList();
		
		for (Role role : listRoles) {
			if(role.getTitreRole().equals(titre)){
				return role;
			}
		}
		
		return null;
	}

	public User findUser(Long id) {
		User user = em.find(User.class, id);
		return user;
	}

	

	
}
