package com.supinfo.timetableasy.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="modalite")
public class Modalite implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idModalite;
	
	
	private String nom;
	
	public Long getIdModalite() {
		return idModalite;
	}
	public void setIdModalite(Long idModalite) {
		this.idModalite = idModalite;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	

}
