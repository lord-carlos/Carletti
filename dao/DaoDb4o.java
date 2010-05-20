package dao;


import java.util.List;

import model.*;
import model.Process;

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

	
	public void close(){
		db.close();
	}

}
