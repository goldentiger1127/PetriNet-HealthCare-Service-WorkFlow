package GUIFinal2;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class BTN_StepByStep_Listener extends Mouse_Listener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Simulation.BTN_StepByStep.setVisible(true);
		Simulation.BTN_StepByStep.setForeground(Color.BLUE);
		Simulation.bt = Button.STEP_BY_STEP;
	}	
}

