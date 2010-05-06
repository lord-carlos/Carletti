package model;

import java.util.ArrayList;

public class Depot {
	private String name;
	private String description;
	private ArrayList<StoringSpace> storingspaces = new ArrayList<StoringSpace>();
	private ArrayList<Drying> dryings = new ArrayList<Drying>();

	public Depot(String name, String desciption){
		this.setName(name);
		this.setDescription(desciption);
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

	public ArrayList<StoringSpace> getStoringSpaces(){
		return this.storingspaces;
	}

	public StoringSpace createStoringSpace(int positionX, int positionY) throws RuntimeException{
		StoringSpace ss =new StoringSpace(positionX,positionY,this);
		this.storingspaces.add(ss);
		return ss;
	}

	public void deleteStoringSpace(StoringSpace storingSpace){
		this.storingspaces.remove(storingSpace);
	}

	public ArrayList<Drying> getDryings(){
		return this.dryings;
	}

	public void addDrying(Drying drying){
		this.dryings.add(drying);
		if(!drying.getDepots().contains(this)){
			drying.addDepot(this);
		}
	}

	public void removeDrying(Drying drying){
		this.dryings.remove(drying);
		if(drying.getDepots().contains(this)){
			drying.removeDepot(this);
		}
	}
	
	public String toString(){
		return name;
	}

}
