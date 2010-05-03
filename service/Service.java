package service;

import java.util.List;

import model.Depot;
import model.Drying;
import model.IntermediateProduct;
import model.ProcessLine;
import model.ProcessLog;
import model.StoringSpace;
import model.SubProcess;

import dao.Dao;
import dao.DaoList;

public class Service {
	private static Service service = null;
	private Dao dao = null;
	
	private Service() {
		dao = DaoList.getDao();
	}
	
	public static Service getService() {
		if (service == null) {
			service = new Service();
		}
		return service;
	}

	//Depot
	public List<Depot> getAllDepots() {
		return dao.getAllDepots();
	}
	
	public Depot createDepot(String name, String description) {
		Depot depot = new Depot(name, description);
		dao.store(depot);
		return depot;
	}
	
	public void deleteDepot(Depot depot) {
		dao.delete(depot);
	}

	//Drying
	public List<Drying> getAllDryings() {
		return dao.getAllDryings();
	}
	
	public Drying createDrying(long minTime, long idealTime, long maxTime, int processStep, ProcessLine processLine) {
		Drying drying = new Drying(minTime, idealTime, maxTime, processStep, processLine);
		dao.store(drying);
		return drying;
	}
	
	public void deleteDrying(Drying drying) {
		dao.delete(drying);
	}
	
	//IntermediateProduct
	public List<IntermediateProduct> getAllIntermediateProducts() {
		return dao.getAllIntermediateProducts();
	}
	
	public Drying createIntermediateProduct(String id, ProductType productType double quantity) {
		IntermediateProduct intermediateProduct = new IntermediateProduct(id, productType, quantity);
		dao.store(intermediateProduct);
		return intermediateProduct;
	}
	
	public void deleteIntermediateProduct(IntermediateProduct intermediateProduct) {
		dao.delete(intermediateProduct);
	}

	//Process
	public List<Process> getAllProcesses() {
		return dao.getAllProcesses();
	}
	
	public Drying createProcess(int processStep, ProcessLine processLine) {
		Process process = new Process(processStep, processLine);
		dao.store(process);
		return process;
	}
	
	public void deleteProcess(Process process) {
		dao.delete(process);
	}

	//ProcessLine
	public List<ProcessLine> getAllProcessLines() {
		return dao.getAllProcessLines();
	}
	
	public ProcessLine createProcessLine(String name, String description, ProductType productType) {
		ProcessLine processLine = new ProcessLine(name, description, productType);
		dao.store(processLine);
		return processLine;
	}
	
	public void deleteProcessLine(ProcessLine processLine) {
		dao.delete(processLine);
	}
	
	//ProcessLog
	public List<ProcessLog> getAllProcessLogs() {
		return dao.getAllProcessLogs();
	}
	
	public ProcessLog createProcessLog(Process process, StoringSpace storingSpace, IntermediateProduct intermediateProduct) {
		ProcessLog processLog = new ProcessLog(process, storingSpace, intermediateProduct);
		dao.store(processLog);
		return processLog;
	}
	
	public void deleteProcessLog(ProcessLog processLog) {
		dao.delete(processLog);
	}
	
	//ProductType
	public List<ProductType> getAllProductTypes() {
		return dao.getAllProductTypes();
	}
	
	public ProductType createProductType(String name) {
		ProductType productType = new ProductType(name);
		dao.store(productType);
		return productType;
	}
	
	public void deleteProductType(ProductType productType) {
		dao.delete(productType);
	}

	//StoringSpace
	public List<StoringSpace> getAllStoringSpaces() {
		return dao.getAllStoringSpaces();
	}
	
	public StoringSpace createStoringSpace(int positionX, int positionY, Depot depot) {
		StoringSpace storingSpace = new StoringSpace(positionX, positionY, depot);
		dao.store(storingSpace);
		return storingSpace;
	}
	
	public void deleteStoringSpace(StoringSpace storingSpace) {
		dao.delete(storingSpace);
	}
	
	//SubProcess
	public List<SubProcess> getAllSubProcesses() {
		return dao.getAllSubProcesses();
	}
	
	public SubProcess createSubProcess(String name, String description, long treatmentTime, double temperature) {
		SubProcess subProcess = new SubProcess(name, description, treatmentTime, temperature);
		dao.store(subProcess);
		return subProcess;
	}
	
	public void deleteSubProcess(SubProcess subProcess) {
		dao.delete(subProcess);
	}
}
