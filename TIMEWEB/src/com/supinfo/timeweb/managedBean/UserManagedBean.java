package com.supinfo.timeweb.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import com.supinfo.timetableasy.entity.Role;
import com.supinfo.timetableasy.entity.User;
import com.supinfo.timetableasy.facade.FacadeRemote;


public class UserManagedBean {

	public String login;
	public String password;
	public FacadeRemote facade;
	public User user;
	public List<User> listUsers;
	public List<Role> allroles;
	public List<Role> newRoles ;
	public List<SelectItem> allrolesSL;
	public List<String> newRolesSL = new ArrayList<String>();
	public String nom;
	public String prenom;
	public List<Role> anciensRoles;
	public User userTemp;
	public Boolean bool;
	public Boolean bool2;
	public Boolean bool3;
	public Boolean bool4;
	
	public UserManagedBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String authentification(){
		try {
			facade = getFacade();
			user = facade.authentification(login, password);
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			session.setAttribute("AuthentifiedUser", user);
			if(user==null){
				return "failedAuthentification";
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.quartz.xml.ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bool = ifCampusManager();
	    bool2 = ifAdministrateur();
	    bool3 = ifIntervenant();
	    bool4 = ifEtudiant();
		return "successAuthentification";
	}
	
	public String doSignOff() {
        String navigateTo = "authentification";
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return navigateTo;
    }
	
	public String persistUser(){

		User newUser = new User();
		newUser.setLogin(login);
		newUser.setPassword(password);
		newUser.setNom(nom);
		newUser.setPrenom(prenom);
		newRoles = getNewRoles();
		
		for (String titreRole : getNewRolesSL()) {
			
			System.err.println(titreRole);
			Role role = new Role();
		    role = facade.findRoleByTitre(titreRole);
			System.out.println(role.getTitreRole());
			newRoles.add(role);
			
		}
       		
		
		facade.addUser(newUser, newRoles);
		newRolesSL = new ArrayList<String>();
		setLogin("");
		setPassword("");
		setNom("");
		setPrenom("");
		return "listUtilisateurs";
	}
	
	public String mergeUser(){
		
		
		try {
			facade = getFacade();
			User newUser = facade.getUserByName(userTemp.getNom());
			newUser.setLogin(login);
			newUser.setPassword(password);
			newUser.setNom(nom);
			newUser.setPrenom(prenom);
			newRoles = getNewRoles();
			
			for (String titreRole : getNewRolesSL()) {
				
				System.err.println(titreRole);
				Role role = new Role();
			    role = facade.findRoleByTitre(titreRole);
				System.out.println(role.getTitreRole());
				newRoles.add(role);
				
			}
	       		
			facade.updateUser(newUser, newRoles);
			newRolesSL = new ArrayList<String>();
			setLogin("");
			setPassword("");
			setNom("");
			setPrenom("");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "listUtilisateurs";
	}
	
	
	
	public String listUtilisateurs(){
		
		setLogin("");
		setPassword("");
		setNom("");
		setPrenom("");
		
		return "listUtilisateurs";
	}
	
	public String addUser(){
		
		return "addUser";
	}
	
	public String removeUser(){
		
		try {
			facade = getFacade();
			User user = facade.getUserByName(nom);
			facade.removeUser(user);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	public String updateUser(){
		
		try {
			facade = getFacade();
			User user = facade.getUserByName(nom);
			userTemp = new User();
			userTemp.setNom(nom);
			setLogin(user.getLogin());
			setPassword(user.getPassword());
			setNom(user.getNom());
			setPrenom(user.getPrenom());
			setAnciensRoles(user.getRoles());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "updateUser";
	}
	
	
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FacadeRemote getFacade() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url","localhost:1099");
		InitialContext context = new InitialContext(properties);
		facade = (FacadeRemote) context.lookup("facadeSB/remote");
		return facade;
	}
	public void setFacade(FacadeRemote facade) {
		this.facade = facade;
	}

	public List<User> getListUsers() {
		try {
			facade = getFacade();
			listUsers = facade.listAllUsers();
			return listUsers;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	public List<Role> getAllroles() {
		try {
			facade = getFacade();
			allroles = facade.listRoles();
			return allroles;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setAllroles(List<Role> allroles) {
		this.allroles = allroles;
	}

	public List<Role> getNewRoles() {
		newRoles = getAllroles();
		newRoles.remove(0);
		newRoles.remove(0);
		newRoles.remove(0);
		newRoles.remove(0);
		return newRoles;
	}

	public void setNewRoles(List<Role> newRoles) {
		this.newRoles = newRoles;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<SelectItem> getAllrolesSL() {
		allrolesSL =  new ArrayList<SelectItem>();
		for (Role role : getAllroles()) {
	        SelectItem e = new SelectItem(role.getTitreRole(),role.getTitreRole());
			allrolesSL.add(e);
		}
		return allrolesSL;
	}

	public void setAllrolesSL(List<SelectItem> allrolesSL) {
		this.allrolesSL = allrolesSL;
	}

	public List<String> getNewRolesSL() {
		return newRolesSL;
	}

	public void setNewRolesSL(List<String> newRolesSL) {
		this.newRolesSL = newRolesSL;
	}

	public List<Role> getAnciensRoles() {
		return anciensRoles;
	}

	public void setAnciensRoles(List<Role> anciensRoles) {
		this.anciensRoles = anciensRoles;
	}

	public User getUserTemp() {
		return userTemp;
	}

	public void setUserTemp(User userTemp) {
		this.userTemp = userTemp;
	}

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}

	
    public Boolean ifCampusManager(){
    	
    	List<Role> roles = user.getRoles();
    	for (Role role : roles) {
			if(role.getCodeRole().equals("CM")){
				return false;
			}
		}
    	return true;
    }
	
    public Boolean ifAdministrateur(){
    	List<Role> roles = user.getRoles();
    	for (Role role : roles) {
			if(role.getCodeRole().equals("Admin")){
				return false;
			}
		}
    	return true;
    }
    
    public Boolean ifIntervenant(){
    	List<Role> roles = user.getRoles();
    	for (Role role : roles) {
			if(role.getCodeRole().equals("Intervenant")){
				return false;
			}
		}
    	return true;
    }
    
    public Boolean ifEtudiant(){
    	List<Role> roles = user.getRoles();
    	for (Role role : roles) {
			if(role.getCodeRole().equals("Etudiant")){
				return false;
			}
		}
    	return true;
    }

	public Boolean getBool2() {
		return bool2;
	}

	public void setBool2(Boolean bool2) {
		this.bool2 = bool2;
	}

	public Boolean getBool3() {
		return bool3;
	}

	public void setBool3(Boolean bool3) {
		this.bool3 = bool3;
	}

	public Boolean getBool4() {
		return bool4;
	}

	public void setBool4(Boolean bool4) {
		this.bool4 = bool4;
	}
	
	
	
	
}
