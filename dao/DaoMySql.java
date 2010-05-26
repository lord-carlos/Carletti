package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Depot;
import model.Drying;
import model.IntermediateProduct;
import model.Process;
import model.ProductType;
import model.StoringSpace;
import model.SubProcess;

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
		ArrayList<Depot> depots = new ArrayList<Depot>();
		try {
			Statement statement = getConnection().createStatement();
			ResultSet resDepots = statement.executeQuery("SELECT * FROM getdepots");
			if(resDepots.next()) {
				depots.add(new Depot(resDepots.getString("name"), resDepots.getString("description"), resDepots.getInt("maxx"), resDepots.getInt("maxy")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return depots;
	}

	@Override
	public List<IntermediateProduct> getAllIntermediateProducts() {
		ArrayList<IntermediateProduct> intermediateProducts = new ArrayList<IntermediateProduct>();
		try {
			Statement statement = getConnection().createStatement();
			ResultSet resIntermediateProducts = statement.executeQuery("SELECT * FROM getintermediateproducts");
			if(resIntermediateProducts.next()) {
				
				
				//IntermediateProduct intermediateProduct = new IntermediateProduct(resIntermediateProducts.getString("id"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return intermediateProducts;
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


			statement.executeQuery("CALL storedepot("+depot.getName()+", "+
					depot.getDescription()+", "+
					depot.getMaxX()+", "+
					depot.getMaxY()+")");

			for (StoringSpace storingSpace : depot.getStoringSpaces()) {

				statement.executeQuery("CALL storestoringspace ("+depot.getName()+", "+
						storingSpace.getPositionX()+", "+
						storingSpace.getPositionY()+", "+
						storingSpace.toString()+")");
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

			String storingSpace = null;
			if (intermediateProduct.getStoringSpace() != null) {
				storingSpace = intermediateProduct.getStoringSpace().toString();
			}

			statement.executeQuery("CALL storeintermediateproduct("+intermediateProduct.isFinished()+", "+
					intermediateProduct.isDiscarded()+", "+
					intermediateProduct.getId()+", "+
					intermediateProduct.getQuantity()+", "+
					intermediateProduct.getProductType().getName()+", "+
					storingSpace+")");

			statement.executeQuery("COMMIT");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void store(ProductType productType) {
		try {
			Statement statement = getConnection().createStatement();

			statement.executeQuery("BEGIN TRANSACTION");
			statement.executeQuery("CALL storeproducttype("+productType.getName()+", "+
					productType.getPicture()+", "+
					productType.getProcessLine().getName()+")");			
			statement.executeQuery("CALL storeprocessline("+productType.getProcessLine().getName()+", "+
					productType.getProcessLine().getDescription()+", "+
					productType.getName()+")");
			
			for (Process process : productType.getProcessLine().getProcesses()) {
				if (process instanceof Drying) {
					statement.executeQuery("CALL storedrying("+process.getProcessStep()+", "+
							process.getProcessLine().getName()+", "+
							((Drying)process).getMinTime()+", "+
							((Drying)process).getIdealTime()+", "+
							((Drying)process).getMaxTime()+")");
							
				}
				else {
					statement.executeQuery("CALL storedrying("+process.getProcessStep()+", "+
							process.getProcessLine().getName()+", "+
							((SubProcess)process).getName()+", "+
							((SubProcess)process).getDescription()+", "+
							((SubProcess)process).getTemperature()+", "+
							((SubProcess)process).getTreatmentTime()+")");
				}
			}
			statement.executeQuery("COMMIT");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
