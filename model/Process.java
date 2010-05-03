package model;

public abstract class Process {
    private int ProcessStep;
    private ProcessLine processLine;

    public void Process(int processStep, ProcessLine processLine){
    }

    public int getProcessStep(){
        return 0;
    }

    public void setProcessStep(int preocessStep){
    }

    public ProcessLine getProcessLine(){
        return null;
    }

}
