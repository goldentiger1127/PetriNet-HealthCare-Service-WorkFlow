package jinhu;

/*******************************************************************************
 * @project: capital-pdm-sj-jszx
 * @package: com.acconsys.avidm4.ui
 * @file: CreatPdmPartUI.java
 * @author: burns
 * @created: 2017-12-12
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2017 AcconSys All rights reserved.
 ******************************************************************************/


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import Petrinet_with_GUI.Petrinet_with_GUI.Simulation_a;

public class MainMenu extends JDialog {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -279332465989743454L;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JScrollPane centerScrollPane;
    private GridBagLayout centerLayout;
    private GridBagConstraints centerConstraints;
    private ArrayList<JComboBox> flList;
    private Map<String, String> graphMap = new HashMap<>();
    public MainMenu(JFrame parent) {
        super(parent, true);
        initData();
        initUI();
    }
    
    
    private void initUI() {
        BorderLayout mainLayout = new BorderLayout();
        getContentPane().setLayout(mainLayout);
        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(350, 20));
        centerPanel = new JPanel();
        centerScrollPane = new JScrollPane();
        centerScrollPane.setViewportView(centerPanel);
        northPanel.setPreferredSize(new Dimension(350, 200));
        southPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(350, 100));
        int selectedComponent = 0;

        /**
         * 上部部分内容
         */
        {
            GridLayout northLayout = new GridLayout(4, 2);
            northPanel.setLayout(northLayout);
            JLabel cpL = new JLabel("*Graph Title：");
            final JTextField cpTF = new JTextField("");
            cpTF.addFocusListener(new FocusListener() {
				// Get the content of input box
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					graphMap.put("Title", cpTF.getText());
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
				}
			});
            
            JLabel lxL = new JLabel("*Variable：");
            JComboBox lxCB = new JComboBox(new String[] { "--Choose a parameter",
                    "doctor", "nurse", "bed" });
            lxCB.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					graphMap.put("paramater", (String) e.getItem());
				}
			});
         

            JLabel sjbjL = new JLabel("*Increment：");
            final JTextField stepTF = new JTextField();
            
            stepTF.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					graphMap.put("paramater_step", stepTF.getText());
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
            
            
            JLabel paraNum = new JLabel("*Max Number：");
            final JTextField paraTF = new JTextField();
            
            paraTF.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					graphMap.put("paramater_num", paraTF.getText());
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
            
            
            northPanel.add(cpL);
            northPanel.add(cpTF);
            northPanel.add(lxL);
            northPanel.add(lxCB);
            northPanel.add(sjbjL);
            northPanel.add(stepTF);
            northPanel.add(paraNum);
            northPanel.add(paraTF);
            	
//          JLabel isOrNotCpL = new JLabel("*创建为产品：");
//          JComboBox isOrNotCpCB = new JComboBox(new String[] { "", "是", "否" });
//            northPanel.add(isOrNotCpL);
//            northPanel.add(isOrNotCpCB);
        }

        /**
         * 中部部分
         */
         {
            centerPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
            centerLayout = new GridBagLayout();
            centerPanel.setLayout(centerLayout);

            centerConstraints = new GridBagConstraints();
            centerConstraints.fill = GridBagConstraints.BOTH;
            // centerConstraints.anchor = GridBagConstraints.WEST;
            // centerConstraints.weightx=0;
            // centerConstraints.weighty=0;
            {
                JLabel bhL = new JLabel("*Initial arrival interval:");
                final JTextField bhTF = new JTextField();
                centerPanel.add(bhL);
                centerPanel.add(bhTF);
                add(centerLayout, bhL, centerConstraints, 0, 0, 6 );
                add(centerLayout, bhTF, centerConstraints, 1, 0, 6);
                bhTF.addFocusListener(new FocusListener() {
    				// Get the content of input box
    				@Override
    				public void focusLost(FocusEvent e) {
    					// TODO Auto-generated method stub
    					graphMap.put("Arrival_Interval", bhTF.getText());
    				}
    				
    				@Override
    				public void focusGained(FocusEvent e) {
    					// TODO Auto-generated method stub
    				}
    			});
            }
            {

                JLabel arrivalTimejL = new JLabel("  *Decrement：");
                final JTextField arrivalTimeTF = new JTextField();
//                JComboBox sjbjCB = new JComboBox(new String[] { "", "是", "否" });
                centerPanel.add(arrivalTimejL);
                centerPanel.add(arrivalTimeTF);
                add(centerLayout, arrivalTimejL, centerConstraints, 0, 0, 1);
                add(centerLayout, arrivalTimeTF, centerConstraints, 1, 0, 0);
                arrivalTimeTF.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						// TODO Auto-generated method stub
						graphMap.put("time_Step", arrivalTimeTF.getText());
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
            }

          
            {
                JLabel mcL = new JLabel("*Coefficient for #Doctors:");
                final JTextField mcTF = new JTextField();
                centerPanel.add(mcL);
                centerPanel.add(mcTF);
                add(centerLayout, mcL, centerConstraints, 0, 0, 1);
                add(centerLayout, mcTF, centerConstraints, 1, 0, 0);
                mcTF.addFocusListener(new FocusListener() {
    				// Get the content of input box
    				@Override
    				public void focusLost(FocusEvent e) {
    					// TODO Auto-generated method stub
    					graphMap.put("doctor", mcTF.getText());
    				}
    				
    				@Override
    				public void focusGained(FocusEvent e) {
    					// TODO Auto-generated method stub
    				}
    			});
            }

            {

                JLabel zpmsL = new JLabel("*Coefficient for #Nurse：");
//                JComboBox zpmsCB =  new JComboBox(new String[] { "", "可分","不可分", "组件" });
                final JTextField nurseTF = new JTextField();
                centerPanel.add(zpmsL);
                centerPanel.add(nurseTF);
                add(centerLayout, zpmsL, centerConstraints, 0, 0, 1);
             
                add(centerLayout, nurseTF, centerConstraints, 1, 0, 0);
                nurseTF.addFocusListener(new FocusListener() {
    				// Get the content of input box
    				@Override
    				public void focusLost(FocusEvent e) {
    					// TODO Auto-generated method stub
    					graphMap.put("nurse", nurseTF.getText());
    				}
    				
    				@Override
    				public void focusGained(FocusEvent e) {
    					// TODO Auto-generated method stub
    				}
    			});
            }

            {

                JLabel yL = new JLabel("*Coefficient for #Beds：");
                final JTextField bedsTF = new JTextField();
                centerPanel.add(yL);
                centerPanel.add(bedsTF);
                add(centerLayout, yL, centerConstraints, 0, 0, 1);
                add(centerLayout, bedsTF, centerConstraints, 1, 0, 0);
                bedsTF.addFocusListener(new FocusListener() {
    				// Get the content of input box
    				@Override
    				public void focusLost(FocusEvent e) {
    					// TODO Auto-generated method stub
    					graphMap.put("bed", bedsTF.getText());
    				}
    				
    				@Override
    				public void focusGained(FocusEvent e) {
    					// TODO Auto-generated method stub
    				}
    			});
            }
            graphMap.put("registration","5");

//            {
//            	
//                JLabel mrzzdmL = new JLabel("*Registration Time：");
////                JComboBox mrzzdmCB = new JComboBox(new String[] { "", "批号",
////                        "批号/序列号", "序列号", "未追踪" });
//                final JTextField registrationTF = new JTextField();
//                centerPanel.add(mrzzdmL);
//                centerPanel.add(registrationTF);
//                add(centerLayout, mrzzdmL, centerConstraints, 0, 0, 2);
//                add(centerLayout, registrationTF, centerConstraints, 1, 0, 0);
//                registrationTF.addFocusListener(new FocusListener() {
//    				// Get the content of input box
//    				@Override
//    				public void focusLost(FocusEvent e) {
//    					// TODO Auto-generated method stub
//    					
//    				}
//    				
//    				@Override
//    				public void focusGained(FocusEvent e) {
//    					// TODO Auto-generated method stub
//    				}
//    			});
//            }

            {

                JLabel mrdwL = new JLabel("*Unit：");
                JComboBox mrdwCB = new JComboBox(new String[] { "", "Hours","Minutes", "Millisecond" });
                mrdwCB.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						graphMap.put("unit", (String) e.getItem());
					}
				});
                centerPanel.add(mrdwL);
                centerPanel.add(mrdwCB);
                add(centerLayout, mrdwL, centerConstraints, 0, 0, 1);
                add(centerLayout, mrdwCB, centerConstraints, 1, 0, 0);
//               
//                JComboBox sjbjCB = new JComboBox(new String[] { "", "是", "否" });
            }

           
//            {
//
//                JLabel smzqmbL = new JLabel("*生命周期模版：");
//                JTextField smzqmbTF = new JTextField("(已生成)");
//                centerPanel.add(smzqmbL);
//                centerPanel.add(smzqmbTF);
//                add(centerLayout, smzqmbL, centerConstraints, 0, 0, 1);
//                add(centerLayout, smzqmbTF, centerConstraints, 1, 0, 0);
//            }
//
//            {
//
//                JLabel wzL = new JLabel("*位置：");
//                JRadioButton zdxzwjjCB = new JRadioButton("自动选择文件夹");
//                JTextField zdxzwjjTF = new JTextField("/测试产品");
//                JLabel tempL = new JLabel();
//                JRadioButton xzwjjCB = new JRadioButton("选择文件夹");
//                JTextField xzwjjTF = new JTextField("");
//                JTree wjjTree = new JTree();
//                centerPanel.add(wzL);
//
//                centerPanel.add(zdxzwjjCB);
//                centerPanel.add(zdxzwjjTF);
//                centerPanel.add(tempL);
//                centerPanel.add(xzwjjCB);
//                centerPanel.add(xzwjjTF);
//                centerPanel.add(wjjTree);
//                add(centerLayout, wzL, centerConstraints, 0, 0, 1);
//                add(centerLayout, zdxzwjjCB, centerConstraints, 0, 0, 1);
//                add(centerLayout, zdxzwjjTF, centerConstraints, 1, 0, 0);
//                add(centerLayout, tempL, centerConstraints, 0, 0, 1);
//                add(centerLayout, xzwjjCB, centerConstraints, 0, 0, 1);
//                add(centerLayout, xzwjjTF, centerConstraints, 1, 0, 1);
//                add(centerLayout, wjjTree, centerConstraints, 0, 0, 0);
//            }

//            {
//
//                JLabel flL = new JLabel("*分类：");
//                JComboBox flCB = new JComboBox(new String[] { "", "B", "L",
//                        "S", "W", "C", "F", "T" });
//                JButton flB = new JButton("+");
//                flB.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        final JLabel flL = new JLabel("*分类：");
//                        final JComboBox flCB = new JComboBox(new String[] { "", "B", "L",
//                                "S", "W", "C", "F", "T" });
//                        final JButton flB = new JButton("-");
//                        flB.addActionListener(new ActionListener() {
//                            public void actionPerformed(ActionEvent e) {
//                                centerPanel.remove(flL);
//                                centerPanel.remove(flCB);
//                                centerPanel.remove(flB);
//                                flList.remove(flCB);
//                                System.out.println("-------------------");
//                                System.out.println("删除组件后，分类可选数量>>>"+flList.size());
//                                for (JComboBox cb : flList) {
//                                    System.out.println(cb.toString());
//                                }
//                                System.out.println("-------------------");
//                                centerPanel.validate();//调用容器的validate()方法
//                                MainMenu.this.validate();
//                                MainMenu.this.repaint();
//                            }
//                        });
//                        centerPanel.add(flL);
//                        centerPanel.add(flCB);
//                        centerPanel.add(flB);
//                        flList.add(flCB);
//                        System.out.println("+++++++++++++++");
//                        System.out.println("增加组件后，分类可选数量>>>"+flList.size());
//                        for (JComboBox cb : flList) {
//                            System.out.println(cb.toString());
//                        }
//                        System.out.println("+++++++++++++++");
//                        add(centerLayout, flL, centerConstraints, 0, 0, 1);
//                        add(centerLayout, flCB, centerConstraints, 1, 0, 1);
//                        add(centerLayout, flB, centerConstraints, 0, 0, 0);
//                        centerPanel.validate();// 调用容器的validate()方法
//                        MainMenu.this.validate();
//                        MainMenu.this.repaint();
//
//                    }
//                });
//                centerPanel.add(flL);
//                centerPanel.add(flCB);
//                centerPanel.add(flB);
//                flList = new ArrayList<JComboBox>();
//                flList.add(flCB);
//                System.out.println("+++++++++++++++");
//                System.out.println("增加组件后，分类可选数量>>>"+flList.size());
//                for (JComboBox cb : flList) {
//                    System.out.println(cb.toString());
//                }
//                System.out.println("+++++++++++++++");
//                add(centerLayout, flL, centerConstraints, 0, 0, 1);
//                add(centerLayout, flCB, centerConstraints, 1, 0, 1);
//                add(centerLayout, flB, centerConstraints, 0, 0, 0);
//            }
//
        }

        /**
         * 下部内容：按钮。
         */
        {
            GridLayout southLayout = new GridLayout(1, 2);
            southPanel.setLayout(southLayout);
            JButton createPdmPartB = new JButton("Create Graph");
           createPdmPartB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				System.out.println("Product:"+ );
				for(String key : graphMap.keySet()) {
					System.out.println("Key:"+key+"Value:"+graphMap.get(key));
				}
				new Simulation_a().drawGraph(graphMap);
			}
		});
          
        
            JButton closeB = new JButton("Close");
            closeB.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    MainMenu.this.dispose();
                   
                }
                
            });

            southPanel.add(createPdmPartB);
            southPanel.add(closeB);
        }
        getContentPane().add(northPanel, BorderLayout.NORTH);
        getContentPane().add(centerScrollPane, BorderLayout.CENTER);
        getContentPane().add(southPanel, BorderLayout.SOUTH);

        /**
         * 设置界面大小和居中
         */
        {
            this.setResizable(true);
            this.setSize(600, 400);
            // 屏幕居中
            int windowWidth = this.getWidth(); // 获得窗口宽
            int windowHeight = this.getHeight(); // 获得窗口高
            Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
            Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
            int screenWidth = screenSize.width; // 获取屏幕的宽
            int screenHeight = screenSize.height; // 获取屏幕的高
            this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight
                    / 2 - windowHeight / 2);
        }

    }
    
   
    /**
     * 
     * @Title: add
     * @Description: TODO(添加控件到容器)
     * @param @param container
     * @param @param c
     * @param @param constraints
     * @param @param x
     * @param @param y
     * @param @param w
     * @param @param h 设定文件
     * @return void 返回类型
     * @throws
     */
    public void add(GridBagLayout layout, Component c,
            GridBagConstraints constraints, int weightx, int weighty,
            int gridwidth) {
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridwidth = gridwidth;
        layout.setConstraints(c, constraints);
    }

    private void initData() {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        MainMenu cpu = new MainMenu(null);
        cpu.setTitle("Patient average waiting time vs. average arrival interval (minutes)");
        cpu.setVisible(true);
    }

}

/*******************************************************************************
 * <B>Revision History</B><BR>
 * [type 'revision' and press Alt + / to insert revision block]<BR>
 * 
 * 
 * 
 * Copyright 2017 AcconSys All rights reserved.
 ******************************************************************************/
