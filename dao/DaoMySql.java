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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/carletti", "root", "2495");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Connection getConnection() {
		try {
			if (connection==null || !connection.isValid(10000)) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost/carletti", "root", "2495");	
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
		return Databaselists.getDatabaselists().getDepots();
	}

	@Override
	public List<IntermediateProduct> getAllIntermediateProducts() {
		return Databaselists.getDatabaselists().getIntermediateProducts();
	}

	@Override
	public List<ProductType> getAllProductTypes() {
		return Databaselists.getDatabaselists().getProductTypes();
	}

	@Override
	public void store(Depot depot) {
		try {
			Statement statement = getConnection().createStatement();

			statement.executeQuery("BEGIN WORK");


			statement.executeQuery("CALL storedepot('"+depot.getName()+"', '"+
					depot.getDescription()+"', "+
					depot.getMaxX()+", "+
					depot.getMaxY()+")");

			for (StoringSpace storingSpace : depot.getStoringSpaces()) {

				statement.executeQuery("CALL storestoringspace ('"+depot.getName()+"', "+
						storingSpace.getPositionX()+", "+
						storingSpace.getPositionY()+", '"+
						storingSpace.toString()+"')");
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

			statement.executeQuery("BEGIN WORK");

			String storingSpace = null;
			if (intermediateProduct.getStoringSpace() != null) {
				storingSpace = intermediateProduct.getStoringSpace().toString();
			}

			statement.executeQuery("CALL storeintermediateproduct("+intermediateProduct.isFinished()+", "+
					intermediateProduct.isDiscarded()+", '"+
					intermediateProduct.getId()+"', "+
					intermediateProduct.getQuantity()+", '"+
					intermediateProduct.getProductType().getName()+"', '"+
					storingSpace+"')");

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

			statement.executeQuery("BEGIN WORK");
			statement.executeQuery("CALL storeproducttype('"+productType.getName()+"', '"+
					productType.getProcessLine().getName()+"', '"+
					productType.getPicture()+"')");			
			statement.executeQuery("CALL storeprocessline('"+productType.getProcessLine().getName()+"', '"+
					productType.getProcessLine().getDescription()+"', '"+
					productType.getName()+"')");

			for (Process process : productType.getProcessLine().getProcesses()) {
				if (process instanceof Drying) {
					statement.executeQuery("CALL storedrying("+process.getProcessStep()+", '"+
							process.getProcessLine().getName()+"', "+
							((Drying)process).getMinTime()+", "+
							((Drying)process).getIdealTime()+", "+
							((Drying)process).getMaxTime()+")");

				}
				else {
					statement.executeQuery("CALL storesubprocess("+process.getProcessStep()+", '"+
							process.getProcessLine().getName()+"', '"+
							((SubProcess)process).getName()+"', '"+
							((SubProcess)process).getDescription()+"', "+
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

	private static class Databaselists{

		private ArrayList<Depot> depots = new ArrayList<Depot>();
		private ArrayList<IntermediateProduct> intermediatateProducts = new ArrayList<IntermediateProduct>();
		private ArrayList<ProductType> productType = new ArrayList<ProductType>();
		private static Databaselists databaselists= null;

		private Databaselists(){
			runUpdate();
		}

		public static Databaselists getDatabaselists(){
			if (databaselists==null){
				databaselists= new Databaselists();
			}
			return databaselists;
		}

		private void runUpdate(){
			depots.clear();
			productType.clear();
			intermediatateProducts.clear();


			try {
				Statement statementDepots = getConnection().createStatement();

				ResultSet resDepots = statementDepots.executeQuery("SELECT * FROM getdepots");
				if(resDepots.next()) {
					depots.add(new Depot(resDepots.getString("name"), resDepots.getString("description"), resDepots.getInt("maxx"), resDepots.getInt("maxy")));
				}

				Statement statementIntermediateProducts = getConnection().createStatement();

				ResultSet resIntermediateProducts = statementIntermediateProducts.executeQuery("SELECT * FROM getintermediateproduct");
				if(resIntermediateProducts.next()) {


					//IntermediateProduct intermediateProduct = new IntermediateProduct(resIntermediateProducts.getString("id"));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		public ArrayList<Depot> getDepots(){
			runUpdate();
			return depots;
		}

		public ArrayList<ProductType> getProductTypes(){
			runUpdate();
			return productType;
		}

		public ArrayList<IntermediateProduct> getIntermediateProducts(){
			runUpdate();
			return intermediatateProducts;
		}


	}

}
