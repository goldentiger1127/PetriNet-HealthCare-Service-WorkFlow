package GUIFinal2;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class BTN_AddToken_Listener extends Mouse_Listener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Simulation.BTN_AddToken.setVisible(true);
		Simulation.BTN_AddToken.setForeground(Color.BLUE); 
		Simulation.bt = Button.ADD_TOKEN;
	}	
}

