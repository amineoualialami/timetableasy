package com.supinfo.timetableasy.sessionBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supinfo.timetableasy.entity.Eval;
import com.supinfo.timetableasy.entity.MEval;
import com.supinfo.timetableasy.entity.Modalite;

/**
 * Session Bean implementation class EvalSessionBean
 */
@Stateless(name = "evalSB")
public class EvalSessionBean implements EvalSessionBeanRemote {

	
	@PersistenceContext(unitName="timetableasyPU")
    EntityManager em; 
	
    /**
     * Default constructor. 
     */
    public EvalSessionBean() {
    }

	public List<Eval> ListEvals() {

		Query query = em.createQuery("select e from Eval e");
		List<Eval> listEvals = query.getResultList();
		return listEvals;
	}

	public List<MEval> ListMEvalsByEval(Eval eval) {

		Query query = em.createQuery("select e from Eval e");
		List<Eval> listEvals = query.getResultList();
		List<MEval> listMEvals = new ArrayList<MEval>();
		for (Eval eval2 : listEvals) {
			if (eval2==eval){
				listMEvals = eval2.getMEvals();
			}
		}
		
		return listMEvals;
	}

	public void addEval(Eval eval, List<MEval> mEval) {

		eval.setMEvals(mEval);
		em.persist(eval);
		
	}


	public void removeEval(Eval eval) {
		em.remove(em.merge(eval));
	}

	public void updateEval(Eval eval, List<MEval> mEval) {
		eval.setMEvals(mEval);
		em.merge(eval);
		
	}



}
