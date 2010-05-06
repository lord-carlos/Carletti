package model;

public class SubProcess extends model.Process {
	private String name;
	private String description;
	private long treatmentTime;
	private double temperature;

	public SubProcess(String name, String description, long treatmentTime, double temperature,int processStep,ProcessLine processLine) throws RuntimeException{
		super(processStep, processLine);
		this.setName(name);
		this.setDescription(description);
		this.setTreatmentTime(treatmentTime);
		this.setTemperature(temperature);
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public long getTreatmentTime(){
		return this.treatmentTime;
	}

	public void setTreatmentTime(long treatmentTime) throws RuntimeException{
		if (treatmentTime<0){
			throw new RuntimeException("treatmentTime can't be a negativ number");
		} else {
			this.treatmentTime=treatmentTime;
		}
	}

	public double getTemperature(){
		return this.temperature;
	}

	public void setTemperature(double temperature){
		this.temperature=temperature;
	}
	
	public String toString(){
		return this.name;
	}

}
