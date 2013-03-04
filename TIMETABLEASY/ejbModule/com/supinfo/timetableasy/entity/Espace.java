package com.supinfo.timetableasy.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="espace")
public class Espace implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEspace;
	
	private String nom;
	
	private String description;
	
	
	public Espace() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdEspace() {
		return idEspace;
	}


	public void setIdEspace(Long idEspace) {
		this.idEspace = idEspace;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
