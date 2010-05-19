package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.Drying;
import model.IntermediateProduct;
import model.ProcessLog;


public class UpdateTimer extends java.util.Timer {

	private TimerAction timerAction = new TimerAction();
	private ArrayList<IntermediateProductPanel> intermediateProductPanels;
	private ArrayList<ProcessLog> logsRecievedAnWarning = new ArrayList<ProcessLog>();
	private ArrayList<ProcessLog> logsToOld = new ArrayList<ProcessLog>();

	public UpdateTimer(int updateIntervalInSeconds, ArrayList<IntermediateProductPanel> intermediateProductPanels){
		this.schedule(timerAction, 100l, updateIntervalInSeconds*1000l);
		this.intermediateProductPanels = intermediateProductPanels;
	}

	class TimerAction extends TimerTask{
		@Override
		public void run() {

			// opdatere prograssbars
			for (int i = 0; i < intermediateProductPanels.size(); i++) {
				intermediateProductPanels.get(i).updateTime();
			}

			List<IntermediateProduct> intermediateProducts = service.Service.getService().getActiveIntermediateProducts();

			//leder efter mellemvare hvor der skal gives en advarsel
			for (int i = 0; i < intermediateProducts.size(); i++) {

				if (intermediateProducts.get(i).getActivProcessLog()!=null){
					if (intermediateProducts.get(i).getActivProcessLog().getProcess().getClass().equals(Drying.class)){
						
						Drying drying = (Drying) intermediateProducts.get(i).getActivProcessLog().getProcess();	
						ProcessLog log = intermediateProducts.get(i).getActivProcessLog();	
						long currentTime = System.currentTimeMillis()-log.getStartTime().getTime();
						if ((drying.getIdealTime()+(drying.getMaxTime()-drying.getIdealTime())/2)<currentTime && drying.getMaxTime()>currentTime && !logsRecievedAnWarning.contains(log)){
							logsRecievedAnWarning.add(log);
							JDialog dialogWarning = new JOptionPane("Mellemvaren "+intermediateProducts.get(i)+" på placeringengen "+log.getStoringSpace().getDepot()+" "+log.getStoringSpace()+" har snat overskredet sin tørretid!",JOptionPane.WARNING_MESSAGE,JOptionPane.CLOSED_OPTION).createDialog("Advarsel");
							dialogWarning.setModal(false);
							dialogWarning.setAlwaysOnTop(true);
							dialogWarning.setVisible(true);
						} else if (drying.getMaxTime()<currentTime && !logsToOld.contains(log)) {
							logsToOld.add(log);
							JDialog dialogToOld = new JOptionPane("Mellemvaren "+intermediateProducts.get(i)+" på placeringengen "+log.getStoringSpace().getDepot()+" "+log.getStoringSpace()+" har overskredet sin tørretid!",JOptionPane.ERROR_MESSAGE,JOptionPane.CLOSED_OPTION).createDialog("Advarsel");
							dialogToOld.setModal(false);
							dialogToOld.setAlwaysOnTop(true);
							dialogToOld.setVisible(true);
						}

					}
				}

			}

		}
	}
}
