package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

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

		Depot depot1 = createDepot("Lager 1","Hovedlageret",5,8);
		Depot depot2 = createDepot("Lager 2","Lager til lort",4,5);
		
		ProductType pteSkumbananer = createProductType("Skumbananer");
		pteSkumbananer.setPicture(new ImageIcon("gui/icons/skumbananer.jpg"));
		
		ProductType pteChokoKaramelLys = createProductType("Choko Karamel Lys");
		pteChokoKaramelLys.setPicture(new ImageIcon("gui/icons/choko karamel lys.jpg"));
		
		ProductType pteChokoKaramelMoerk = createProductType("Choko Karamel Moerk");
		pteChokoKaramelMoerk.setPicture(new ImageIcon("gui/icons/choko karamel moerk.jpg"));
		
		ProductType pteChokoladelinser = createProductType("Chokoladelinser");
		pteChokoladelinser.setPicture(new ImageIcon("gui/icons/chokoladelinser.jpg"));
		
		ProductType pteCitronDrage = createProductType("Citron Dragé");
		pteCitronDrage.setPicture(new ImageIcon("gui/icons/citron dragé.jpg"));
		
		// Carls mess below
		ProcessLine processLine0 = createProcessLine("lakrits", "tilfoejer lakritz", pteChokoladelinser);
		processLine0.createSubProcess(0, "den foerste", "her sker noget", 440, 60);
		processLine0.createSubProcess(1, "den naeste", "her sker ikke noget :D", 200, 60);
		// end of Carls mess
		
		
		createIntermediateProduct("011", pteSkumbananer, 80);
		createIntermediateProduct("012", pteCitronDrage, 80);
		createIntermediateProduct("013", pteChokoladelinser, 100);
		createIntermediateProduct("014", pteChokoKaramelMoerk, 100);
		createIntermediateProduct("015", pteChokoKaramelLys, 140);
		
		createIntermediateProduct("021", pteSkumbananer, 80);
		createIntermediateProduct("022", pteCitronDrage, 80);
		createIntermediateProduct("023", pteChokoladelinser, 100);
		createIntermediateProduct("024", pteChokoKaramelMoerk, 100);
		createIntermediateProduct("025", pteChokoKaramelLys, 140);
		
		
		for (int i = 0; i < 5; i++) {
			depot1.getStoringSpaces().get(i).setIntermediateProduct(Service.getService().getAllIntermediateProducts().get(i));
		}
		
		for (int i = 5; i < 10; i++) {
			depot2.getStoringSpaces().get(i-5).setIntermediateProduct(Service.getService().getAllIntermediateProducts().get(i));
		}

	}
	
	public void getFinishedIntermediateProducts() {
		ArrayList<IntermediateProduct> result = new ArrayList<IntermediateProduct>();
		
		for (IntermediateProduct intermediateProduct : Service.getService().getAllIntermediateProducts()) {
			if(intermediateProduct.getStoringSpace() == null){
				System.out.println("intermediaProdukt without storingspace: "+ intermediateProduct);
//				intermediateProduct.
				System.out.println(intermediateProduct.getProductType().getProcessLine().getProcesses());
			}
		}
		
//		return result;
		
	}

}
