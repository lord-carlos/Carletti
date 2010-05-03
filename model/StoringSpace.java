package model;

import java.util.ArrayList;

public class StoringSpace {
	private int positionX;
	private int positionY;
	private IntermediateProduct intermediateProduct;
	private Depot depot;
	private ArrayList<ProcessLog> processLogs;

	public StoringSpace(int positionX, int positionY, Depot depot) throws RuntimeException{
		if (depot==null){
			throw new RuntimeException("depot can't be null");
		} else {
			this.setPositionX(positionX);
			this.setPositionY(positionY);
			this.depot=depot;
		}
	}

	public int getPositionX(){
		return this.positionX;
	}

	public void setPositionX(int positionX){
		if (positionX<0){
			throw new RuntimeException("positionX can't be a negativ number");
		} else {
			this.positionX=positionX;
		}
	}

	public int getPositionY(){
		return this.positionY;
	}

	public void setPositionY(int positionY){
		if (positionY<0){
			throw new RuntimeException("positionY can't be a negativ number");
		} else {
			this.positionY=positionY;
		}
	}

	public IntermediateProduct getIntermediateProduct(){
		return this.intermediateProduct;
	}

	public void setIntermediateProduct(IntermediateProduct intermediateProduct){
		if (this.intermediateProduct != null){
			this.intermediateProduct.unsetStoringSpace();
		}
		this.intermediateProduct=intermediateProduct;
		if (intermediateProduct.getStoringSpace()!=this){
			intermediateProduct.setStoringSpace(this);
		}
	}
	public void unsetIntermediateProduct(){
		IntermediateProduct oldIntermediateProduct = this.intermediateProduct;
		this.intermediateProduct = null;
		if (oldIntermediateProduct.getStoringSpace()!=null){
			oldIntermediateProduct.unsetStoringSpace();
		}
    }

	public Depot getDepot(){
		return this.depot;
	}

	public ArrayList<ProcessLog> getProcessLogs(){
		return this.processLogs;
	}

	public void addProcessLog(ProcessLog processLog){
		this.processLogs.add(processLog);
		if (processLog.getStoringSpace() != this){
			processLog.setStoringSpace(this);
		}
	}

	public void removeProcessLog(ProcessLog preocessLog){
		this.processLogs.remove(preocessLog);
		if (preocessLog.getStoringSpace() != null){
		preocessLog.unsetStoringSpace();
		}
	}

}
