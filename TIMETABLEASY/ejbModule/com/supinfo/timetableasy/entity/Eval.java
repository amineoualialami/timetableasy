package com.supinfo.timetableasy.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="eval")
public class Eval  implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEval;
    
	private String nom;
	
	//hada khask tchoufff lih l3ayba bach ytcalcula automatik f linitialisation dial had l entity
	@Transient   
	private int volumeHorraire;
	
	
	@OneToMany ( cascade=CascadeType.ALL , fetch=FetchType.EAGER )
	private List<MEval> mEvals;
	
	
	public Eval() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdEval() {
		return idEval;
	}


	public void setIdEval(Long idEval) {
		this.idEval = idEval;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getVolumeHorraire() {
		return volumeHorraire;
	}


	public void setVolumeHorraire(int volumeHorraire) {
		this.volumeHorraire = volumeHorraire;
	}


	public List<MEval> getMEvals() {
		return mEvals;
	}


	public void setMEvals(List<MEval> evals) {
		mEvals = evals;
	}
	
	
	
	
	

}
