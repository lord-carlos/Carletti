package model;

public class IntermediateProduct {
    private boolean finished;
    private String id;
    private double quantity;
    private ProduktType produktType;
    private ArrayList<ProcessLog> processLogs;

    public void IntermediateProduct(String id, ProduktType produktType, double quantity){
    }

    public boolean isFinished(){
        return false;
    }

    public String getId(){
        return null;
    }

    public void setId(String id){
    }

    public double getQuantity(){
        return 0;
    }

    public void setQuantity(double quantity){
    }

    public ProduktType getProduktType(){
        return null;
    }

    public void setProduktType(ProduktType produktType){
    }

    public ArrayList<ProcessLog> getProcessLogs(){
        return null;
    }

    public void createProcessLog(Process process, StoringSpace storingSpace){
    }

    public void deleteProcessLog(ProcessLog processLog){
    }

}
