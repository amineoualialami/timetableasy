package com.supinfo.timetableasy.sessionBean;
import java.util.List;

import javax.ejb.Remote;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Espace;
import com.supinfo.timetableasy.entity.User;

@Remote
public interface CampusSessionBeanRemote {
	
	public void addCampus(Campus campus, List<User> users , List<Espace> espaces);
	public void updateCampus(Campus campus, List<User> users , List<Espace> espaces);
	public void removeCampus(Campus campus);
	public List<Campus> listAllCampus();
	public List<Espace> listEspacesByCampus(Campus campus);
	public Campus getCampusByNom(String nom	);
	public Campus findCampus(Long id);
	public Espace findEspace(Long id);
	
	

}
