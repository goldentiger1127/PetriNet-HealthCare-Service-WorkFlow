package GUIFinal2;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class BTN_SaveFile_Listener extends Mouse_Listener {
	@Override
	public void mouseClicked(MouseEvent e) {
		Simulation.BTN_Save.setVisible(true);
		Simulation.BTN_Save.setForeground(Color.BLUE);
	
		if(Simulation.net.saveNet()) {
			System.out.println("");
			Simulation.BTN_Save.setForeground(null);		
		}
		Simulation.bt = Button.PANEL;	
	}
}

