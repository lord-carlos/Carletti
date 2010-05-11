package gui;

import java.util.ArrayList;
import java.util.TimerTask;


public class UpdateTimer extends java.util.Timer {

	TimerAction timerAction = new TimerAction();
	ArrayList<IntermediateProductPanel> intermediateProductPanels;
	
	public UpdateTimer(int updateIntervalInSeconds, ArrayList<IntermediateProductPanel> intermediateProductPanels){
		this.schedule(timerAction, 0l, updateIntervalInSeconds*1000l);
		this.intermediateProductPanels = intermediateProductPanels;
	}
	
	class TimerAction extends TimerTask{
		@Override
		public void run() {
			
			for (int i = 0; i < intermediateProductPanels.size(); i++) {
				intermediateProductPanels.get(i).updateTime();
			}
		}
	}
	
	
}
