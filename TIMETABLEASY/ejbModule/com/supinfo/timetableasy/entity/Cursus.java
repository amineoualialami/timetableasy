package com.supinfo.timetableasy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="cursus")

public class Cursus implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCursus;
	
	
	private String nom;
	
	
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut ;
	
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	
	@ManyToMany
	private List<User> users;//hado les responsables machi étudiant
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cursus_fk")
	private List<PeriodeDetude> periodeDetudes;
	
	
	public Cursus() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdCursus() {
		return idCursus;
	}


	public void setIdCursus(Long idCursus) {
		this.idCursus = idCursus;
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


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public List<PeriodeDetude> getPeriodeDetudes() {
		return periodeDetudes;
	}


	public void setPeriodeDetudes(List<PeriodeDetude> periodeDetudes) {
		this.periodeDetudes = periodeDetudes;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	
	

}
