package com.supinfo.timetableasy.sessionBean;
import java.util.List;

import javax.ejb.Remote;

import org.quartz.xml.ValidationException;

import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Modalite;
import com.supinfo.timetableasy.entity.Role;
import com.supinfo.timetableasy.entity.User;

@Remote
public interface UserSessionBeanRemote {
	
	public User authentification( String login, String password) throws ValidationException ,javax.xml.bind.ValidationException;
	public void addUser(User user, List<Role> roles); 
	public void updateUser(User user, List<Role> roles);	
	public void removeUser(User user);
	public List<User> listAllUsers();	
	public List<User> listUsersByRole(Role role);	
	public User getUserByName(String nom);
	public void inscrireEtudiant(User user, Classe classe);	
	public List<Role> listRoles();
	public Role findRole(Long id);
	public Role findRoleByTitre(String titre);
    public User findUser(Long id);
    
		
}
