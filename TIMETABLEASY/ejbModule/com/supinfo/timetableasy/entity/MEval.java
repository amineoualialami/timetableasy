package com.supinfo.timetableasy.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="meval")
public class MEval implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMEval;
	
	@ManyToOne
	@JoinColumn(name="idModalite" )
	private Modalite modalite;
	
	@ManyToOne
	@JoinColumn(name="idCour")
	private Eval eval;
	
	
	private int horraireModalite;
	
	
	public MEval() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdMEval() {
		return idMEval;
	}


	public void setIdMEval(Long idMEval) {
		this.idMEval = idMEval;
	}


	public Modalite getModalite() {
		return modalite;
	}


	public void setModalite(Modalite modalite) {
		this.modalite = modalite;
	}


	public Eval getEval() {
		return eval;
	}


	public void setEval(Eval eval) {
		this.eval = eval;
	}


	public int getHorraireModalite() {
		return horraireModalite;
	}


	public void setHorraireModalite(int horraireModalite) {
		this.horraireModalite = horraireModalite;
	}
	
	
	

}
