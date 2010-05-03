package model;

import java.util.ArrayList;

public class Drying extends model.Process {
	private long minTime;
	private long idealTime;
	private long maxTime;
	private ArrayList<Depot> Depots;

	public Drying(long minTime, long idealTime, long maxTime, int processStep, ProcessLine processLine) throws RuntimeException{
		super(processStep, processLine);
		this.setMinTime(minTime);
		this.setIdealTime(idealTime);
		this.setMaxTime(maxTime);
	}

	public long getMinTime(){
		return this.minTime;
	}

	public void setMinTime(long minTime) throws RuntimeException{
		if (minTime<=0){
			throw new RuntimeException("minTime can't be a negative number");
		} else {
			this.minTime=minTime;
		}
	}

	public long getIdealTime(){
		return 0;
	}

	public void setIdealTime(long idealTime) throws RuntimeException{
	}

	public long getMaxTime(){
		return 0;
	}

	public void setMaxTime(long maxTime) throws RuntimeException{
	}

	public ArrayList<Depot> getDepots(){
		return null;
	}

	public void addDepot(Depot depot){
	}

	public void removeDepot(Depot depot){
	}

}
