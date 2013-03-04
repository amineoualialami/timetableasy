package com.supinfo.timetableasy.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="mpedagogique")
public class MPedagogique implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMPedagogique;
	
	@ManyToOne
	@JoinColumn(name="idModalite" )
	private Modalite modalite;
	
	
	
	
	private int horraireModalite;
	
	
	
	public Long getIdMPedagogique() {
		return idMPedagogique;
	}
	public void setIdMPedagogique(Long idMPedagogique) {
		this.idMPedagogique = idMPedagogique;
	}
	public Modalite getModalite() {
		return modalite;
	}
	public void setModalite(Modalite modalite) {
		this.modalite = modalite;
	}
	public int getHorraireModalite() {
		return horraireModalite;
	}
	public void setHorraireModalite(int horraireModalite) {
		this.horraireModalite = horraireModalite;
	}

	
	
	

}
