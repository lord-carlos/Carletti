package dao;

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

/** 
 * @author Brian
 */

public interface Dao {	
	//Depot
	public List<Depot> getAllDepots();
	public void store (Depot depot);
	public void delete(Depot depot);
		
	//IntermediateProduct
	public List<IntermediateProduct> getAllIntermediateProducts(); 
	public void store(IntermediateProduct intermediateProduct);		
	public void delete(IntermediateProduct intermediateProduct); 
		
	//ProductType
	public List<ProductType> getAllProductTypes(); 
	public void store(ProductType productType); 
	public void delete(ProductType productType); 
	
	public void close();		
}
