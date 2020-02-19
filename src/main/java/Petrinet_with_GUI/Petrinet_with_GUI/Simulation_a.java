package Petrinet_with_GUI.Petrinet_with_GUI;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.title.LegendTitle;

import GUIFinal2.BTN_Run_Listener;
import jinhu.GraphResult;
import jinhu.LineChart;
import simulation.*;

public class Simulation_a {
	public static Petrinet_a pn;

	int globalClock = 0;
	int step;
	
	int tp3Count = 0; 
	int tp4Count = 0;
	int testBA = 0;
	double inTime = 0.0;
	double outTime = 0.0;
	int inPatient = 0;
	int outPatient = 0;
	ExponentialDistribution exp;
	double arrvrate;

	Petrinet_Place p1, pDOC, pNUR, pBED, pREG;
	Petrinet_Transition t1, tp1, tp2, tp3, tp4, t11, t22, t14;

	public Simulation_a() {
		pn = new Petrinet_a("EDS Simulation");
		exp = new ExponentialDistribution();
		arrvrate = 40;
		// define doctor number
		pDOC = pn.place("Doctor in RA,AM,DP,ED", 3);
		// define new resource nurse number
		pNUR = pn.place("Nurse in BA,GN,EH,IV,EKG", Integer.MAX_VALUE);
		// define bed number
		pBED = pn.place("Bed in BA->DP", Integer.MAX_VALUE);
		// define registration number
		pREG = pn.place("RGster in RG", Integer.MAX_VALUE);

		t1 = pn.transition("t1", arrvrate, true);
		p1 = pn.place("Patient", 0);
		t11 = pn.transition("t11-BA", 18.0, true);
		t14 = pn.transition("t14-ED", 10.0, true);
		t22 = pn.transition("t22-DP", 10.0, true);
		tp1 = pn.transition("tp1-PB", 0, true);
		tp2 = pn.transition("tp2-PB", 0, true);
		tp3 = pn.transition("tp3-PB", 0, true);
		tp4 = pn.transition("tp4-PB", 0, true);
		tp1.setType(false);
		tp2.setType(false);
		tp3.setType(false);
		tp4.setType(false);

		PNModel.constructPetrinet(p1, pDOC, pNUR, pBED, pREG, t1, t11, t14, t22, tp1, tp2, tp3, tp4);
	}

	public void run() throws Exception {
		System.out.println("==========WELCOME TO PETRI NET SIMULATOR=========");
		System.out.println("              constructed petri net              ");
		System.out.println("=============NOW START TO SIMULATE...============");

		final int Option_MSFS = 1;
		final int Option_MSVS = 2;
		final int Option_MSVS_RFF = 3;

		Scanner in = new Scanner(System.in);
		int option;
		
		System.out.println("=============CHOOSE SIMULATION MODE==============");
		System.out.println("1. Multiple Server Fixed Step");
		System.out.println("2. Multiple Server Variable Step");
		System.out.println("3. Multiple Server Variable Step(Resource Form File)");
		option = in.nextInt();
		int patient;
		ArrayList<Petrinet_Transition> fireableTList = new ArrayList<Petrinet_Transition>();
		List<Petrinet_Transition> tl = pn.getTransitionList();
		switch (option) {
		case Option_MSFS:
			patient = getPatient(arrvrate);
			p1.setTokens(patient);
			fireT1();
			step = 1;
			process(fireableTList, tl, true);
			display(patient);
			break;
		case Option_MSVS:
			patient = getPatient(arrvrate);
			System.out.println(patient);
			p1.setTokens(patient);
			fireT1();
			step = 0;
			process(fireableTList, tl, false);
			// pn.displayTokens();
			display(patient);
			break;
		case Option_MSVS_RFF:
			ArrayList<Resource> list = readFromFile();
			ArrayList<Double> iList = new ArrayList<Double>();
			// for(int i = 0; i < 5; i++) {
			// Resource r = list.get(i);
			for (Resource r : list) {
				ArrayList<Double> delayList = new ArrayList<Double>();
				int count = 0;
				arrvrate = r.getArrv();
				System.out.println("arrvrate:" + arrvrate);
				t1.setNewTime(arrvrate);
//				int doc = Integer.MAX_VALUE;
				int doc = r.getDoc();
				pDOC.setTokens(doc);
//				int nur = Integer.MAX_VALUE;
				int nur = r.getNur();
				pNUR.setTokens(nur);
//				int bed = Integer.MAX_VALUE;
				int bed = r.getBed();
				pBED.setTokens(bed);
//				int reg = Integer.MAX_VALUE;
				int reg = r.getReg();
				pREG.setTokens(reg);
				while (count < 1000) {
//					 patient = 10000;
					patient = getPatient(arrvrate);
					p1.setTokens(patient);
					inPatient = 0;
					outPatient = 0;
					globalClock = 0;
					inTime = 0;
					outTime = 0;
					testBA = 0;
					tp3Count =0;
					tp4Count =0;
					fireT1();
					step = 0;
					process(fireableTList, tl, false);
					double delay = (outTime - inTime) / patient / 60;
					delayList.add(delay);
//					display(patient);
					count++;
					System.out.println("Count:"+count+"Delay:"+delay);
				}
				iList.add(getAverage(delayList));
			}
			writeToFile(iList);
			break;
		}
	}

	public void DoctorChange(int DocNumber) {
		pn = new Petrinet_a("EDS Simulation");
		exp = new ExponentialDistribution();
		arrvrate = 5;
		// define doctor number
		pDOC = pn.place("Doctor in RA,AM,DP,ED", 5);
		// define new resource nurse number
		pNUR = pn.place("Nurse in BA,GN,EH,IV,EKG", 1);
		// define bed number
		pBED = pn.place("Bed in BA->DP", 1);
		// define registration number
		pREG = pn.place("RGster in RG", 1);

		t1 = pn.transition("t1", arrvrate, true);
		p1 = pn.place("Patient", 1);
		t11 = pn.transition("t11-BA", 18.0, true);
		t14 = pn.transition("t14-ED", 10.0, true);
		t22 = pn.transition("t22-DP", 10.0, true);
		tp1 = pn.transition("tp1-PB", 0, true);
		tp2 = pn.transition("tp2-PB", 0, true);
		tp3 = pn.transition("tp3-PB", 0, true);
		tp4 = pn.transition("tp4-PB", 0, true);
		tp1.setType(false);
		tp2.setType(false);
		tp3.setType(false);
		tp4.setType(false);

		PNModel.constructPetrinet(p1, pDOC, pNUR, pBED, pREG, t1, t11, t14, t22, tp1, tp2, tp3, tp4);
		int patient;
		ArrayList<Petrinet_Transition> fireableTList = new ArrayList<Petrinet_Transition>();
		List<Petrinet_Transition> tl = pn.getTransitionList();
		patient = getPatient(arrvrate);
		p1.setTokens(patient);
		fireT1();
		step = 1;
		process(fireableTList, tl, true);
		display(patient);
	}
	
	
	public void Guirun(int option) throws Exception {
		System.out.println("==========WELCOME TO PETRI NET SIMULATOR=========");
		System.out.println("              constructed petri net              ");
		System.out.println("=============NOW START TO SIMULATE...============");

		final int Option_MSFS = 1;
		final int Option_MSVS = 2;
		final int Option_MSVS_RFF = 3;

		Scanner in = new Scanner(System.in);
		System.out.println(System.getProperty("用户的当前工作目录:/n"+"user.dir"));
		System.out.println("=============CHOOSE SIMULATION MODE==============");
		System.out.println("1. Multiple Server Fixed Step");
		System.out.println("2. Multiple Server Variable Step");
		System.out.println("3. Multiple Server Variable Step(Resource Form File)");
		int patient;
		ArrayList<Petrinet_Transition> fireableTList = new ArrayList<Petrinet_Transition>();
		List<Petrinet_Transition> tl = pn.getTransitionList();
		switch (option) {
		case Option_MSFS:
			patient = getPatient(arrvrate);
			p1.setTokens(patient);
			fireT1();
			step = 1;
			process(fireableTList, tl, true);
			display2(patient);
			break;
		case Option_MSVS:
			patient = getPatient(arrvrate);
			System.out.println(patient);
			p1.setTokens(patient);
			fireT1();
			step = 0;
			process(fireableTList, tl, false);
			// pn.displayTokens();
			display2(patient);
			break;
		case Option_MSVS_RFF:
			ArrayList<Resource> list = readFromFile();
			ArrayList<Double> iList = new ArrayList<Double>();
			// for(int i = 0; i < 5; i++) {
			// Resource r = list.get(i);
			for (Resource r : list) {
				ArrayList<Double> delayList = new ArrayList<Double>();
				int count = 0;
				arrvrate = r.getArrv();
				t1.setNewTime(arrvrate);
//				int doc = Integer.MAX_VALUE;
				int doc = r.getDoc();
				pDOC.setTokens(doc);
//				int nur = Integer.MAX_VALUE;
				int nur = r.getNur();
				pNUR.setTokens(nur);
//				int bed = Integer.MAX_VALUE;
				int bed = r.getBed();
				pBED.setTokens(bed);
//				int reg = Integer.MAX_VALUE;
				int reg = r.getReg();
				pREG.setTokens(reg);
				while (count < 100) {
					 patient = 10000;
//					patient = getPatient(arrvrate);
					p1.setTokens(patient);
					inPatient = 0;
					outPatient = 0;
					globalClock = 0;
					inTime = 0;
					outTime = 0;
					testBA = 0;
					tp3Count =0;
					tp4Count =0;
					fireT1();
					step = 0;
					process(fireableTList, tl, false);
					double delay = (outTime - inTime) / patient / 60;
					delayList.add(delay);
					System.out.println("Count:"+delay);
//					display2(patient);
					count++;
					
				}
				iList.add(getAverage(delayList));
			}
			writeToFile(iList);
			break;
		}
	}

	
	
	private void process(ArrayList<Petrinet_Transition> fireableTList, List<Petrinet_Transition> tl, boolean flag) {
		while (!(fireableTList = pn.getTransitionsAbleToFire()).isEmpty()) {
			//System.out.println(fireableTList);
			decrementFiringTime(flag, fireableTList);
			/*
			 * deal with common transitions
			 */
			Iterator<Petrinet_Transition> iterator = fireableTList.iterator();
			while (iterator.hasNext()) {
				Petrinet_Transition t = iterator.next();
				//System.out.println(t.getName() + " " + t.getTimeQ().peek().getTime());
				while ((!t.getTimeQ().isEmpty()) && t.getTimeQ().peek().getTime() == 0) {
					t.fire();
					//System.out.println(t.getName() + " fired, clock: " + globalClock);
					// to fire merge t
					for (Petrinet_Transition transition : tl) {
						if (transition.getType() && transition.time() == 0) {
							while (transition.canFire()) {
								transition.fireIncomingArc();
								transition.fire();
								//System.out.println(transition.getName() + " fired, clock: " + globalClock);
							}
						}
					}
					t.getTimeQ().remove();
					specialProcessing(t);
					if (t.getTimeQ().isEmpty()) {
						iterator.remove();
					}
				}
			}
			/*
			 * deal with the transitions with probability
			 */
			firingPreselectTransitions();
		}
	}

	private void fireT1() {
		t1.sampleTimeQ();
		t1.fireIncomingArc();
		t1.fire();
		globalClock += t1.getTimeQ().peek().getTime();
		t1.getTimeQ().remove();
		inPatient++;
		inTime += globalClock;
		//System.out.println("t1 fired, simulation starts at pn.time:" + globalClock);
	}

	private void writeToFile(ArrayList<Double> iList) throws Exception {
		System.out.println("======START TO WRITE TO FILE======");
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("TestResult");
		int rowIndex = 0;
		Row titleRow = sheet.createRow(rowIndex);
		titleRow.createCell(0).setCellValue("TestResult");
		rowIndex++;
		for (Double a : iList) {
			Row row = sheet.createRow(rowIndex);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(a);
			rowIndex++;
		}
		System.out.println("======WRITE FINISHED======");
		FileOutputStream fos = new FileOutputStream("./MultipleServerresult.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("SUCCESSFUL!");
	}

	private int getPatient(double arrvrate) {
		int currtime = 0;
		double temptime = 0;
		int patient = 0;

		ExponentialDistribution exp = new ExponentialDistribution();
		while (currtime < (10 * 60)) {// Initial in 10 hours
			temptime = exp.getExponential(arrvrate);
			patient++;
			currtime += temptime;
		}
		return patient;
	}

	private double getAverage(ArrayList<Double> al) {
		double total = 0;
		for (Double a : al) {
			total += a;
		}
		return total / al.size();
	}

	private ArrayList<Resource> readFromFile() throws Exception {
		ArrayList<Resource> al = new ArrayList<Resource>();
		File excelFile = new File("./5 Resource for MultipleServer.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
		XSSFSheet sheet = wb.getSheetAt(1);
		System.out.println("Sheet Name :" + sheet.getSheetName());
		Row row;
		for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			Cell cell0 = row.getCell(0);
			Cell cell1 = row.getCell(1);
			Cell cell2 = row.getCell(2);
			Cell cell3 = row.getCell(3);
			Cell cell4 = row.getCell(4);
			System.out.println("cell0"+cell0);
			Resource r = new Resource((int) cell0.getNumericCellValue(), (int) cell1.getNumericCellValue(),
					(int) cell2.getNumericCellValue(), (int) cell3.getNumericCellValue(),
					(int) cell4.getNumericCellValue());
			al.add(r);
		}
		
		return al;
	}
	
	private ArrayList<Resource> readParamater(Map<String, String> graphmap) throws Exception {
		ArrayList<Resource> al = new ArrayList<Resource>();
		Row row;
		int arrival_time = 0;
		int arrival_time_step  = Integer.parseInt(graphmap.get("time_Step"));
		String ChangeParamater = graphmap.get("paramater");
		int paramater_num = Integer.parseInt(graphmap.get("paramater_num"));
		while (arrival_time< Integer.parseInt(graphmap.get("Arrival_Interval"))) {
			arrival_time = arrival_time + arrival_time_step;
			int paramater = 0;
			int paramater_step = Integer.parseInt(graphmap.get("paramater_step"));
			
			while (paramater < paramater_num) {
				paramater = paramater + paramater_step;
				Resource r = null;
				int bed = (int) (Math.ceil(Double.parseDouble(graphmap.get("bed")) * paramater));
				int nurse = (int) (Math.ceil(Double.parseDouble(graphmap.get("nurse")) * paramater));
				int doctor = (int) (Math.ceil(Double.parseDouble(graphmap.get("doctor")) * paramater));
				if (ChangeParamater=="doctor") {
//					 r = new Resource((int) arrival_time, paramater,
//								Integer.parseInt(graphmap.get("nurse")),Integer.parseInt(graphmap.get("bed")),
//								Integer.parseInt(graphmap.get("registration")));
//					  only for template test
					 r = new Resource((int) arrival_time, paramater,
							nurse,bed,
							Integer.parseInt(graphmap.get("registration")));
				}else if (ChangeParamater=="nurse") {
					 r = new Resource((int) arrival_time, doctor,paramater,bed,
							Integer.parseInt(graphmap.get("registration")));
				}else if (ChangeParamater=="bed") {
					 r = new Resource((int) arrival_time, doctor,nurse,paramater,
							Integer.parseInt(graphmap.get("registration")));
				}else if (ChangeParamater=="registration") {
					 r = new Resource((int) arrival_time, paramater * Integer.parseInt(graphmap.get("doctor")),
							 paramater * Integer.parseInt(graphmap.get("nurse")),paramater * Integer.parseInt(graphmap.get("bed")),
							 paramater);
				}
//				System.out.println(r.toString());
				
				al.add(r);
			}
			
			
		}
		return al;
	}
	

	private void decrementFiringTime(boolean flag, ArrayList<Petrinet_Transition> fireableTList) {
		if (flag) {
			step = 1;
		} else {
			step = getStep(fireableTList);
		}
		globalClock += step;

		//System.out.println("clock: " + globalClock);
		for (Petrinet_Transition t : fireableTList) {
			t.timeQDecrease(step);
		}
	}

	private int getStep(ArrayList<Petrinet_Transition> fireableTList) {
		step = fireableTList.get(0).getTimeQ().peek().getTime();
		int temp;
		for (Petrinet_Transition t : fireableTList) {
			temp = t.getTimeQ().peek().getTime();
			if (temp < step) {
				step = temp;
			}
		}
		return step;
	}

	/*
	 * deal with the transitions with probability
	 */
	private void firingPreselectTransitions() {
		Random rd = new Random();
		double p;
		while (tp1.canFire() || tp2.canFire()) {
			p = rd.nextDouble();
			// System.out.println(p);
			if (p <= 0.7) {
				tp1.fireIncomingArc();
				tp1.fire();
				//System.out.println("tp1 fires");
			} else {
				tp2.fireIncomingArc();
				tp2.fire();
				//System.out.println("tp2 fires");
			}
		}
		while (tp3.canFire() || tp4.canFire()) {
			p = rd.nextDouble();
			// System.out.println(p);
			if (p <= 0.7) {
				tp3.fireIncomingArc();
				tp3.fire();
				tp3Count++;
				//System.out.println("tp3 fires");
			} else {
				tp4.fireIncomingArc();
				tp4.fire();
				tp4Count++;
				//System.out.println("tp4 fires");
			}
		}
	}

	private void specialProcessing(Petrinet_Transition t) {
		if (t.getName().equals(t11.getName())) {
			testBA++;
		}
		if (t.getName().equals(t1.getName())) {
			inTime += globalClock;
			inPatient++;
			//System.out.println("A patient goes in at " + globalClock + ", now have " + inPatient + " patients");
		}
		if (t.getName().equals(t22.getName()) || t.getName().equals(t14.getName())) {
			outTime += globalClock;
			outPatient++;
			//System.out.println("A patient goes out at " + globalClock + ", now " + outPatient + " patients go out");
		}
	}

	private void display(int patient) {
		System.out.println("=============SIMULATION FINISHED============\n");
		System.out.println("In time:" + exp.format(inTime) + " , out time:" + exp.format(outTime));
		System.out.println("In time:" + exp.format(inTime) + " , out time:" + exp.format(outTime)/patient/60);
		// System.out.println("Average delay time: " + exp.format((outTime - inTime) /
		// patient / 60) + " hours "
		// + exp.format((outTime - inTime) / patient % 60) + " mins");
		System.out.println("Average delay time: " + (int) ((outTime - inTime) / patient) / 60 + " hours "
				+ (int) ((outTime - inTime) / patient) % 60 + " mins");
		System.out.println("In p:" + inPatient + " , out p:" + outPatient);
		System.out.println("BA times:" + testBA);
		System.out.println("tp3 times: " + tp3Count);
		System.out.println("tp4 times: " + tp4Count);
		System.out.println("\n===============SUMMARY======================");
	}
	
	private void display2(int patient) {
		
		
		BTN_Run_Listener.frame1.setVisible(false);
		JFrame frame1 = new JFrame();
		frame1.setTitle("SIMULATION FINISHED");
		frame1.setSize(new Dimension(450, 250));
		frame1.setDefaultCloseOperation(2);
		frame1.setLocationRelativeTo(null);
		JPanel jPanel=new JPanel();
		
		frame1.add(jPanel);
		
		JLabel jLabel1=new JLabel("In time:" + exp.format(inTime) + " , out time:" + exp.format(outTime)+"\n");
		JLabel jLabel2=new JLabel("Average delay time: " + (int) ((outTime - inTime) / patient) / 60 + " hours "
				+ (int) ((outTime - inTime) / patient) % 60 + " mins"+"\n");
		JLabel jLabel3=new JLabel("In p:" + inPatient + " , out p:" + outPatient+"\n");
		jPanel.setLayout(null);
		jLabel1.setBounds(20, 30, 400, 20);
		jLabel2.setBounds(20, 80, 400, 20);
		jLabel3.setBounds(20, 130, 400, 20);
		jPanel.add(jLabel1);
		jPanel.add(jLabel2);
		jPanel.add(jLabel3);
		
		frame1.setVisible(true);
	}

	public void button_run() throws Exception {
		new Simulation_a().run();
	}
	
	
	public void drawGraph(final Map graph) {
		int patient;
		ArrayList<Petrinet_Transition> fireableTList = new ArrayList<Petrinet_Transition>();
		List<Petrinet_Transition> tl = pn.getTransitionList();
		
		
		ArrayList<Resource> list = null;
		try {
			list = readParamater(graph);
			System.out.println("drawGraph Print---------------------");
			for(Resource resource : list) {
				
				System.out.println(resource.toString());
			}
			System.out.println("drawGraph Print---------------------");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final ArrayList<GraphResult> gresultList = new ArrayList<GraphResult>();
		// for(int i = 0; i < 5; i++) {
		// Resource r = list.get(i);
		for (Resource r : list) {
			ArrayList<Double> delayList = new ArrayList<Double>();
			int count = 0;
			arrvrate = r.getArrv();
			t1.setNewTime(arrvrate);
//			int doc = Integer.MAX_VALUE;
			int doc = r.getDoc();
			pDOC.setTokens(doc);
//			int nur = Integer.MAX_VALUE;
			int nur = r.getNur();
			pNUR.setTokens(nur);
//			int bed = Integer.MAX_VALUE;
			int bed = r.getBed();
			pBED.setTokens(bed);
//			int reg = Integer.MAX_VALUE;
			int reg = r.getReg();
			pREG.setTokens(reg);
			while (count < 100) {
				// patient = 10000;
				patient = getPatient(arrvrate);
				p1.setTokens(patient);
				inPatient = 0;
				outPatient = 0;
				globalClock = 0;
				inTime = 0;
				outTime = 0;
				testBA = 0;
				tp3Count =0;
				tp4Count =0;
				fireT1();
				step = 0;
				process(fireableTList, tl, false);
				
				double delay = (outTime - inTime) / patient / 60;
				
				delayList.add(delay);
//				display2(patient);
				count++;
//				System.out.println(delay);
			}
			
			r.setDelay(getAverage(delayList));
			String Paramater = (String) graph.get("paramater");
			if (Paramater.equals("doctor")) {
				gresultList.add(new GraphResult(getAverage(delayList), r.getDoc(), r.getArrv()));
			} else if (Paramater.equals("nurse")) {
				gresultList.add(new GraphResult(getAverage(delayList), r.getNur(), r.getArrv()));
			} else {
				gresultList.add(new GraphResult(getAverage(delayList), r.getBed(), r.getArrv()));
			}
			
//			iList.add(getAverage(delayList));
		}
		final ArrayList<Resource> rList = list;
		 final JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1024, 420);
	        frame.setLocationRelativeTo(null);
	        frame.setTitle((String) graph.get("Title"));
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                // 创建图形
	                ChartPanel chartPanel = new LineChart().createChart(Integer.parseInt((String) graph.get("Arrival_Interval")),Integer.parseInt((String) graph.get("time_Step")),gresultList,graph);
	                frame.getContentPane().add(chartPanel);
	                
	               
	                frame.setVisible(true);
	            }
	        });
		// Call the draw graph function
//		writeToFile(iList);
	}
	
	public static void main(String[] args) throws Exception {
//		for(int i=0;i<100;i++) {
//			new Simulation_a().DoctorChange(i);
//		}
		new Simulation_a().run();
	}
}
