package GUIFinal2;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class BTN_DeleteArc_Listener extends Mouse_Listener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Simulation.BTN_DeleteArc.setVisible(true);
		Simulation.BTN_DeleteArc.setForeground(Color.BLUE); 
		Simulation.bt = Button.DELETE_ARC;
	}
}
