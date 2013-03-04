package com.supinfo.timetableasy.sessionBean;
import java.util.List;

import javax.ejb.Remote;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Cursus;
import com.supinfo.timetableasy.entity.User;

@Remote
public interface ClasseSessionBeanRemote {
	
	public void addClasse(Classe classe,List<User> etudiants ,Cursus cursus, Campus campus);
	public void updateClasse(Classe classe,List<User> etudiants, Cursus cursus, Campus campus);
	public void removeClasse(Classe classe);
	public List<Classe> listClasses();
	public List<Classe>	listClassesByCursus(Cursus cursus);
	public List<Classe> listClassesByCampus(Campus campus);
	public List<User> listUsersByClasse(Classe classe);
	public Classe listClasseByName(String name);
	public Classe findClasse(Long id);
	
	

}
