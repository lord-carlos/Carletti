package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IntermediateProductTest {

	private ProductType pT;
	private IntermediateProduct iP;
	private Depot dp ;

	@Before
	public void setUp() throws Exception {
		pT = new ProductType("test");
		ProcessLine pL = new ProcessLine("test", "none", pT);

		pL.createSubProcess(1, "ProcessTest", "none", 10, 10);
		pL.createDrying(2, 1, 2, 3);
		pL.createSubProcess(3, "ProcessTest", "none", 10, 10);
		pL.createSubProcess(4, "ProcessTest", "none", 10, 10);
		pL.createDrying(5, 1, 2, 3);
		pL.createSubProcess(6, "ProcessTest", "none", 10, 10);
		pL.createDrying(7, 1, 2, 3);
		pL.createSubProcess(8, "ProcessTest", "none", 10, 10);
		pL.createDrying(9, 1, 2, 3);

		iP = new IntermediateProduct("", pT, 0);
		
		dp = new Depot("", "", 2, 2);;
	}
	
	@Test
	public void testConstructor() {
		IntermediateProduct iP1 = new IntermediateProduct("", pT, 0);
		assertEquals(pT,iP1.getProductType());
		assertTrue(pT.getIntermediateProducts().contains(iP1));
		assertEquals(0, iP1.getQuantity(),0.1);
		IntermediateProduct iP2 = new IntermediateProduct("", pT, 1.2);
		assertEquals(pT,iP2.getProductType());
		assertTrue(pT.getIntermediateProducts().contains(iP2));
		assertEquals(1.2, iP2.getQuantity(),0.1);
		ProductType pT1 = new ProductType("");
		IntermediateProduct iP3 = new IntermediateProduct("asd", pT1, 3000);
		assertEquals(pT1,iP3.getProductType());
		assertTrue(pT1.getIntermediateProducts().contains(iP3));
		assertEquals(3000, iP3.getQuantity(),0.1);
	}

	@Test (expected = RuntimeException.class)
	public void testConstructor1() {
		new IntermediateProduct("", null, 0);
	}

	@Test (expected = RuntimeException.class)
	public void testConstructor2() {
		new IntermediateProduct("", pT, -1);
	}

	@Test (expected = RuntimeException.class)
	public void testConstructor3() {
		new IntermediateProduct("", pT, -301);
	}

	@Test (expected = RuntimeException.class)
	public void testConstructor4() {
		new IntermediateProduct("", pT, -0.000001);
	}

	@Test (expected = RuntimeException.class)
	public void testConstructor5() {
		new IntermediateProduct("", null, 12.5);
	}

	@Test
	public void testSetId(){
		iP.setId("asdf");
		assertEquals("asdf", iP.getId());
		iP.setId("");
		assertEquals("", iP.getId());
	}

	@Test
	public void testSetProductType(){
		ProductType pT1 = new ProductType("asdf");
		iP.setProductType(pT1);
		assertEquals(pT1, iP.getProductType());
		assertTrue(pT1.getIntermediateProducts().contains(iP));
		iP.setProductType(pT);
		assertEquals(pT, iP.getProductType());
		assertFalse(pT1.getIntermediateProducts().contains(iP));
		assertTrue(pT.getIntermediateProducts().contains(iP));
	}

	@Test (expected = RuntimeException.class)
	public void testSetProductType1(){
		iP.setProductType(null);
	}

	@Test
	public void testSetQuantity(){
		iP.setQuantity(2.2);
		assertEquals(2.2, iP.getQuantity(),0.1);
		iP.setQuantity(67.3);
		assertEquals(67.3, iP.getQuantity(),0.1);
		iP.setQuantity(500);
		assertEquals(500, iP.getQuantity(),0.1);
		iP.setQuantity(14);
		assertEquals(14, iP.getQuantity(),0.1);
		iP.setQuantity(3004.6);
		assertEquals(3004.6, iP.getQuantity(),0.1);
		iP.setQuantity(0);
		assertEquals(0, iP.getQuantity(),0.1);

	}

	@Test (expected = RuntimeException.class)
	public void testSetQuantity1(){
		iP.setQuantity(-0.0001);
	}

	@Test (expected = RuntimeException.class)
	public void testSetQuantity2(){
		iP.setQuantity(-14.1);
	}

	@Test (expected = RuntimeException.class)
	public void testSetQuantity3(){
		iP.setQuantity(-16);
	}

	@Test (expected = RuntimeException.class)
	public void testSetQuantity4(){
		iP.setQuantity(-300.3);
	}

	@Test 
	public void testSetStoringSpace (){
		iP.setStoringSpace(dp.getStoringSpaces().get(0));
		assertEquals(dp.getStoringSpaces().get(0), iP.getStoringSpace());
		assertEquals(iP,dp.getStoringSpaces().get(0).getIntermediateProduct());

		iP.setStoringSpace(dp.getStoringSpaces().get(1));
		assertEquals(dp.getStoringSpaces().get(1), iP.getStoringSpace());
		assertEquals(iP,dp.getStoringSpaces().get(1).getIntermediateProduct());
		assertNull(dp.getStoringSpaces().get(0).getIntermediateProduct());

		iP.unsetStoringSpace();
		assertNull(iP.getStoringSpace());
		assertNull(dp.getStoringSpaces().get(1).getIntermediateProduct());
	}

	@Test 
	public void testCreateProcessLog(){
		ProcessLog pLog1 = iP.createProcessLog(pT.getProcessLine().getProcesses().get(0), null);
		assertEquals(1,iP.getProcessLogs().size());
		assertTrue(pT.getProcessLine().getProcesses().get(0).getProcessLogs().contains(pLog1));
		ProcessLog pLog2 = iP.createProcessLog(pT.getProcessLine().getProcesses().get(1), dp.getStoringSpaces().get(0));
		assertEquals(2,iP.getProcessLogs().size());
		assertTrue(pT.getProcessLine().getProcesses().get(1).getProcessLogs().contains(pLog2));
		assertTrue(dp.getStoringSpaces().get(0).getProcessLogs().contains(pLog2));
		iP.deleteProcessLog(pLog1);
		assertFalse(pT.getProcessLine().getProcesses().get(0).getProcessLogs().contains(pLog1));
		assertEquals(1,iP.getProcessLogs().size());
		iP.deleteProcessLog(pLog2);
		assertFalse(pT.getProcessLine().getProcesses().get(1).getProcessLogs().contains(pLog2));
		assertFalse(dp.getStoringSpaces().get(0).getProcessLogs().contains(pLog2));
		assertEquals(0,iP.getProcessLogs().size());
	}

	@Test
	public void TestProcessHandling(){
		//before start
		assertFalse(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertNull(iP.getActivProcessLog());
		assertEquals(pT.getProcessLine().getProcesses().get(0), iP.getNextProcess());
		assertEquals(0, iP.getProcessLogs().size());

		//first process
		iP.sendToNextProcess(null);

		assertFalse(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertEquals(pT.getProcessLine().getProcesses().get(0), iP.getActivProcessLog().getProcess());
		assertEquals(pT.getProcessLine().getProcesses().get(1), iP.getNextProcess());
		assertEquals(1, iP.getProcessLogs().size());
		assertNull(iP.getStoringSpace());
		assertTrue(iP.getActivProcessLog().isActive());

		//sekond prcess
		iP.sendToNextProcess(dp.getStoringSpaces().get(0));

		assertFalse(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertEquals(pT.getProcessLine().getProcesses().get(1), iP.getActivProcessLog().getProcess());
		assertEquals(pT.getProcessLine().getProcesses().get(2), iP.getNextProcess());
		assertEquals(2, iP.getProcessLogs().size());
		assertEquals(dp.getStoringSpaces().get(0), iP.getStoringSpace());
		assertEquals(iP, dp.getStoringSpaces().get(0).getIntermediateProduct());
		assertTrue(iP.getActivProcessLog().isActive());
		assertFalse(iP.getProcessLogs().get(0).isActive());
		
		//third prcess
		iP.sendToNextProcess(null);

		assertFalse(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertEquals(pT.getProcessLine().getProcesses().get(2), iP.getActivProcessLog().getProcess());
		assertEquals(pT.getProcessLine().getProcesses().get(3), iP.getNextProcess());
		assertEquals(3, iP.getProcessLogs().size());
		assertNull(iP.getStoringSpace());
		assertNull(dp.getStoringSpaces().get(0).getIntermediateProduct());
		assertTrue(iP.getActivProcessLog().isActive());
		assertFalse(iP.getProcessLogs().get(1).isActive());
		
		//Skipping the middle processes
		iP.sendToNextProcess(null);
		iP.sendToNextProcess(null);
		iP.sendToNextProcess(null);
		iP.sendToNextProcess(null);
		//second to last process
		iP.sendToNextProcess(null);
		
		assertFalse(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertEquals(pT.getProcessLine().getProcesses().get(7), iP.getActivProcessLog().getProcess());
		assertEquals(pT.getProcessLine().getProcesses().get(8), iP.getNextProcess());
		assertEquals(8, iP.getProcessLogs().size());
		assertFalse(iP.getProcessLogs().get(0).isActive());
		assertFalse(iP.getProcessLogs().get(1).isActive());
		assertFalse(iP.getProcessLogs().get(2).isActive());
		assertFalse(iP.getProcessLogs().get(3).isActive());
		assertFalse(iP.getProcessLogs().get(4).isActive());
		assertFalse(iP.getProcessLogs().get(5).isActive());
		assertFalse(iP.getProcessLogs().get(6).isActive());
		assertTrue(iP.getActivProcessLog().isActive());
		
		//last process
		iP.sendToNextProcess(null);
		
		assertFalse(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertEquals(pT.getProcessLine().getProcesses().get(8), iP.getActivProcessLog().getProcess());
		assertNull(iP.getNextProcess());
		assertEquals(9, iP.getProcessLogs().size());
		assertTrue(iP.getActivProcessLog().isActive());
		assertFalse(iP.getProcessLogs().get(7).isActive());
		
		//intermediateproduct is finished
		iP.sendToNextProcess(null);
		
		assertTrue(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertNull(iP.getActivProcessLog());
		assertNull(iP.getNextProcess());
		assertEquals(9, iP.getProcessLogs().size());
		assertFalse(iP.getProcessLogs().get(8).isActive());
	}
	
	@Test
	public void testDiscardMethod(){
		//before start
		assertFalse(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertNull(iP.getActivProcessLog());
		assertEquals(pT.getProcessLine().getProcesses().get(0), iP.getNextProcess());
		assertEquals(0, iP.getProcessLogs().size());

		//first process
		iP.sendToNextProcess(null);

		assertFalse(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertEquals(pT.getProcessLine().getProcesses().get(0), iP.getActivProcessLog().getProcess());
		assertEquals(pT.getProcessLine().getProcesses().get(1), iP.getNextProcess());
		assertEquals(1, iP.getProcessLogs().size());
		assertNull(iP.getStoringSpace());
		assertTrue(iP.getActivProcessLog().isActive());

		//sekond prcess
		iP.sendToNextProcess(dp.getStoringSpaces().get(0));

		assertFalse(iP.isFinished());
		assertFalse(iP.isDiscarded());
		assertEquals(pT.getProcessLine().getProcesses().get(1), iP.getActivProcessLog().getProcess());
		assertEquals(pT.getProcessLine().getProcesses().get(2), iP.getNextProcess());
		assertEquals(2, iP.getProcessLogs().size());
		assertEquals(dp.getStoringSpaces().get(0), iP.getStoringSpace());
		assertTrue(iP.getActivProcessLog().isActive());
		assertFalse(iP.getProcessLogs().get(0).isActive());
		
		//testing discard method
		iP.discardThisIntermediateProduct();
		assertFalse(iP.isFinished());
		assertTrue(iP.isDiscarded());
		assertNull(iP.getActivProcessLog());
		assertNull(iP.getNextProcess());
		assertEquals(2, iP.getProcessLogs().size());
		assertNull(iP.getStoringSpace());
		assertFalse(iP.getProcessLogs().get(0).isActive());
		assertFalse(iP.getProcessLogs().get(1).isActive());
		
	}

}
