package dao;

import java.util.ArrayList;
import java.util.List;

import model.Depot;
import model.Drying;
import model.IntermediateProduct;
import model.Process;
import model.ProcessLine;
import model.ProcessLog;
import model.ProductType;
import model.StoringSpace;
import model.SubProcess;


public class DaoList implements Dao {
	private List<Depot> depots;
	private List<Drying> dryings;
	private List<IntermediateProduct> intermediateProducts;
	private List<Process> processes;
	private List<ProcessLine> processLines;
	private List<ProcessLog> processLogs;
	private List<ProductType> productTypes;
	private List<StoringSpace> storingSpaces;
	private List<SubProcess> subProcesses;
	
	private static DaoList dao = null;
	
	private DaoList() {
		depots = new ArrayList<Depot>();
		dryings = new ArrayList<Drying>();
		intermediateProducts = new ArrayList<IntermediateProduct>();
		processes = new ArrayList<Process>();
		processLines = new ArrayList<ProcessLine>();
		processLogs = new ArrayList<ProcessLog>();
		productTypes = new ArrayList<ProductType>();
		storingSpaces = new ArrayList<StoringSpace>();
		subProcesses = new ArrayList<SubProcess>();
	}
	
	public static Dao getDao() {
		if (dao == null)
			dao = new DaoList();
		return dao;
	}
	
	//Depot
	public List<Depot> getAllDepots() {
		return depots;
	}
	
	public void store(Depot depot) {
		if (!depots.contains(depot))
			depots.add(depot);
	}
	
	public void delete(Depot depot) {
		depots.remove(depot);
	}
	
	//Drying
	public List<Drying> getAllDryings() {
		return dryings;
	}
	
	public void store(Drying drying) {
		if (!dryings.contains(drying))
			dryings.add(drying);
	}
	
	public void delete(Drying drying) {
		dryings.remove(drying);
	}
	
	//IntermediateProduct
	public List<IntermediateProduct> getAllIntermediateProducts() {
		return intermediateProducts;
	}
	
	public void store(IntermediateProduct interMediateProduct) {
		if (!intermediateProducts.contains(interMediateProduct))
			intermediateProducts.add(interMediateProduct);
	}
	
	public void delete(IntermediateProduct interMediateProduct) {
		intermediateProducts.remove(interMediateProduct);
	}
	
	//Process
	public List<Process> getAllProcesses() {
		return processes;
	}
	
	public void store(Process process) {
		if (!processes.contains(process))
			processes.add(process);
	}
	
	public void delete(Process process) {
		processes.remove(process);
	}
	
	//ProcessLine
	public List<ProcessLine> getAllProcessLines() {
		return processLines;
	}
	
	public void store(ProcessLine processLine) {
		if (!processLines.contains(processLine))
			processLines.add(processLine);
	}
	
	public void delete(ProcessLine processLine) {
		processLines.remove(processLine);
	}
	
	//ProcessLog
	public List<ProcessLog> getAllProcessLogs() {
		return processLogs;
	}
	
	public void store(ProcessLog processLog) {
		if (!processLogs.contains(processLog))
			processLogs.add(processLog);
	}
	
	public void delete(ProcessLog processLog) {
		processLogs.remove(processLog);
	}
	
	//ProductType
	public List<ProductType> getAllProductTypes() {
		return productTypes;
	}
	
	public void store(ProductType productType) {
		if (!productTypes.contains(productType))
			productTypes.add(productType);
	}
	
	public void delete(ProductType productType) {
		productTypes.remove(productType);
	}
	
	//StoringSpace
	public List<StoringSpace> getAllStoringSpaces() {
		return storingSpaces;
	}
	
	public void store(StoringSpace storingSpace) {
		if (!storingSpaces.contains(storingSpace))
			storingSpaces.add(storingSpace);
	}
	
	public void delete(StoringSpace storingSpace) {
		storingSpaces.remove(storingSpace);
	}
	
	//SubProcess
	public List<SubProcess> getAllSubProcesss() {
		return subProcesses;
	}
	
	public void store(SubProcess subProcess) {
		if (!subProcesses.contains(subProcess))
			subProcesses.add(subProcess);
	}
	
	public void delete(SubProcess subProcess) {
		subProcesses.remove(subProcess);
	}
	
	public void close() {
		// do nothing here
	}

}
