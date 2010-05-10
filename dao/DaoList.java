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
	private List<IntermediateProduct> intermediateProducts;
	private List<Process> processes;
	private List<ProcessLine> processLines;
	private List<ProductType> productTypes;
	
	private static DaoList dao = null;
	
	private DaoList() {
		depots = new ArrayList<Depot>();
		intermediateProducts = new ArrayList<IntermediateProduct>();
		processes = new ArrayList<Process>();
		processLines = new ArrayList<ProcessLine>();
		productTypes = new ArrayList<ProductType>();
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

	@Override
	public void close() {
		// do nothing here
	}
	

}
