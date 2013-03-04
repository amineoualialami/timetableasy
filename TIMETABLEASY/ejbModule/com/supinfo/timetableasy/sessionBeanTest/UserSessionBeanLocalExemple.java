package com.supinfo.timetableasy.sessionBeanTest;



import java.util.List;

import javax.ejb.Remote;


import com.supinfo.timetableasy.entity.Cour;
import com.supinfo.timetableasy.entity.Modalite;
import com.supinfo.timetableasy.entity.User;
import com.supinfo.timetableasy.entity.Role;

@Remote
public interface UserSessionBeanLocalExemple {
	
	public void addUser(User user);
	public List<Role> listRoles();
	
	
	public void addCour (Cour cour);
	public List<Modalite> modalites();
	
	public  List<Cour> ListCour ();
	public Cour findCour(Long courId);
	
	public void removeCour(Cour cour);
	
	

}
