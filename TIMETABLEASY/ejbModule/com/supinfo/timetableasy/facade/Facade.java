package com.supinfo.timetableasy.facade;

import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
import com.supinfo.timetableasy.sessionBean.CampusSessionBeanRemote;
import com.supinfo.timetableasy.sessionBean.ClasseSessionBeanRemote;
import com.supinfo.timetableasy.sessionBean.CourSessionBeanRemote;
import com.supinfo.timetableasy.sessionBean.CursusSessionBeanRemote;
import com.supinfo.timetableasy.sessionBean.EvalSessionBeanRemote;
import com.supinfo.timetableasy.sessionBean.EvenementSessionBeanRemote;
import com.supinfo.timetableasy.sessionBean.UserSessionBeanRemote;

/**
 * Session Bean implementation class facade
 */
@Stateless(name = "facadeSB")
public class Facade implements FacadeRemote {

	/**
	 * Default constructor.
	 * 
	 * @throws NamingException
	 */
	public Context initializ() throws NamingException {

		Properties properties = new Properties();
		properties.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs",
				"=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");
		InitialContext context = new InitialContext(properties);
		return context;

	}

	private CourSessionBeanRemote courSB = getCourSB();
	private CampusSessionBeanRemote campusSB = getCampusSB();
	private ClasseSessionBeanRemote classeSB = getClasseSB();
	private CursusSessionBeanRemote cursusSB = getCursusSB();
	private UserSessionBeanRemote userSB = getUserSB();
	private EvalSessionBeanRemote evalSB = getEvalSB();
	private EvenementSessionBeanRemote eventSB = getEventSB();

	public Facade() {
		// TODO Auto-generated constructor stub
	}

	public List<Cour> listCours() {
		return courSB.listCours();
	}

	public List<Eval> ListEvals() {
		return evalSB.ListEvals();
	}

	public List<MEval> ListMEvalsByEval(Eval eval) {
		return evalSB.ListMEvalsByEval(eval);
	}

	public List<MPedagogique> ListMPedagogiquesByCour(Cour cour) {
		return courSB.ListMPedagogiquesByCour(cour);
	}

	public void addCampus(Campus campus, List<User> users, List<Espace> espaces) {
		campusSB.addCampus(campus, users, espaces);
	}

	public void addClasse(Classe classe, List<User> etudiants, Cursus cursus,
			Campus campus) {
		classeSB.addClasse(classe, etudiants, cursus, campus);

	}

	public void addCour(Cour cour, List<MPedagogique> pedagogiques) {
		courSB.addCour(cour, pedagogiques);
	}

	public void addCursus(Cursus cursus, List<User> users,
			List<PeriodeDetude> periodeDetudes) {
		cursusSB.addCursus(cursus, users, periodeDetudes);
	}

	public void addEval(Eval eval, List<MEval> eval2) {
		evalSB.addEval(eval, eval2);
	}

	public void addUser(User user, List<Role> roles) {
		userSB.addUser(user, roles);
	}

	public User getUserByName(String nom) {
		return userSB.getUserByName(nom);
	}

	public void inscrireEtudiant(User user, Classe classe) {
		userSB.inscrireEtudiant(user, classe);
	}

	public List<Campus> listAllCampus() {
		return campusSB.listAllCampus();
	}

	public List<User> listAllUsers() {
		return userSB.listAllUsers();
	}

	public Classe listClasseByName(String name) {
		return classeSB.listClasseByName(name);
	}

	public List<Classe> listClasses() {
		return classeSB.listClasses();
	}

	public List<Classe> listClassesByCursus(Cursus cursus) {
		return classeSB.listClassesByCursus(cursus);
	}

	public List<Cursus> listCursus() {
		return cursusSB.listCursus();
	}

	public List<Espace> listEspacesByCampus(Campus campus) {
		return campusSB.listEspacesByCampus(campus);
	}

	public List<Modalite> listModalites() {
		return courSB.listModalites();
	}

	public List<PeriodeDetude> listPeriodeDetudesByCursus(Cursus cursus) {
		return cursusSB.listPeriodeDetudesByCursus(cursus);
	}

	public List<User> listResponsableCursus(Cursus cursus) {
		return cursusSB.listResponsableCursus(cursus);
	}

	public List<Role> listRoles() {
		return userSB.listRoles();
	}

	public List<User> listUsersByClasse(Classe classe) {
		return classeSB.listUsersByClasse(classe);
	}

	public List<User> listUsersByRole(Role role) {
		return userSB.listUsersByRole(role);
	}

	public void removeCampus(Campus campus) {
		campusSB.removeCampus(campus);

	}

	public void removeClasse(Classe classe) {
		classeSB.removeClasse(classe);

	}

	public void removeCour(Cour cour) {
		courSB.removeCour(cour);

	}

	public void removeCursus(Cursus cursus) {
		cursusSB.removeCursus(cursus);

	}

	public void removeEval(Eval eval) {
		evalSB.removeEval(eval);

	}

	public void removeUser(User user) {
		userSB.removeUser(user);

	}

	public void updateCampus(Campus campus, List<User> users,
			List<Espace> espaces) {
		campusSB.updateCampus(campus, users, espaces);

	}

	public void updateClasse(Classe classe, List<User> etudiants,
			Cursus cursus, Campus campus) {
		classeSB.updateClasse(classe, etudiants, cursus, campus);
	}

	public void updateCour(Cour cour, List<MPedagogique> pedagogiques) {
		courSB.updateCour(cour, pedagogiques);
	}

	public void updateCursus(Cursus cursus, List<User> users,
			List<PeriodeDetude> periodeDetudes) {
		cursusSB.updateCursus(cursus, users, periodeDetudes);

	}

	public void updateEval(Eval eval, List<MEval> eval2) {
		evalSB.updateEval(eval, eval2);

	}

	public void updateUser(User user, List<Role> roles) {
		userSB.updateUser(user, roles);

	}

	public User authentification(String login, String password)
			throws ValidationException, javax.xml.bind.ValidationException {

		return userSB.authentification(login, password);
	}

	public CourSessionBeanRemote getCourSB() {

		try {
			CourSessionBeanRemote courSB;
			courSB = (CourSessionBeanRemote) initializ()
					.lookup("courSB/remote");
			return courSB;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CampusSessionBeanRemote getCampusSB() {

		try {
			CampusSessionBeanRemote campusSB;
			campusSB = (CampusSessionBeanRemote) initializ().lookup(
					"campusSB/remote");
			return campusSB;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ClasseSessionBeanRemote getClasseSB() {

		try {
			ClasseSessionBeanRemote classeSB;
			classeSB = (ClasseSessionBeanRemote) initializ().lookup(
					"classeSB/remote");
			return classeSB;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CursusSessionBeanRemote getCursusSB() {

		try {
			CursusSessionBeanRemote cursusSB;
			cursusSB = (CursusSessionBeanRemote) initializ().lookup(
					"cursusSB/remote");
			return cursusSB;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public UserSessionBeanRemote getUserSB() {

		try {
			UserSessionBeanRemote userSB;
			userSB = (UserSessionBeanRemote) initializ()
					.lookup("userSB/remote");
			return userSB;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public EvalSessionBeanRemote getEvalSB() {

		try {
			EvalSessionBeanRemote evalSB;
			evalSB = (EvalSessionBeanRemote) initializ()
					.lookup("evalSB/remote");
			return evalSB;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public EvenementSessionBeanRemote getEventSB() {
		try {
			EvenementSessionBeanRemote evenementSB;
			evenementSB = (EvenementSessionBeanRemote) initializ().lookup(
					"evenementSB/remote");
			return evenementSB;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Role findRole(Long id) {
		return userSB.findRole(id);
	}

	public Role findRoleByTitre(String titre) {
		return userSB.findRoleByTitre(titre);
	}

	public Campus getCampusByNom(String nom) {
		return campusSB.getCampusByNom(nom);
	}

	public Cursus FindCursus(Long id) {
		return cursusSB.FindCursus(id);
	}

	public Campus findCampus(Long id) {
		return campusSB.findCampus(id);
	}

	public User findUser(Long id) {
		return userSB.findUser(id);
	}

	public Cursus getCursusByName(String nom) {
		return cursusSB.getCursusByName(nom);
	}

	public Cour listCourByName(String nom) {
		return courSB.listCourByName(nom);
	}

	public List<Classe> listClassesByCampus(Campus campus) {
		return classeSB.listClassesByCampus(campus);
	}

	public Classe findClasse(Long id) {
		return classeSB.findClasse(id);
	}

	public PeriodeDetude findPeriodeDetude(Long id) {
		return cursusSB.findPeriodeDetude(id);
	}

	public Cour findCour(Long id) {
		return courSB.findCour(id);
	}

	public Espace findEspace(Long id) {
		return campusSB.findEspace(id);
	}

	public void addEvenement(Evenement evenement) {
		eventSB.addEvenement(evenement);
	}

	public List<Evenement> listEvenementByClasse(String nomClasse) {
		return eventSB.listEvenementByClasse(nomClasse);
	}

	public List<Evenement> listEvenements() {
		return eventSB.listEvenements();
	}

	public void removeEvenement(Evenement evenement) {
		eventSB.removeEvenement(evenement);
	}
}
