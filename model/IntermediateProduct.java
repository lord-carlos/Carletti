package model;

import java.util.ArrayList;

public class IntermediateProduct {
	private boolean finished = false;
	private boolean discarded = false;
	private String id;
	private double quantity;
	private ProductType productType;
	private ArrayList<ProcessLog> processLogs  = new ArrayList<ProcessLog>();
	private StoringSpace storingSpace = null;

	/**
	 * Creates an Intermediate product
	 * @param id - the id of the product
	 * @param productType - type of product
	 * @param quantity - how much of the product there is
	 * @throws RuntimeException
	 */
	public IntermediateProduct(String id, ProductType productType, double quantity) throws RuntimeException{
		this.setId(id);
		this.setProductType(productType);
		this.setQuantity(quantity);
	}

	/**
	 * Tels if the product hav been throught al its processes
	 * @return Boolean
	 */
	public boolean isFinished(){
		return this.finished;
	}

	/**
	 * Tels if the product af been discarded
	 * @return
	 */
	public boolean isDiscarded(){
		return this.discarded;
	}

	/**
	 * Product id
	 * @return String
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * Sets an nes id for the product
	 * @param id
	 */
	public void setId(String id){
		this.id=id;
	}

	/**
	 * Gets how mutch of the product there is
	 * @return double
	 */
	public double getQuantity(){
		return this.quantity;
	}

	/**
	 * Tells the quantity of the product
	 * @param quantity
	 * @throws RuntimeException - throw an exeption if quantity is smaller then 0
	 */
	public void setQuantity(double quantity) throws RuntimeException{
		if (quantity<0){
			throw new RuntimeException("quantity can't be a negative number");
		} else {
			this.quantity=quantity;
		}
	}

	/**
	 * Information about the type of product
	 * @return ProductType
	 */
	public ProductType getProductType(){
		return this.productType;
	}

	/**
	 * sets the type of product
	 * @param productType
	 * @throws RuntimeException throws an exeption if productType is null
	 */
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

	/**
	 * Gets the processes that the intermediate product have been throught
	 * @return ArrayList<ProcessLog>
	 */
	public ArrayList<ProcessLog> getProcessLogs(){
		return this.processLogs;
	}

	/**
	 * Creates an ProcessLog.
	 * Warning dont call this method directly, use {@link #sendToNextProcess(StoringSpace)} or {@link #discardThisIntermediateProduct()}
	 * @param process - the process that is started
	 * @param storingSpace - the Storing space where the process is executed
	 * @return ProcessLog
	 * @throws RuntimeException throughts an exeption if process is null
	 */
	public ProcessLog createProcessLog(Process process, StoringSpace storingSpace) throws RuntimeException {
		ProcessLog p =new ProcessLog(process,storingSpace,this);
		this.processLogs.add(p);
		return p;
	}

	/**
	 * Deletes an ProcessLog
	 * Warning dont call this method, it will mess up the data that this class contains
	 * @param processLog
	 */
	public void deleteProcessLog(ProcessLog processLog){
		this.processLogs.remove(processLog);
		if (processLog.getStoringSpace()!=null){
			processLog.unsetStoringSpace();
		}
		processLog.getProcess().removeProcessLog(processLog);
	}

	/**
	 * returns the storingspace where this IntermediateProduct is
	 * @return
	 */
	public StoringSpace getStoringSpace(){
		return this.storingSpace;
	}

	/**
	 * Sets the storingSpace where this product is.
	 * Warning dont call this method directly, use {@link #sendToNextProcess(StoringSpace)} or {@link #discardThisIntermediateProduct()}
	 * @param storingSpace
	 */
	public void setStoringSpace(StoringSpace storingSpace){
		if (this.storingSpace != null){
			this.storingSpace.unsetIntermediateProduct();
		}
		this.storingSpace=storingSpace;
		if (storingSpace.getIntermediateProduct()!=this){
			storingSpace.setIntermediateProduct(this);
		}
	}

	/**
	 * remove this IntermediateProduct from its StoringSpace.
	 * Warning dont call this method directly, use {@link #sendToNextProcess(StoringSpace)} or {@link #discardThisIntermediateProduct()}
	 */
	public void unsetStoringSpace(){
		StoringSpace oldStoringSpace = this.storingSpace;
		this.storingSpace = null;
		if (oldStoringSpace.getIntermediateProduct()!=null){
			oldStoringSpace.unsetIntermediateProduct();
		}
	}

	/**
	 * IntermediateProduct description
	 */
	public String toString() {
		return id+" "+productType.getName();
	}

	/**
	 * Method used to start, send to, and finish the processes the IntermediateProduct have to go throught
	 * This method automaticly sts and unsets the storingspace og this product
	 * @param StoringSpace if the process needs an storingspace set, his atribute, otherwise set it to null
	 */
	public void sendToNextProcess(StoringSpace storingSpace){
		//tests if the IntermediateProduct is finished or discarded
		if (!isDiscarded() && !isFinished()){
			//tests if we are going to start the first process
			if (processLogs.size()==0){

				createProcessLog(this.productType.getProcessLine().getProcesses().get(0), storingSpace);
				if (storingSpace==null){
					if (this.storingSpace!=null){
						unsetStoringSpace();
					}
				} else {
					this.setStoringSpace(storingSpace);
				}

				//test if we are at the last process
			} else if (processLogs.size()>=this.productType.getProcessLine().getProcesses().size()) {
				if (this.storingSpace!=null){
					unsetStoringSpace();
				}
				processLogs.get(processLogs.size()-1).endProcess();
				finished=true;

				//neither first or last process
			} else {
				int i = processLogs.size()-1;
				processLogs.get(i).endProcess();
				createProcessLog(this.productType.getProcessLine().getProcesses().get(i+1), storingSpace);

				if (storingSpace==null){
					if (this.storingSpace!=null){
						unsetStoringSpace();
					}
				} else {
					this.setStoringSpace(storingSpace);
				}

			}
		}
	}

	/**
	 * returns the active ProcessLog
	 * will be null if the IntermediateProduct is finished, discared or that we havent started any processes yet
	 * @return
	 */
	public ProcessLog getActivProcessLog(){

		if (processLogs.size()==0 || isFinished() || isDiscarded()) {
			return null;
		} else {
			return processLogs.get(processLogs.size()-1);
		}
	}

	/**
	 * returns the next process
	 *  will be null if the IntermediateProduct is finished, discared or if the active process is the last
	 * @return
	 */
	public Process getNextProcess(){
		if (processLogs.size()>=this.productType.getProcessLine().getProcesses().size() || isFinished() || isDiscarded()) {
			return null;
		} else {
			return productType.getProcessLine().getProcesses().get(processLogs.size());
		}

	}

	/**
	 * Discards this IntermediateProdict
	 */
	public void discardThisIntermediateProduct(){
		if (!isDiscarded() && !isFinished()){
			discarded = true;
			if (processLogs.size()!=0){
				int i = processLogs.size()-1;
				processLogs.get(i).endProcess();
			}
			if (this.storingSpace!=null){
				unsetStoringSpace();
			}
		}
	}
}
