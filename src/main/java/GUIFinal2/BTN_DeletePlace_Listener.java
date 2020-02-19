package GUIFinal2;


import java.awt.Color;
import java.awt.event.MouseEvent;

public class BTN_DeletePlace_Listener extends Mouse_Listener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Simulation.BTN_DeletePlace.setVisible(true);
		Simulation.BTN_DeletePlace.setForeground(Color.BLUE);
		Simulation.bt = Button.DELETE_PLACE;
	}
}
