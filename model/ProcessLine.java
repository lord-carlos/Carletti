package model;

import java.util.ArrayList;

public class ProcessLine {
	private String name;
	private String description;
	private ProductType productType;
	private ArrayList<Process> processes  = new ArrayList<Process>();

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
		return this.processes;
	}

	public SubProcess createSubProcess(int processStep, String name, String desciption, long treatmentTime, double temperature){
		SubProcess sp = new SubProcess(name, desciption, treatmentTime, temperature, processStep, this);	

		this.processes.add(sp);
		

		return sp;

	}

	public Drying createDrying(int processStep, long minTime, long idealTime, long maxTime) throws RuntimeException{
		Drying d = new Drying(minTime, idealTime, maxTime, processStep, this);
		
		this.processes.add(d);
		
		return d;
	}

	public void deleteProcess(Process process){
		this.processes.remove(process);
	}

}
