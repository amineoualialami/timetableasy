package com.supinfo.timetableasy.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="evenement")
public class Evenement implements Serializable {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEvenement;
	
	private String type;
	
	@ManyToOne
	private Espace espace;
	
	@ManyToOne
	private Classe classe;
	
	@ManyToOne
	private User intervenant;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebut;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;
	
	@Transient
	private float duree;
	
	@ManyToOne
	private Cour cour;

	public Long getIdEvenement() {
		return idEvenement;
	}

	public void setIdEvenement(Long idEvenement) {
		this.idEvenement = idEvenement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Espace getEspace() {
		return espace;
	}

	public void setEspace(Espace espace) {
		this.espace = espace;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public User getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(User intervenant) {
		this.intervenant = intervenant;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public float getDuree() {
		return duree;
	}

	public void setDuree(float duree) {
		this.duree = duree;
	}

	public Cour getCour() {
		return cour;
	}

	public void setCour(Cour cour) {
		this.cour = cour;
	}
	
	@PostLoad
    @PostPersist
    @PostUpdate
	public void calculateDuree(){
		Long debut = getDateDebut().getTime();
		Long fin = getDateFin().getTime();
		Long resultat  = fin - debut;
		duree = resultat.floatValue();
		duree = duree/3600000;
	}
	
	
	
	
	

}
