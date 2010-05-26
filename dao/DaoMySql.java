package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Depot;
import model.IntermediateProduct;
import model.ProductType;
import model.StoringSpace;

public class DaoMySql implements Dao{

	private static Connection connection;
	private static DaoMySql dao = null;

	private DaoMySql() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://81.89.108.201:2525/carletti", "root", "MortenErSej!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			if (!connection.isValid(10000)) {
				connection = DriverManager.getConnection("jdbc:mysql://81.89.108.201:2525/carletti", "root", "MortenErSej!");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return connection;
	}

	public static Dao getDao(){
		if (dao == null)
			dao = new DaoMySql();
		return dao;
	}
	@Override
	public void close() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Depot depot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(IntermediateProduct interMediateProduct) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ProductType productType) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Depot> getAllDepots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IntermediateProduct> getAllIntermediateProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductType> getAllProductTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(Depot depot) {
		try {
			Statement statement = getConnection().createStatement();

			statement.executeQuery("BEGIN TRANSACTION");
			

			statement.executeQuery("CALL storedepot("+depot.getName()+", "+depot.getDescription()+", "+depot.getMaxX()+", "+depot.getMaxY()+")");
			for (StoringSpace storingSpace : depot.getStoringSpaces()) {
				statement.executeQuery("CALL storestoringspace ("+depot.getName()+", "+storingSpace.getPositionX()+", "+storingSpace.getPositionY()+")");
			}
			statement.executeQuery("COMMIT");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void store(IntermediateProduct intermediateProduct) {
		try {
			Statement statement = getConnection().createStatement();

			statement.executeQuery("BEGIN TRANSACTION");
			statement.executeQuery("CALL storeintermediateproduct("+intermediateProduct.isFinished()+", "+intermediateProduct.isDiscarded()+", "+intermediateProduct.getId()+", "+intermediateProduct.getQuantity()+", "+intermediateProduct.getProductType().getName()+", "+intermediateProduct.getStoringSpace().getDepot().getName()+", "+intermediateProduct.getStoringSpace().getPositionX()+", "+intermediateProduct.getStoringSpace().getPositionY()+")");
			statement.executeQuery("COMMIT");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void store(ProductType productType) {
		// TODO Auto-generated method stub

	}



}
