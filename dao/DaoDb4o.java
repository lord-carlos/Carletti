package dao;


import java.util.List;

import model.Depot;
import model.Drying;
import model.IntermediateProduct;
import model.ProcessLine;
import model.ProcessLog;
import model.StoringSpace;
import model.SubProcess;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

/**
 * @author Brian
 */
public class DaoDb4o implements Dao {
	
	private ObjectContainer db;
	private static DaoDb4o dao = null;

	private DaoDb4o(){
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		configuration.common().activationDepth(6);
		configuration.common().updateDepth(6);
	
		db = Db4oEmbedded.openFile(configuration,"db.db4o");
		
	}
	
	public static Dao getDao(){
		if (dao == null)
			dao = new DaoDb4o();
		return dao;
	}

	//Depot
	public List<Depot> getAllDepots() {
		return db.query(Depot.class);
	}

	public void store(Depot depot) {
		db.store(depot);
		db.commit();
	}

	public void delete(Depot depot) {
		db.delete(depot);
		db.commit();
	}
	
	//Drying
	public List<Drying> getAllDryings() {
		return db.query(Drying.class);
	}

	public void store(Drying drying) {
		db.store(drying);
		db.commit();
	}

	public void delete(Drying drying) {
		db.delete(drying);
		db.commit();
	}
	
	//IntermediateProduct
	public List<IntermediateProduct> getAllIntermediateProducts() {
		return db.query(IntermediateProduct.class);
	}

	public void store(IntermediateProduct intermediateProduct) {
		db.store(intermediateProduct);
		db.commit();
	}

	public void delete(IntermediateProduct intermediateProduct) {
		db.delete(intermediateProduct);
		db.commit();
	}
	
	//Process
	public List<Process> getAllProcesses() {
		return db.query(Process.class);
	}

	public void store(Process process) {
		db.store(process);
		db.commit();
	}

	public void delete(Process process) {
		db.delete(process);
		db.commit();
	}
	
	//ProcessLine
	public List<ProcessLine> getAllProcessLines() {
		return db.query(ProcessLine.class);
	}

	public void store(ProcessLine processLine) {
		db.store(processLine);
		db.commit();
	}

	public void delete(ProcessLine processLine) {
		db.delete(processLine);
		db.commit();
	}
	
	//ProcessLog
	public List<ProcessLog> getAllProcessLogs() {
		return db.query(ProcessLog.class);
	}

	public void store(ProcessLog processLog) {
		db.store(processLog);
		db.commit();
	}

	public void delete(ProcessLog processLog) {
		db.delete(processLog);
		db.commit();
	}
	
	//ProductType
	public List<ProductType> getAllProductTypes() {
		return db.query(ProductType.class);
	}

	public void store(ProductType productType) {
		db.store(productType);
		db.commit();
	}

	public void delete(ProductType productType) {
		db.delete(productType);
		db.commit();
	}
	
	//StoringSpace
	public List<StoringSpace> getAllStoringSpaces() {
		return db.query(StoringSpace.class);
	}

	public void store(StoringSpace storingSpace) {
		db.store(storingSpace);
		db.commit();
	}

	public void delete(StoringSpace storingSpace) {
		db.delete(storingSpace);
		db.commit();
	}
	
	//SubProcess
	public List<SubProcess> getAllSubProcesss() {
		return db.query(SubProcess.class);
	}

	public void store(SubProcess subProcess) {
		db.store(subProcess);
		db.commit();
	}

	public void delete(SubProcess subProcess) {
		db.delete(subProcess);
		db.commit();
	}
	
	public void close(){
		db.close();
	}

}
