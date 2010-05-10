package model;

import java.sql.Date;

public class ProcessLog{
	private Date startTime;
	private Date endTime;
	private Process process;
	private StoringSpace storingSpace;
	private IntermediateProduct intermediateProduct;

	public ProcessLog(Process process, StoringSpace storingSpace, IntermediateProduct intermediateProduct) throws RuntimeException{
		if (intermediateProduct==null){
			throw new RuntimeException("intermediateProduct can't be set to null");
		} else {
			this.setProcess(process);
			if (storingSpace==null){
				this.storingSpace=null;
			} else {
				this.setStoringSpace(storingSpace);
			}
			this.intermediateProduct=intermediateProduct;
			this.startTime = new Date(System.currentTimeMillis());
		}
	}

	public Date getStartTime(){
		return this.startTime;
	}

	public Date getEndTime(){
		return this.endTime;
	}

	public void endProcess(){
		this.endTime = new Date(System.currentTimeMillis());
	}

	public boolean isActive(){
		return this.endTime==null;
	}

	public Process getProcess(){
		return this.process;
	}

	public void setProcess(Process process) throws RuntimeException{
		if (process==null){
			throw new RuntimeException("process can't be null");
		} else {
			if (this.process != null){
				this.process.removeProcessLog(this);
			}
			this.process=process;
			if (!process.getProcessLogs().contains(this)){
				process.addProcessLog(this);
			}
		}
	}

	public StoringSpace getStoringSpace(){
		return this.storingSpace;
	}

	public void setStoringSpace(StoringSpace storingSpace){
		if (this.storingSpace != null){
			this.storingSpace.removeProcessLog(this);
		}
		this.storingSpace=storingSpace;
		if (!storingSpace.getProcessLogs().contains(this)){
			storingSpace.addProcessLog(this);
		}
	}

	public void unsetStoringSpace(){
		StoringSpace oldStoringSpace = this.storingSpace;
		this.storingSpace = null;
		if (oldStoringSpace.getProcessLogs().contains(this)){
			oldStoringSpace.removeProcessLog(this);
		}
	}

	public IntermediateProduct getIntermediateProduct(){
		return this.intermediateProduct;
	}

}
