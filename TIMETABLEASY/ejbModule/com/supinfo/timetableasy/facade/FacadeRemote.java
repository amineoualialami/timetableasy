package com.supinfo.timetableasy.facade;
import java.util.List;

import javax.ejb.Remote;

import org.quartz.xml.ValidationException;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Cour;
import com.supinfo.timetableasy.entity.Cursus;
import com.supinfo.timetableasy.entity.Espace;
import com.supinfo.timetableasy.entity.Eval;
import com.supinfo.timetableasy.entity.Evenement;
import com.supinfo.timetableasy.entity.MEval;
import com.supinfo.timetableasy.entity.MPedagogique;
import com.supinfo.timetableasy.entity.Modalite;
import com.supinfo.timetableasy.entity.PeriodeDetude;
import com.supinfo.timetableasy.entity.Role;
import com.supinfo.timetableasy.entity.User;

@Remote
public interface FacadeRemote {
	 
	//Campus
	public void addCampus(Campus campus, List<User> users , List<Espace> espaces);
	public void updateCampus(Campus campus, List<User> users , List<Espace> espaces);
	public void removeCampus(Campus campus);
	public List<Campus> listAllCampus();
	public List<Espace> listEspacesByCampus(Campus campus);
	public Campus getCampusByNom(String nom	);
	public Campus findCampus(Long id);
	public Espace findEspace(Long id);
	
	//Classe
	public void addClasse(Classe classe,List<User> etudiants, Cursus cursus, Campus campus);
	public void updateClasse(Classe classe,List<User> etudiants, Cursus cursus, Campus campus);
	public void removeClasse(Classe classe);
	public List<Classe> listClasses();
	public List<Classe>	listClassesByCursus(Cursus cursus);
	public List<Classe> listClassesByCampus(Campus campus);
	public List<User> listUsersByClasse(Classe classe);
	public Classe listClasseByName(String name);
	public Classe findClasse(Long id);
	
	//Cour
	public void addCour (Cour cour, List<MPedagogique> mPedagogiques);
	public void updateCour(Cour cour, List<MPedagogique> mPedagogiques);
	public void removeCour(Cour cour);
	public List<Modalite> listModalites();	
	public List<Cour> listCours ();
	public List<MPedagogique> ListMPedagogiquesByCour(Cour cour);
	public Cour  listCourByName(String nom);
	public Cour findCour(Long id);
	
	
	//Eval
	public void addEval (Eval eval, List<MEval> mEval);
	public void updateEval(Eval eval, List<MEval> mEval);
	public void removeEval(Eval eval);
	public List<Eval> ListEvals ();
	public List<MEval> ListMEvalsByEval(Eval eval);
	
	//Cursus
	public void addCursus(Cursus cursus, List<User> users, List<PeriodeDetude> periodeDetudes);
    public void updateCursus(Cursus cursus, List<User> users, List<PeriodeDetude> periodeDetudes);
    public void removeCursus(Cursus cursus);
    public List<Cursus> listCursus();
    public List<PeriodeDetude> listPeriodeDetudesByCursus(Cursus cursus);
    public List<User> listResponsableCursus(Cursus cursus);
    public Cursus getCursusByName(String nom);
    public Cursus FindCursus(Long id);
    public PeriodeDetude findPeriodeDetude(Long id);
    
    
    //user
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


	//evenement 
	public void addEvenement(Evenement evenement);
	public List<Evenement> listEvenementByClasse(String nomClasse);
	public List<Evenement> listEvenements();
	public void removeEvenement(Evenement evenement);

	

}
