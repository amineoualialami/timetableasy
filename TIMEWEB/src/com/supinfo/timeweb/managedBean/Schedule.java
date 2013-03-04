package com.supinfo.timeweb.managedBean;

import java.util.Date;

import org.apache.myfaces.custom.schedule.model.ScheduleEntry;
import org.apache.myfaces.custom.schedule.model.SimpleScheduleModel;

public class Schedule extends SimpleScheduleModel{
	
	public Schedule() {
		super();
		setMode(WORKWEEK);
	}

	@Override
	public boolean containsDate(Date arg0) {
		// TODO Auto-generated method stub
		return super.containsDate(arg0);
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return super.get(index);
	}

	@Override
	public int getMode() {
		// TODO Auto-generated method stub
		return super.getMode();
	}

	@Override
	public Date getSelectedDate() {
		// TODO Auto-generated method stub
		return super.getSelectedDate();
	}

	@Override
	public ScheduleEntry getSelectedEntry() {
		// TODO Auto-generated method stub
		return super.getSelectedEntry();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return super.isEmpty();
	}

	@Override
	public boolean isEntrySelected() {
		// TODO Auto-generated method stub
		return super.isEntrySelected();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return super.size();
	}

	@Override
	public void removeSelectedEntry() {
		// TODO Auto-generated method stub
		super.removeSelectedEntry();
	}

	
	
}
