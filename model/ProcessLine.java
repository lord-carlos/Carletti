package model;

import java.util.ArrayList;

public class ProcessLine {
	private String name;
	private String description;
	private ProductType productType;
	private ArrayList<Process> processes;

	public ProcessLine(String name, String description, ProductType productType) throws RuntimeException{
		if ( productType==null){
			throw new RuntimeException("produktType can't be null");
		} else {
			this.setName(name);
			this.setDescription(description);
			this.productType=productType;
			productType.setProcessLine(this);
		}
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

	public ProductType getProductType(){
		return this.productType;
	}

	public ArrayList<Process> getProcesses(){
		return this.getProcesses();
	}

	public void createSubProcess(int processStep, String name, String desciption, long treatmentTime, double temperature){
		this.processes.add(new SubProcess(name, desciption, treatmentTime, temperature, processStep, this));
	}

	public void createDrying(int processStep, long minTime, long idealTime, long maxTime) throws RuntimeException{
		this.processes.add(new Drying(minTime, idealTime, maxTime, processStep, this));
	}

	public void deleteProcess(Process process){
		this.processes.remove(process);
	}

}
