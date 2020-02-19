package GUIFinal2;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class BTN_AddArc_Listener extends Mouse_Listener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Simulation.BTN_AddArc.setVisible(true);
		Simulation.BTN_AddArc.setForeground(Color.BLUE);
		Simulation.bt = Button.ADD_ARC;
	}
}

