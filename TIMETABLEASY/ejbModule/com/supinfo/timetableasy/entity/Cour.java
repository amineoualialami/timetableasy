package com.supinfo.timetableasy.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="cour")
public class Cour implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCour;
	
	
	private String nom;
	
	//hada khask tchoufff lih l3ayba bach ytcalcula automatik f linitialisation dial had l entity
	@Transient   
	private int volumeHorraire;
	

	@OneToMany ( cascade=CascadeType.ALL  )
	private List<MPedagogique> mPedagogiques;

	public Long getIdCour() {
		return idCour;
	}

	public void setIdCour(Long idCour) {
		this.idCour = idCour;
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

	public List<MPedagogique> getMPedagogiques() {
		
		if (mPedagogiques==null){
			mPedagogiques = new ArrayList<MPedagogique>();
		}
		
		return mPedagogiques;
	}

	public void setMPedagogiques(List<MPedagogique> pedagogiques) {
		mPedagogiques = pedagogiques;
	}
	
	
	@PostLoad
    @PostPersist
    @PostUpdate
	public void calculateVH(){
		int i = this.mPedagogiques.get(0).getHorraireModalite();
		int j = this.mPedagogiques.get(1).getHorraireModalite();
		int k = this.mPedagogiques.get(2).getHorraireModalite();
		volumeHorraire=i+j+k;
	}

	
}
