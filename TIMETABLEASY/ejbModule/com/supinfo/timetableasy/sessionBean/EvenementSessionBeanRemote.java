package com.supinfo.timetableasy.sessionBean;
import java.util.List;

import javax.ejb.Remote;

import com.supinfo.timetableasy.entity.Evenement;

@Remote
public interface EvenementSessionBeanRemote {
	
	public void addEvenement(Evenement evenement);
	public void removeEvenement(Evenement evenement);
	public List<Evenement> listEvenementByClasse(String nomClasse);
	public List<Evenement> listEvenements();

}
