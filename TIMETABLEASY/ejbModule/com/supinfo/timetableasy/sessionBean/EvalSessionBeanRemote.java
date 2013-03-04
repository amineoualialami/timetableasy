package com.supinfo.timetableasy.sessionBean;
import java.util.List;

import javax.ejb.Remote;

import com.supinfo.timetableasy.entity.Eval;
import com.supinfo.timetableasy.entity.MEval;
import com.supinfo.timetableasy.entity.Modalite;

@Remote
public interface EvalSessionBeanRemote {

	public void addEval (Eval eval, List<MEval> mEval);
	public void updateEval(Eval eval, List<MEval> mEval);
	public void removeEval(Eval eval);
	
	public List<Eval> ListEvals ();
	
	public List<MEval> ListMEvalsByEval(Eval eval);
	
}
