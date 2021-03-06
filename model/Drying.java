package model;

import java.util.ArrayList;

/** 
 * @author Carl
 */

public class Drying extends model.Process {
	private long minTime;
	private long idealTime;
	private long maxTime;
	private ArrayList<Depot> depots  = new ArrayList<Depot>();

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
		return this.idealTime;
	}

	public void setIdealTime(long idealTime) throws RuntimeException{
		if (idealTime<=0){
			throw new RuntimeException("idealTime can't be a negative number");
		} else if(idealTime <= minTime){
			throw new RuntimeException("idealTime can't be less then minTime");
		} else {
			this.idealTime=idealTime;
		}
	}

	public long getMaxTime(){
		return this.maxTime;
	}

	public void setMaxTime(long maxTime) throws RuntimeException{
		if (maxTime<=0){
			throw new RuntimeException("maxTime can't be a negative number");
		} else if(maxTime <= idealTime){
			throw new RuntimeException("maxTime can't be less then idealTime");
		} else {
			this.maxTime=maxTime;
		}
	}

	public ArrayList<Depot> getDepots(){
		return this.depots;
	}

	public void addDepot(Depot depot){
		this.depots.add(depot);
		if(!depot.getDryings().contains(this)){
			depot.addDrying(this);
		}
	}

	public void removeDepot(Depot depot){
		this.depots.remove(depot);
		if(depot.getDryings().contains(this)){
			depot.removeDrying(this);
		}
	}

	public String toString(){
		return "Toerring";
	}
	
}
