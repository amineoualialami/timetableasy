package com.supinfo.timetableasy.sessionBean;
import java.util.List;

import javax.ejb.Remote;

import com.supinfo.timetableasy.entity.Cour;
import com.supinfo.timetableasy.entity.MPedagogique;
import com.supinfo.timetableasy.entity.Modalite;

@Remote
public interface CourSessionBeanRemote {
	
	public void addCour (Cour cour, List<MPedagogique> mPedagogiques);
	public void updateCour(Cour cour, List<MPedagogique> mPedagogiques);
	public void removeCour(Cour cour);
	
	public List<Modalite> listModalites();	
	public List<Cour> listCours ();
	
	public List<MPedagogique> ListMPedagogiquesByCour(Cour cour);
	public Cour  listCourByName(String nom);
	public Cour findCour(Long id);
	

	

}
