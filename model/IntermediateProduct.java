package model;

import java.util.ArrayList;

public class IntermediateProduct {
	private boolean finished = false;
	private String id;
	private double quantity;
	private ProductType productType;
	private ArrayList<ProcessLog> processLogs  = new ArrayList<ProcessLog>();
	private StoringSpace storingSpace;

	public IntermediateProduct(String id, ProductType productType, double quantity) throws RuntimeException{
		this.setId(id);
		this.setProductType(productType);
		this.setQuantity(quantity);
	}

	public boolean isFinished(){
		return this.finished;
	}

	public void setFinished(){
		this.finished=true;
	}

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id=id;
	}

	public double getQuantity(){
		return this.quantity;
	}

	public void setQuantity(double quantity) throws RuntimeException{
		if (quantity<0){
			throw new RuntimeException("quantity can't be a negative number");
		} else {
			this.quantity=quantity;
		}
	}

	public ProductType getProductType(){
		return this.productType;
	}

	public void setProductType(ProductType productType) throws RuntimeException{
		if (productType==null){
			throw new RuntimeException("productType can't be null");
		} else {
			if (this.productType != null){
				this.productType.removeIntermediateProduct(this);
			}
			this.productType=productType;
			if (!productType.getIntermediateProducts().contains(this)){
				productType.addIntermediateProduct(this);
			}
		}
	}

	public ArrayList<ProcessLog> getProcessLogs(){
		return this.processLogs;
	}

	public ProcessLog createProcessLog(Process process, StoringSpace storingSpace){
		ProcessLog p =new ProcessLog(process,storingSpace,this);
		this.processLogs.add(p);
		return p;
	}

	public void deleteProcessLog(ProcessLog processLog){
		this.processLogs.remove(processLog);
	}

	public StoringSpace getStoringSpace(){
		return this.storingSpace;
	}

	public void setStoringSpace(StoringSpace storingSpace){
		if (this.storingSpace != null){
			this.storingSpace.unsetIntermediateProduct();
		}
		this.storingSpace=storingSpace;
		if (storingSpace.getIntermediateProduct()!=this){
			storingSpace.setIntermediateProduct(this);
		}
	}

	public void unsetStoringSpace(){
		StoringSpace oldStoringSpace = this.storingSpace;
		this.storingSpace = null;
		if (oldStoringSpace.getIntermediateProduct()!=null){
			oldStoringSpace.unsetIntermediateProduct();
		}
	}
}
