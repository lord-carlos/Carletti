package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import model.*;
import model.Process;

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
	
	public Depot createDepot(String name, String description,int maxX, int maxY) {
		Depot depot = new Depot(name, description,maxX,maxY);
		dao.store(depot);
		return depot;
	}
	
	public void deleteDepot(Depot depot) {
		dao.delete(depot);
	}
	
	//IntermediateProduct
	public List<IntermediateProduct> getAllIntermediateProducts() {
		return dao.getAllIntermediateProducts();
	}
	
	public IntermediateProduct createIntermediateProduct(String id, ProductType productType, double quantity) {
		IntermediateProduct intermediateProduct = new IntermediateProduct(id, productType, quantity);
		dao.store(intermediateProduct);
		return intermediateProduct;
	}
	
	public void deleteIntermediateProduct(IntermediateProduct intermediateProduct) {
		dao.delete(intermediateProduct);
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
	
	public void createCalletiDepots() {
		Depot dp1 = createDepot("depot1","depot som ligger langt mellemgangen",5,8);
		Depot dp2 = createDepot("depot2","depot som ligger i hj�rnet",3,5);
		
		
		
		
		
	}
	
	public void createTestData() {

		Depot depot1 = createDepot("depot1","depot som ligger langt mellemgangen",5,8);
		ProductType productType1 = createProductType("Tomater");
		ArrayList<IntermediateProduct> temporaryProducts = new ArrayList<IntermediateProduct>();
		for (int i = 0; i < 8; i++) {
			temporaryProducts.add(createIntermediateProduct(Integer.toString(i), productType1, 120));
		}
		
		ArrayList<StoringSpace> storingSpaces = new ArrayList<StoringSpace>();
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 8; y++) {
				storingSpaces.add(new StoringSpace(x,y,depot1));
			}
		}
		for (int i = 0; i < 8; i++) {
			temporaryProducts.get(i).setStoringSpace(storingSpaces.get(i));
		}
		
		System.out.println("Så begynder listen");
		for (int i = 0; i < depot1.getStoringSpaces().size(); i++) {
			System.out.println(depot1.getStoringSpaces().get(i)+" - "+depot1.getStoringSpaces().get(i).getIntermediateProduct());
		}
		
		Depot depot2 = createDepot("depot2","lager til lort",3,5);
	}

}