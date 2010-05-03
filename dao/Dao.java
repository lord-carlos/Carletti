package dao;

import java.util.List;

import model.Depot;
import model.Drying;
import model.IntermediateProduct;
import model.Process;
import model.ProcessLine;
import model.ProcessLog;
import model.StoringSpace;
import model.SubProcess;

public interface Dao {	
	//Depot
	public List<Depot> getAllDepots();
	public void store (Depot depot);
	public void delete(Depot depot);
	
	//Drying
	public List<Drying> getAllDryings();
	public void store(Drying drying);
	public void delete(Drying drying);
		
	//IntermediateProduct
	public List<IntermediateProduct> getAllIntermediateProducts(); 
	public void store(IntermediateProduct interMediateProduct);		
	public void delete(IntermediateProduct interMediateProduct); 
	
	//Process
	public List<Process> getAllProcesses(); 
	public void store(Process process); 
	public void delete(Process process); 
	
	//ProcessLine
	public List<ProcessLine> getAllProcessLines(); 
	public void store(ProcessLine processLine); 
	public void delete(ProcessLine processLine); 
		
	//ProcessLog
	public List<ProcessLog> getAllProcessLogs(); 
	public void store(ProcessLog processLog); 
	public void delete(ProcessLog processLog); 
		
	//ProductType
	public List<ProductType> getAllProductTypes(); 
	public void store(ProductType productType); 
	public void delete(ProductType productType); 
		
	//StoringSpace
	public List<StoringSpace> getAllStoringSpaces(); 	
	public void store(StoringSpace storingSpace); 	
	public void delete(StoringSpace storingSpace); 
		
	//SubProcess
	public List<SubProcess> getAllSubProcesss(); 
	public void store(SubProcess subProcess); 
	public void delete(SubProcess subProcess); 
	
	public void close();		
}
