package model;

import java.util.ArrayList;

public class Depot {
	private String name;
	private String description;
	private ArrayList<StoringSpace> storingspaces;
	private ArrayList<Drying> dryings;

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

	public void createStoringSpace(int positionX, int positionY) throws RuntimeException{
		if (positionX>=0 && positionY>=0){
			this.storingspaces.add(new StoringSpace(positionX,positionY,this));
		} else {
			throw new RuntimeException("X and Y position can't be a negative number");
		}
	}

	public void deleteStoringSpace(StoringSpace storingSpace){
		this.storingspaces.remove(storingSpace);
	}

	public ArrayList<Drying> getDryings(){
		return this.dryings;
	}

	public void addDrying(Drying drying){
		this.dryings.add(drying);
	}

	public void removeDrying(Drying drying){
		this.dryings.remove(drying);
	}

}
