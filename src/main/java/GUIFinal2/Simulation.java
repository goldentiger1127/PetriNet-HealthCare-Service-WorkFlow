// GUI
package GUIFinal2;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

interface IdSync {
	Place syncPlaceId(int id);
	Transition syncTransId(int id);
}

public class Simulation extends JPanel{
	
	public static PetriNet net;
	public static JPanel panel1;
	public static JPanel panel2;
	public static JPanel panel3;
	public static JPanel panel4;
	public static JPanel panel5;
	public static JFrame frame;
	public static JButton BTN_ClearAll, BTN_StepByStep, BTN_Stop, 
						 BTN_AddPlace,BTN_AddToken, BTN_AddTrans, BTN_AddArc,
						 BTN_DeletePlace, BTN_DeleteToken, BTN_DeleteTrans, BTN_DeleteArc, 
						 BTN_EventTiming, BTN_ResourceConfigurator, BTN_Run;
	public static JButton BTN_New,BTN_Open,BTN_Save,BTN_SaveAs,BTN_Close,BTN_Exit,BTN_Help;

	public static Button bt = Button.PANEL;
	//public static PopOutWindow newWindow;
	public static boolean drag;
	
	public static void Simulation() {

		frame = new JFrame();
		//frame.addMyWindowListener();
		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(new GridLayout(0,7));
		frame.getContentPane().add(BorderLayout.NORTH, panel1);

		panel2 = new Simulation();
		panel2.setBackground(Color.WHITE);
		panel2.setLayout(new FlowLayout());
		frame.getContentPane().add(BorderLayout.CENTER, panel2);
		
		panel3 = new Simulation();
		panel3.setBackground(Color.LIGHT_GRAY);
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));
		TitledBorder titledBorder  = BorderFactory.createTitledBorder("Petri Net");
		panel3.setBorder(titledBorder);
		frame.getContentPane().add(BorderLayout.WEST, panel3);
		
//		panel5 = new Simulation();
//		panel5.setBackground(Color.LIGHT_GRAY);
//		panel5.setLayout(new FlowLayout());
//		TitledBorder Consoles = BorderFactory.createTitledBorder("Consloe");
//		panel5.setBorder(Consoles);
////		panel5.setBounds(null);
//		frame.getContentPane().add(BorderLayout.SOUTH, panel5);
	
		
//		
		panel4 = new Simulation();
		panel4.setBackground(Color.LIGHT_GRAY);
		panel4.setLayout(new FlowLayout());
		JLabel panel4Name = new JLabel("Monmouth University, West Long Branch, New Jersey, 2018");
		panel4.add(panel4Name);
	
		frame.getContentPane().add(BorderLayout.SOUTH,panel4);

		BTN_ClearAll = new JButton("Clear All"); BTN_ClearAll.addMouseListener(new BTN_ClearAll_Listener());
		BTN_StepByStep = new JButton("Step By Step");  BTN_StepByStep.addMouseListener(new BTN_StepByStep_Listener());
		BTN_Stop = new JButton("Stop");  BTN_Stop.addMouseListener(new BTN_Stop_Listener());
		BTN_AddPlace = new JButton("Add Place"); BTN_AddPlace.addMouseListener(new BTN_AddPlace_Listener());
		BTN_AddToken = new JButton("Add Token"); BTN_AddToken.addMouseListener(new BTN_AddToken_Listener());
		BTN_AddTrans = new JButton("Add Trans"); BTN_AddTrans.addMouseListener(new BTN_AddTrans_Listener());
		BTN_AddArc = new JButton("Add Arc"); BTN_AddArc.addMouseListener(new BTN_AddArc_Listener());
		BTN_DeletePlace = new JButton("Delete Place"); BTN_DeletePlace.addMouseListener(new BTN_DeletePlace_Listener());
		BTN_DeleteToken = new JButton("Delete Token"); BTN_DeleteToken.addMouseListener(new BTN_DeleteToken_Listener());
		BTN_DeleteTrans = new JButton("Delete Trans"); BTN_DeleteTrans.addMouseListener(new BTN_DeleteTrans_Listener());
		BTN_DeleteArc = new JButton("Delete Arc"); BTN_DeleteArc.addMouseListener(new BTN_DeleteArc_Listener());
		BTN_EventTiming = new JButton("Event Timing");
		BTN_ResourceConfigurator = new JButton("Resource Configurator");
		BTN_Run = new JButton("Run"); BTN_Run.addMouseListener(new BTN_Run_Listener());
		
		BTN_New= new JButton("New");
		BTN_Open = new JButton("Open");
		
		BTN_Open.addMouseListener(new BTN_OpenFile_Listener());
		
		BTN_Save = new JButton("Save");
		
		BTN_SaveAs = new JButton("Save As");
		BTN_SaveAs.addMouseListener(new BTN_SaveFile_Listener());
		
		BTN_Close = new JButton("Close");
		BTN_Exit = new JButton("Exit");
		
		BTN_Help = new JButton("Help");
		
		//b14.addActionListener();
		
		panel3.add(BTN_New);
		panel3.add(BTN_Open);
		panel3.add(BTN_Save);
		panel3.add(BTN_SaveAs);
		panel3.add(BTN_Close);
		panel3.add(BTN_Exit);
		panel3.add(BTN_Help);
		
		panel1.add(BTN_ClearAll); panel1.add(BTN_StepByStep); panel1.add(BTN_Stop); panel1.add(BTN_AddPlace);
		panel1.add(BTN_AddToken); panel1.add(BTN_AddTrans); panel1.add(BTN_AddArc); panel1.add(BTN_DeletePlace);
		panel1.add(BTN_DeleteToken); panel1.add(BTN_DeleteTrans); panel1.add(BTN_DeleteArc); panel1.add(BTN_EventTiming);
		panel1.add(BTN_ResourceConfigurator); panel1.add(BTN_Run);

		panel2.addMouseListener(new Panel2_Listener());
			
		frame.setTitle("PetriNet Application");
		frame.setSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	
		net= new PetriNet();
		drag = false;
	}
	
	public void paint(Graphics g) {
		if(bt == Button.CLEAR_ALL){
			g.setColor(Color.WHITE);
			bt = Button.PANEL;
		}
		//System.out.println("进入到simulation的paint");
		super.paint(g);
		g.setColor(Color.WHITE);
		net.paint(g);// method defined in PetriNet class
		//System.out.println("_______退出simulation的paint,退出 net.paint()____________");
	}
	
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				Simulation();
			}
		});
	}
}	


