package com.supinfo.timetableasy.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "campus")
public class Campus implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCampus;

	private String nom;

	@ManyToMany
	private List<User> users;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "campus_fk")
	private List<Espace> espaces;

	public Campus() {
		// TODO Auto-generated constructor stu
	}

	public Long getIdCampus() {
		return idCampus;
	}

	public void setIdCampus(Long idCampus) {
		this.idCampus = idCampus;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Espace> getEspaces() {
		return espaces;
	}

	public void setEspaces(List<Espace> espaces) {
		this.espaces = espaces;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
