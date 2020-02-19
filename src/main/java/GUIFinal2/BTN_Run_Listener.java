package GUIFinal2;

import java.awt.Color;
import java.awt.Dimension;

import Petrinet_with_GUI.Petrinet_with_GUI.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BTN_Run_Listener extends Mouse_Listener {
	public int choice = 0;
	
	public static JButton J1,J2,J3;
	public static JFrame frame1 ;
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Simulation.BTN_Run.setVisible(true);
		Simulation.BTN_Run.setForeground(Color.BLUE);
		Simulation.bt = Button.RUN;
		frame1= new JFrame();
		frame1.setTitle("Simulation Mode");
		frame1.setSize(new Dimension(450, 250));
		frame1.setDefaultCloseOperation(2);
		frame1.setLocationRelativeTo(null);
		/*
		 * 添加面板 用于选择需要的操作
		 */
		JPanel jPanel=new JPanel();	
		frame1.add(jPanel);
		placeComponents(jPanel);
		frame1.setLocationRelativeTo(null);
		final Simulation_a sim = new Simulation_a();	
		frame1.setVisible(true);
		J1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					sim.Guirun(1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		J2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					sim.Guirun(2);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		J3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					sim.Guirun(3);
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		
	}

	public static void  placeComponents(JPanel jPanel) {
		jPanel.setLayout(null);
		
//		JLabel userLabel=new JLabel("CHOOSE SIMULATION MODE");
//		userLabel.setBounds(100,10,300,20);
//		jPanel.add(userLabel);

		J1=new JButton(" Multiple Server Fixed Step");
		J1.setBounds(20, 30, 400, 40);
		jPanel.add(J1);
		J2=new JButton("Multiple Server Variable Step");
		J2.setBounds(20, 90, 400, 40);
		jPanel.add(J2);
		J3=new JButton("Multiple Server Variable Step (Resource From File)");
		J3.setBounds(20, 150, 400, 40);
		jPanel.add(J3);
		
		
	
	}


}
