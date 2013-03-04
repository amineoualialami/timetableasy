package com.supinfo.timeweb.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.supinfo.timetableasy.facade.FacadeRemote;
import com.supinfo.timetableasy.entity.*;


public class CourManagedBean {

	public FacadeRemote facade;
	public List<Modalite> listModalites;
	public String nom;
	public int horraireModalite1;
	public Modalite modalite1;
	public int horraireModalite2;
	public Modalite modalite2;
	public int horraireModalite3;
	public Modalite modalite3;
	public List<Cour> listCourss;
	public MPedagogique mPedagogiques;
	
	

	
	public String listCours(){
		setNom("");
		setHorraireModalite1(0);
		setHorraireModalite2(0);
		setHorraireModalite3(0);
		try {
			facade = getFacade();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listCours";
	}
	
	
	public String persistCour(){
		
		try {
			facade=getFacade();
			MPedagogique mpedagogique1 = new MPedagogique();
			mpedagogique1.setModalite(facade.listModalites().get(0));
			mpedagogique1.setHorraireModalite(getHorraireModalite1());
			
			MPedagogique mpedagogique2 = new MPedagogique();
			mpedagogique2.setModalite(facade.listModalites().get(1));
			mpedagogique2.setHorraireModalite(getHorraireModalite2());
			
			MPedagogique mpedagogique3 = new MPedagogique();
			mpedagogique3.setModalite(facade.listModalites().get(2));
			mpedagogique3.setHorraireModalite(getHorraireModalite3());
			
			Cour newCour = new Cour();
			newCour.setNom(getNom());
			List<MPedagogique> mpedagogiques = new ArrayList<MPedagogique>();
			mpedagogiques.add(mpedagogique1);
			mpedagogiques.add(mpedagogique2);
			mpedagogiques.add(mpedagogique3);
			
			
			facade.addCour(newCour, mpedagogiques);
			setNom("");
			setHorraireModalite1(0);
			setHorraireModalite2(0);
			setHorraireModalite3(0);
			return "listCours";
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public String detailsCour(){
		
		try {
			facade = getFacade();
			List<Cour> allCours = facade.listCours();
			for (Cour cour : allCours) {
				if(cour.getNom().equals(nom)){
					
					setModalite1(facade.listModalites().get(0));
					setModalite2(facade.listModalites().get(1));
					setModalite3(facade.listModalites().get(2));
					setHorraireModalite1(cour.getMPedagogiques().get(0).getHorraireModalite());
					setHorraireModalite2(cour.getMPedagogiques().get(1).getHorraireModalite());
					setHorraireModalite3(cour.getMPedagogiques().get(2).getHorraireModalite());
					
				}
			}
			return "detailsCour";
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	public void removeCour(){
		try {
			facade = getFacade();
			List<Cour> allCours = facade.listCours();
			for (Cour cour : allCours) {
				if(cour.getNom().equals(nom)){
					facade.removeCour(cour);
				}
			}
			setNom("");
			setHorraireModalite1(0);
			setHorraireModalite2(0);
			setHorraireModalite3(0);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public String updateCour(){
		try {
			facade = getFacade();
			List<Cour> allCours = facade.listCours();
			for (Cour cour : allCours) {
				if(cour.getNom().equals(nom)){
					
					setModalite1(facade.listModalites().get(0));
					setModalite2(facade.listModalites().get(1));
					setModalite3(facade.listModalites().get(2));
					setHorraireModalite1(cour.getMPedagogiques().get(0).getHorraireModalite());
					setHorraireModalite2(cour.getMPedagogiques().get(1).getHorraireModalite());
					setHorraireModalite3(cour.getMPedagogiques().get(2).getHorraireModalite());
					
				}
			}
			
			return "updateCour";
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String mergeCour(){
		try {
			facade=getFacade();
			MPedagogique mpedagogique1 = new MPedagogique();
			mpedagogique1.setModalite(facade.listModalites().get(0));
			mpedagogique1.setHorraireModalite(getHorraireModalite1());
			
			MPedagogique mpedagogique2 = new MPedagogique();
			mpedagogique2.setModalite(facade.listModalites().get(1));
			mpedagogique2.setHorraireModalite(getHorraireModalite2());
			
			MPedagogique mpedagogique3 = new MPedagogique();
			mpedagogique3.setModalite(facade.listModalites().get(2));
			mpedagogique3.setHorraireModalite(getHorraireModalite3());
			
			Cour newCour = new Cour();
			List<MPedagogique> mpedagogiques = new ArrayList<MPedagogique>();
			mpedagogiques.add(mpedagogique1);
			mpedagogiques.add(mpedagogique2);
			mpedagogiques.add(mpedagogique3);
			List<Cour> allCours = facade.listCours();
			for (Cour cour : allCours) {
				if(cour.getNom().equals(nom)){
					facade.updateCour(cour, mpedagogiques);
				}
			}
			setNom("");
			setHorraireModalite1(0);
			setHorraireModalite2(0);
			setHorraireModalite3(0);
			
			return "listCours";
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
		
		
		
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

	public List<Modalite> getListModalites() {
		try {
			facade = getFacade();
			return listModalites;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setListModalites(List<Modalite> listModalites) {
		this.listModalites = listModalites;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getHorraireModalite1() {
		return horraireModalite1;
	}


	public void setHorraireModalite1(int horraireModalite1) {
		this.horraireModalite1 = horraireModalite1;
	}


	public Modalite getModalite1() {
		try {
			facade = getFacade();
			modalite1 = facade.listModalites().get(0);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modalite1;
	}


	public void setModalite1(Modalite modalite1) {
		this.modalite1 = modalite1;
	}


	public int getHorraireModalite2() {
		try {
			facade = getFacade();
			modalite2 = facade.listModalites().get(1);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return horraireModalite2;
	}


	public void setHorraireModalite2(int horraireModalite2) {
		this.horraireModalite2 = horraireModalite2;
	}


	public Modalite getModalite2() {
		return modalite2;
	}


	public void setModalite2(Modalite modalite2) {
		
		this.modalite2 = modalite2;
	}


	public int getHorraireModalite3() {
		try {
			facade = getFacade();
			modalite3 = facade.listModalites().get(2);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return horraireModalite3;
	}


	public void setHorraireModalite3(int horraireModalite3) {
		this.horraireModalite3 = horraireModalite3;
	}


	public Modalite getModalite3() {
		return modalite3;
	}


	public void setModalite3(Modalite modalite3) {
		this.modalite3 = modalite3;
	}


	public List<Cour> getListCourss() {
		try {
			facade = getFacade();
			listCourss = facade.listCours();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listCourss;
	}


	public void setListCourss(List<Cour> listCourss) {
		this.listCourss = listCourss;
	}


	public MPedagogique getMPedagogiques() {
		return mPedagogiques;
	}


	public void setMPedagogiques(MPedagogique pedagogiques) {
		mPedagogiques = pedagogiques;
	}


	
	
	
	
	
}
