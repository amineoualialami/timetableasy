package com.supinfo.timetableasy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="periodedetude")
public class PeriodeDetude implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPeriodeDetude;
	
	private String nom;
	
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	
	@ManyToMany (fetch=FetchType.EAGER )
	private List<Cour> cours;

    public PeriodeDetude() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdPeriodeDetude() {
		return idPeriodeDetude;
	}

	public void setIdPeriodeDetude(Long idPeriodeDetude) {
		this.idPeriodeDetude = idPeriodeDetude;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public List<Cour> getCours() {
		return cours;
	}

	public void setCours(List<Cour> cours) {
		this.cours = cours;
	}
    
    
	
	
}
