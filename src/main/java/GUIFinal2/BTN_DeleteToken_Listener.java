package GUIFinal2;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class BTN_DeleteToken_Listener extends Mouse_Listener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Simulation.BTN_DeleteToken.setVisible(true);
		Simulation.BTN_DeleteToken.setForeground(Color.BLUE);
		Simulation.bt = Button.DELETE_TOKEN;
	}
}
