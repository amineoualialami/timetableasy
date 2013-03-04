package com.supinfo.timetableasy.sessionBean;
import java.util.List;

import javax.ejb.Remote;

import com.supinfo.timetableasy.entity.Cursus;
import com.supinfo.timetableasy.entity.PeriodeDetude;
import com.supinfo.timetableasy.entity.User;

@Remote
public interface CursusSessionBeanRemote {
	
	public void addCursus(Cursus cursus, List<User> users, List<PeriodeDetude> periodeDetudes);
    public void updateCursus(Cursus cursus, List<User> users, List<PeriodeDetude> periodeDetudes);
    public void removeCursus(Cursus cursus);
    public List<Cursus> listCursus();
    public List<PeriodeDetude> listPeriodeDetudesByCursus(Cursus cursus);
    public List<User> listResponsableCursus(Cursus cursus);
    public Cursus getCursusByName(String nom);
    public Cursus FindCursus(Long id);
    public PeriodeDetude findPeriodeDetude(Long id);
    
    
}
