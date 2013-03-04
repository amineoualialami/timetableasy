package com.supinfo.timetableasy.sessionBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supinfo.timetableasy.entity.Campus;
import com.supinfo.timetableasy.entity.Classe;
import com.supinfo.timetableasy.entity.Cursus;
import com.supinfo.timetableasy.entity.User;

/**
 * Session Bean implementation class ClasseSessionBean
 */
@Stateless(name = "classeSB")
public class ClasseSessionBean implements ClasseSessionBeanRemote {

	/**
	 * Default constructor.
	 */

	@PersistenceContext(unitName = "timetableasyPU")
	EntityManager em;

	public ClasseSessionBean() {
	}

	public void addClasse(Classe classe, List<User> etudiants, Cursus cursus,
			Campus campus) {

		classe.setEtudiants(etudiants);
		classe.setCampus(campus);
		classe.setCursus(cursus);
		em.persist(classe);

	}

	public List<Classe> listClasses() {

		Query query = em.createQuery("select c from Classe c");
		List<Classe> classes = query.getResultList();
		for (Classe classe : classes) {
			classe.getEtudiants().size();
		}

		return classes;
	}

	public List<Classe> listClassesByCursus(Cursus cursus) {

		Query query = em.createQuery("select c from Classe c");
		List<Classe> classes = query.getResultList();
		List<Classe> classesCursus = new ArrayList<Classe>();
		for (Classe classe : classes) {
			classe.getEtudiants().size();
		}
		for (Classe classe : classes) {
			if (classe.getCursus().getNom().equals(cursus.getNom())) {
				classesCursus.add(classe);
			}
		}
		return classesCursus;
	}

	public List<User> listUsersByClasse(Classe classe) {

		Query query = em.createQuery("select u from User u");
		List<Classe> classes = query.getResultList();
		for (Classe classe1 : classes) {
			classe1.getEtudiants().size();
		}
		List<User> users = new ArrayList<User>();
		for (Classe classee : classes) {
			if (classee.getNom().equals(classe.getNom())) {
				users = classee.getEtudiants();
			}
		}

		return users;
	}

	public void removeClasse(Classe classe) {
		em.remove(em.merge(classe));

	}

	public void updateClasse(Classe classe, List<User> etudiants,
			Cursus cursus, Campus campus) {
		classe.setEtudiants(etudiants);
		classe.setCampus(campus);
		classe.setCursus(cursus);
		em.merge(classe);

	}

	public Classe listClasseByName(String name) {

		Query query = em.createQuery("select c from Classe c");
		List<Classe> classes = query.getResultList();
		for (Classe classe : classes) {
			classe.getEtudiants().size();
		}
		for (Classe classe : classes) {
			if (classe.getNom().equals(name)) {
				return classe;
			}
		}
		return null;
	}

	public List<Classe> listClassesByCampus(Campus campus) {
		Query query = em.createQuery("select c from Classe c");
		List<Classe> classes = query.getResultList();
		for (Classe classe : classes) {
			classe.getEtudiants().size();
		}
		List<Classe> classesCampus = new ArrayList<Classe>();
		for (Classe classe : classes) {
			if (classe.getCampus().getNom().equals(campus.getNom())) {
				classesCampus.add(classe);
			}
		}
		return classesCampus;

	}

	public Classe findClasse(Long id) {
		Classe classe = em.find(Classe.class, id);
		return classe;
	}

}
