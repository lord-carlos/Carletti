package model;

import java.util.ArrayList;

public class Depot {
	private String name;
	private String description;
	private int maxX;
	private int maxY;
	private ArrayList<StoringSpace> storingspaces = new ArrayList<StoringSpace>();
	private ArrayList<Drying> dryings = new ArrayList<Drying>();

	public Depot(String name, String desciption, int maxX, int maxY) throws RuntimeException{
		
		if (maxY<=0 || maxX<=0){
			throw new RuntimeException("maxY and maxX can't be a negatic number");
		} else {
		this.maxX=maxX;
		this.maxY=maxY;
		
		for (int x = 1; x <=maxX; x++) {
			for (int y = 1; y <=maxY; y++) {
				StoringSpace ss =new StoringSpace(x,y,this);
				this.storingspaces.add(ss);
			}
		}
		
		this.setName(name);
		this.setDescription(desciption);
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

	public ArrayList<StoringSpace> getStoringSpaces(){
		return this.storingspaces;
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
	
	public int getMaxX(){
		return this.maxX;
	}
	
	public int getMaxY(){
		return this.maxY;
	}
	
	public String toString(){
		return name;
	}

}
