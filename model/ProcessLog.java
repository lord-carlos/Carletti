package model;

public class ProcessLog extends Comparable {
    private Date startTime;
    private Date endTime;
    private Process process;
    private StoringSpace storingSpace;
    private IntermediateProduct intermediateProduct;

    public void ProcessLog(Process process, StoringSpace storingSpace, IntermediateProduct intermediateProduct){
    }

    public Date getStartTime(){
        return null;
    }

    public Date getEndTime(){
        return null;
    }

    public void endProcess(){
    }

    public void isActive(){
    }

    public Process getProcess(){
        return null;
    }

    public void setProcess(Process process){
    }

    public StoringSpace getStoringSpace(){
        return null;
    }

    public void setStoringSpace(StoringSpace storingSpace){
    }

    public IntermediateProduct getIntermediateProduct(){
        return null;
    }

}
