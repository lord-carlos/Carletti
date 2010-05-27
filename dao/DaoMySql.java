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
import model.ProcessLine;
import model.ProcessLog;
import model.ProductType;
import model.StoringSpace;
import model.SubProcess;

public class DaoMySql implements Dao{

	private static Connection connection;
	private static DaoMySql dao = null;

	private DaoMySql() {
		connection = getConnection();
	}

	private static Connection getConnection() {
		try {
			if (connection==null || !connection.isValid(10000)) {
				connection = DriverManager.getConnection("jdbc:mysql://81.89.108.201:3306/carletti", "root", "MortenErSej!");	
			}
		} catch (SQLException e) {
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

			for (int i = 0;i<intermediateProduct.getProcessLogs().size();i++){
				ProcessLog pLog = intermediateProduct.getProcessLogs().get(i);
				String storingSpaceId = null;
				if (pLog.getStoringSpace()!=null){
					storingSpaceId = pLog.getStoringSpace().toString();
				}
				statement.executeQuery("CALL storeprocessLog ("+pLog.getStartTime()+", " +
						pLog.getEndTime()+", '" +
						intermediateProduct.getId()+"', '" +
						pLog.getProcess().getProcessLine().getName()+"', " +
						pLog.getProcess().getProcessStep()+", '" +
						storingSpaceId+"')");
			}


			statement.executeQuery("COMMIT");
		} catch (SQLException e) {
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

					Drying d = (Drying)process;
					for (int i = 0;i<d.getDepots().size();i++){
						statement.executeQuery("CALL storedepotdryingassociation ('"+d.getDepots().get(i).toString()+"', '" +
								d.getProcessLine().getName()+"', " +
								d.getProcessStep()+")");
					}

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
				//producttyper
				Statement statementTypes = getConnection().createStatement();
				ResultSet resTypes = statementTypes.executeQuery("SELECT P.name,P.processine,P.picture,L.description FROM getproducttype P left join getprocessline L on P.name=L.producttype");

				while (resTypes.next()){
					ProductType p = new ProductType(resTypes.getString("name"));
					productType.add(p);
					p.setPicture(resTypes.getString("picture"));
					ProcessLine l = new ProcessLine(resTypes.getString("processline"), resTypes.getString("description"), p);

					Statement statementProcesses = getConnection().createStatement();
					ResultSet resProcesses = statementProcesses.executeQuery("SELECT * FROM getprocesses where processline='"+l.getName()+"' order by processStep");

					while (resProcesses.next()){
						if (resProcesses.getString("pikachu")=="s"){
							l.createSubProcess(resProcesses.getInt("processStep"), resProcesses.getString("name"), resProcesses.getString("description"), resProcesses.getLong("treatmentTime"), resProcesses.getDouble("temperature"));
						} else if(resProcesses.getString("pikachu")=="d"){
							l.createDrying(resProcesses.getInt("processStep"), resProcesses.getLong("minTime"), resProcesses.getLong("idealTime"), resProcesses.getLong("maxTime"));
						}
					}
				}


				//depoter
				Statement statementDepots = getConnection().createStatement();
				ResultSet resDepots = statementDepots.executeQuery("SELECT * FROM getdepots");
				while (resDepots.next()) {
					Depot d = new Depot(resDepots.getString("name"), resDepots.getString("description"), resDepots.getInt("maxx"), resDepots.getInt("maxy"));
					depots.add(d);

					Statement statementDDAsociation = getConnection().createStatement();
					ResultSet resDDAsociation = statementDDAsociation.executeQuery("SELECT * FROM getdepotdryingassociation where depot='"+d.toString()+"'");

					while (resDDAsociation.next()) {
						d.addDrying(findDrying(resDDAsociation.getString("processLine"), resDDAsociation.getInt("processStep")));
					}

				}

				//mellemvare
				Statement statementIntermediateProducts = getConnection().createStatement();
				ResultSet resIntermediateProducts = statementIntermediateProducts.executeQuery("SELECT * FROM getintermediateproduct");
				while (resIntermediateProducts.next()) {

					IntermediateProduct iP = new IntermediateProduct(resIntermediateProducts.getString("id"), findProductType(resIntermediateProducts.getString("productType")),resIntermediateProducts.getDouble("quantity"));
					iP.setStoringSpace(findStoringSpace(resDepots.getString("storingspaceid")));

					Statement statementProcessLog = getConnection().createStatement();
					ResultSet resProcessLog = statementProcessLog.executeQuery("SELECT * FROM getprocessLog where intermediateProduct='"+iP.getId()+"' order by startTime");

					while (resProcessLog.next()){
						Process process = iP.getProductType().getProcessLine().getProcesses().get(resProcessLog.getInt("processStep")-1);
						iP.createProcessLog(process, findStoringSpace(resProcessLog.getString("storingSpace")));
					}
					intermediatateProducts.add(iP);

				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}


		}

		private ProductType findProductType(String name){
			ProductType found = null;
			int i = 0;
			while (found!=null && i<productType.size()){
				ProductType p = productType.get(i);

				if (p.getName().equals(name)){
					found=p;
				} else {
					i++;
				}

			}
			return found;
		}

		private StoringSpace findStoringSpace(String name){
			StoringSpace found = null;
			int i = 0;
			while (found!=null && i<depots.size()){
				int j = 0;
				while(found!=null && i<depots.get(i).getStoringSpaces().size()){
					StoringSpace s = depots.get(i).getStoringSpaces().get(j);

					if (s.toString().equals(name)){
						found=s;
					} else {
						j++;
					}
				}
				i++;
			}
			return found;
		}

		private Drying findDrying(String processLine, int processStep){
			Drying found = null;
			int i = 0;
			while (found!=null && i<productType.size()){
				int j = 0;

				if (productType.get(i).getProcessLine().getName().equals(processLine)){
					while(found!=null && i<productType.get(i).getProcessLine().getProcesses().size()){
						Process p = productType.get(i).getProcessLine().getProcesses().get(j);

						if (p.getProcessStep()==processStep){
							found=(Drying) p;
						} else {
							j++;
						}
					}
				}
				i++;
			}
			return found;
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
