package model;

public abstract class Process {
	private int ProcessStep;
	private ProcessLine processLine;

	public Process(int processStep, ProcessLine processLine) throws RuntimeException{
		if (processLine==null){
			throw new RuntimeException("processLine can't be set to null");
		} else {
			this.setProcessStep(processStep);
			this.processLine=processLine;
		}
	}

	public int getProcessStep(){
		return this.ProcessStep;
	}

	public void setProcessStep(int processStep)throws RuntimeException{
		if (processStep<0) {
			throw new RuntimeException("processStep can't be a negative number");
		} else {
			this.ProcessStep=processStep;
		}
	}

	public ProcessLine getProcessLine(){
		return this.processLine;
	}

}
