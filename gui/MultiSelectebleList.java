package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


/**
 * Shows an Jlist that can be edited by the push of a button<br>
 * the selcteble List will be handled as an ArrayList
 * @author M. C. Høj
 */
@SuppressWarnings("serial")
public class MultiSelectebleList extends Container{

	private JButton btnCloseWindow, btnValgVTilE, btnFjernFraE, btnOpenMiniFrame, btnVaelgAlle, btnVaelgIngen;
	private JLabel lblSelectebleElements, lblSelctedElements;
	private JScrollPane scSelctedElements, scSelctedElementsMF, scSelectebleElementsMF;
	private JList jlSelectedElements, jlSelectedElementsMF, jlSelectebleElementsMF;
	private JDialog miniFrame;

	/**
	 * ArrayList over Elements that can be selected
	 */
	private ArrayList<Object> selectebleElements = new ArrayList<Object>();
	/**
	 * ArrayList over Elements that are selected
	 */
	private ArrayList<Object> selectedElements = new ArrayList<Object>();
	/**
	 * ArrayList over Elements that can be selected
	 */
	private ArrayList<Object> origenList = new ArrayList<Object>();

	/**
	 * DefaultListModel over Elements names that can be selected
	 */
	private DefaultListModel selectebleElementsNames = new DefaultListModel();
	/**
	 * DefaultListModel over Elements names that are selected
	 */
	private DefaultListModel selectedElementsNames = new DefaultListModel();
	/**
	 * DefaultListModel over Elements names that can be selected
	 */
	private DefaultListModel origenListNames = new DefaultListModel();

	/**
	 * actionsListener for our Jbuttons
	 */
	private BtnController btnController = new BtnController();

	// ****************************** Constructer ***************************************
	/**
	 * the constructer sets the grafical interface for the MultiSelectableList
	 * @param ElementNavn The names of the elements
	 */
	public MultiSelectebleList(String ElementNavn){
		JFrameSetup(ElementNavn);

		jlSelectedElements = new JList(selectedElementsNames);
		jlSelectedElements.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		scSelctedElements = new JScrollPane(jlSelectedElements);
		scSelctedElements.setSize(150, 75);
		scSelctedElements.setLocation(0, 0);
		this.add(scSelctedElements);

		btnOpenMiniFrame = new JButton("Vælg "+ ElementNavn);
		btnOpenMiniFrame.setSize(150, 25);
		btnOpenMiniFrame.setLocation(0, 75);
		btnOpenMiniFrame.addActionListener(btnController);
		this.add(btnOpenMiniFrame);

		super.setSize(150, 100);
		this.setVisible(true);

	}
	// ****************************** Container methods ***************************************
	/**
	 * Resizes this component so that it has width width and height height. 
	 * @param width the new width of this component in pixels
	 * @param height the new height of this component in pixels
	 */
	public void setSize(int width, int height){

		if (width<150){
			width=150;
		}
		if (height<100){
			height=100;
		}

		scSelctedElements.setSize(width, height-25);
		btnOpenMiniFrame.setSize(width, 25);
		btnOpenMiniFrame.setLocation(0, height-25);
		super.setSize(width, height);

	}

	// ****************************** List methods ***************************************

	/**
	 * adds an objekt that can be selected to the end of the list, and sets the objects name
	 * @param object The object that will be added to the list
	 * @param objectName The showed name of the object
	 */
	public void add(Object object, String objectName,boolean isSelected){
		origenList.add(object);
		origenListNames.addElement(objectName);
		if (isSelected) {
			selectedElements.add(object);
			selectedElementsNames.addElement(objectName);
		} else {
			selectebleElements.add(object);
			selectebleElementsNames.addElement(objectName);
		}
	}

	/**
	 * adds an objekt that can be selected to the end of the list, and sets the objects name to the returned String from the toString method
	 * @param object The object that will be added to the list
	 */
	public void add(Object object, boolean isSelected){
		origenList.add(object);
		origenListNames.addElement(object.toString());
		if (isSelected) {
			selectedElements.add(object);
			selectedElementsNames.addElement(object.toString());
		} else {
			selectebleElements.add(object);
			selectebleElementsNames.addElement(object.toString());
		}
	}

	/**
	 * adds an objekt that can be selected to a specific position, and sets the objects name
	 * @param index The position where the object will be added
	 * @param object The object that will be added to the list
	 * @param objectName The showed name of the object
	 */
	public void add(int index, Object object, String objectName, boolean isSelected){
		origenList.add(index, object);
		origenListNames.add(index, objectName);
		if (isSelected) {
			selectedElements.add(index, object);
			selectedElementsNames.add(index, objectName);
		} else {
			selectebleElements.add(index, object);
			selectebleElementsNames.add(index, objectName);
		}
	}

	/**
	 * adds an objekt that can be selected to a specific position, and sets the objects name to the returned String from the toString method
	 * @param index The position where the object will be added
	 * @param object The object that will be added to the list
	 */
	public void add(int index, Object object, boolean isSelected){
		origenList.add(index, object);
		origenListNames.add(index, object.toString());
		if (isSelected) {
			selectedElements.add(index, object);
			selectedElementsNames.add(index, object.toString());
		} else {
			selectebleElements.add(index, object);
			selectebleElementsNames.add(index, object.toString());
		}
	}

	/**
	 * Removes object from the lists
	 * @param object Object to remove
	 */
	public void remove(Object object){

		if (origenList.contains(object)){
			int index = origenList.indexOf(object);
			origenList.remove(index);
			origenListNames.remove(index);
			if (selectebleElements.contains(object)){
				index = selectebleElements.indexOf(object);
				selectebleElements.remove(index);
				selectebleElementsNames.remove(index);
			}
			if (selectedElements.contains(object)){
				index = selectedElements.indexOf(object);
				selectedElements.remove(index);
				selectedElementsNames.remove(index);
			}
		}

	}

	/**
	 * Removes object at a specific lokation
	 * @param index Lokation of the object to remove
	 */
	public void remove(int index){
		if (origenList.size()>index){
			Object object = origenList.get(index);
			origenList.remove(index);
			origenListNames.remove(index);
			if (selectebleElements.contains(object)){
				index = selectebleElements.indexOf(object);
				selectebleElements.remove(index);
				selectebleElementsNames.remove(index);
			}
			if (selectedElements.contains(object)){
				index = selectedElements.indexOf(object);
				selectedElements.remove(index);
				selectedElementsNames.remove(index);
			}
		}
	}

	/**
	 * Clears all lists
	 */
	public void Clear(){
		origenList.clear();
		origenListNames.clear();
		selectebleElements.clear();
		selectebleElementsNames.clear();
		selectedElements.clear();
		selectedElementsNames.clear();
	}

	/**
	 * Clears the list over selected items
	 */
	public void ClearSelctedItemsList(){
		selectedElements.clear();
		selectedElementsNames.clear();

		selectebleElements.clear();
		selectebleElementsNames.clear();

		for (int i=0;i<origenList.size();i++){
			selectebleElements.add(origenList.get(i));
			selectebleElementsNames.addElement(origenListNames.elementAt(i));
		}

	}

	/**
	 * Gets the list over objects that can be selcted
	 */
	public ArrayList<Object> getSelectebleElements(){
		return origenList;
	}

	/**
	 * Gets the list over objects that isn't selected
	 */
	public ArrayList<Object> getNonSelectedElements(){
		return selectebleElements;
	}

	/**
	 * Gets the list over selcted bjects
	 */
	public ArrayList<Object> getSelectedElements(){
		return selectedElements;
	}

	/**
	 * Gets the indexes of the selceted objects
	 */
	public int[] getSelectedIndexs(){
		int[] integersToReturn = new int[selectedElements.size()];

		for (int i=0; i<selectedElements.size();i++){
			integersToReturn[i]=selectebleElements.indexOf(selectedElements.get(i));
		}

		return integersToReturn;
	}

	// ****************************** actionListner setup ***************************************

	public void addActionToClose(ActionListener a){
		btnCloseWindow.addActionListener(a);
	}

	public void removeActionFromClose(ActionListener a){
		btnCloseWindow.removeActionListener(a);
	}
	
	public JButton getBtnCloseWindow(){
		return btnCloseWindow;
	}


	// ****************************** JFrame setup ***************************************

	/**
	 * sets the elements for the miniJframe
	 */
	private void JFrameSetup(String ElementNavn){


		miniFrame = new JDialog();
		miniFrame.setModal(true);
		miniFrame.setTitle("ValgListe over "+ ElementNavn);
		miniFrame.setLocation(300, 200);
		miniFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		miniFrame.setLayout(null);
		miniFrame.setSize(450, 300);
		miniFrame.setResizable(false);
		miniFrame.setVisible(false);
		miniFrame.toFront();


		lblSelectebleElements = new JLabel("Tilgængelige "+ ElementNavn);
		Font LabelFontH1 = new Font(lblSelectebleElements.getFont().getName(),Font.BOLD,13);
		lblSelectebleElements.setFont(LabelFontH1);
		lblSelectebleElements.setSize(190, 25);
		lblSelectebleElements.setLocation(20, 25);
		miniFrame.add(lblSelectebleElements);

		lblSelctedElements = new JLabel("Valgte " + ElementNavn);
		lblSelctedElements.setFont(LabelFontH1);
		lblSelctedElements.setSize(150, 25);
		lblSelctedElements.setLocation(250, 25);
		miniFrame.add(lblSelctedElements);

		btnCloseWindow = new JButton("Luk winduet");
		btnCloseWindow.setName("btnCloseWindow");
		btnCloseWindow.setSize(150, 25);
		btnCloseWindow.setLocation(140, 240);
		btnCloseWindow.addActionListener(btnController);
		miniFrame.add(btnCloseWindow);

		btnValgVTilE = new JButton(">>");
		btnValgVTilE.setName("btnValgVTilE");
		btnValgVTilE.setSize(50, 25);
		btnValgVTilE.setLocation(195, 90);
		btnValgVTilE.addActionListener(btnController);
		miniFrame.add(btnValgVTilE);

		btnFjernFraE = new JButton("<<");
		btnFjernFraE.setName("btnFjernFraE");
		btnFjernFraE.setSize(50, 25);
		btnFjernFraE.setLocation(195, 140);
		btnFjernFraE.addActionListener(btnController);
		miniFrame.add(btnFjernFraE);

		jlSelectebleElementsMF = new JList(selectebleElementsNames);
		jlSelectebleElementsMF.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		scSelectebleElementsMF = new JScrollPane(jlSelectebleElementsMF);
		scSelectebleElementsMF.setSize(170, 150);
		scSelectebleElementsMF.setLocation(20, 50);
		miniFrame.add(scSelectebleElementsMF);

		btnVaelgAlle = new JButton("Vælg Alle");
		btnVaelgAlle.setName("btnCloseWindow");
		btnVaelgAlle.setSize(170, 25);
		btnVaelgAlle.setLocation(20, 205);
		btnVaelgAlle.addActionListener(btnController);
		miniFrame.add(btnVaelgAlle);

		jlSelectedElementsMF = new JList(selectedElementsNames);
		jlSelectedElementsMF.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		scSelctedElementsMF = new JScrollPane(jlSelectedElementsMF);
		scSelctedElementsMF.setSize(170, 150);
		scSelctedElementsMF.setLocation(250, 50);
		miniFrame.add(scSelctedElementsMF);

		btnVaelgIngen = new JButton("Vælg Ingen");
		btnVaelgIngen.setName("btnCloseWindow");
		btnVaelgIngen.setSize(170, 25);
		btnVaelgIngen.setLocation(250, 205);
		btnVaelgIngen.addActionListener(btnController);
		miniFrame.add(btnVaelgIngen);

	}

	// ****************************** btnController ***************************************
	/**
	 * actionsListener for our Jbuttons
	 */
	private class BtnController implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnOpenMiniFrame) {
				miniFrame.setVisible(true);
			}

			if (e.getSource() == btnCloseWindow) {
				miniFrame.setVisible(false);
			}

			if (e.getSource() == btnValgVTilE) {
				int[] indexs = jlSelectebleElementsMF.getSelectedIndices();
				for (int i=indexs.length-1; i>=0;i--){
					selectebleElements.remove(indexs[i]);
					selectebleElementsNames.removeElementAt(indexs[i]);
				}

				selectedElements.clear();
				selectedElementsNames.clear();

				for (int i=0;i<origenList.size();i++){
					if (!selectebleElements.contains(origenList.get(i))){
						selectedElements.add(origenList.get(i));
						selectedElementsNames.addElement(origenListNames.elementAt(i));
					}
				}

			}

			if (e.getSource() == btnFjernFraE) {
				int[] indexs = jlSelectedElementsMF.getSelectedIndices();
				for (int i=indexs.length-1; i>=0;i--){
					selectedElements.remove(indexs[i]);
					selectedElementsNames.removeElementAt(indexs[i]);
				}

				selectebleElements.clear();
				selectebleElementsNames.clear();

				for (int i=0;i<origenList.size();i++){
					if (!selectedElements.contains(origenList.get(i))){
						selectebleElements.add(origenList.get(i));
						selectebleElementsNames.addElement(origenListNames.elementAt(i));
					}
				}

			}

			if (e.getSource() == btnVaelgAlle) {
				selectebleElements.clear();
				selectebleElementsNames.clear();


				selectedElements.clear();
				selectedElementsNames.clear();

				for (int i=0;i<origenList.size();i++){
					if (!selectebleElements.contains(origenList.get(i))){
						selectedElements.add(origenList.get(i));
						selectedElementsNames.addElement(origenListNames.elementAt(i));
					}
				}

			}

			if (e.getSource() == btnVaelgIngen) {
				selectedElements.clear();
				selectedElementsNames.clear();

				selectebleElements.clear();
				selectebleElementsNames.clear();

				for (int i=0;i<origenList.size();i++){
					if (!selectedElements.contains(origenList.get(i))){
						selectebleElements.add(origenList.get(i));
						selectebleElementsNames.addElement(origenListNames.elementAt(i));
					}
				}

			}
		}
	}

}
