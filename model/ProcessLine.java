package model;

public class ProcessLine {
    private String name;
    private String description;
    private ProduktType produktType;
    private ArrayList<Process> processes;

    public void ProcessLine(String name, String description, ProduktType produktType){
    }

    public String getName(){
        return null;
    }

    public void setName(String name){
    }

    public String getDescription(){
        return null;
    }

    public void setDescription(String description){
    }

    public ProduktType getProduktType(){
        return null;
    }

    public void setProduktType(ProduktType produktType){
    }

    public ArrayList<Process> getProcesses(){
        return null;
    }

    public void createSubProcess(int processStep, String name, String desciption, long treatmentTime, double temperature){
    }

    public void createDrying(int preocessStep, long minTime, long idealTime, long maxTime){
    }

    public void deleteProcess(Process process){
    }

}
