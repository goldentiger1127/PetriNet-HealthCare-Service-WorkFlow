package Petrinet_with_GUI.Petrinet_with_GUI;

public class PNModel {
	public static void constructPetrinet(Petrinet_Place p1, Petrinet_Place pDOC, Petrinet_Place pNUR, Petrinet_Place pBED, Petrinet_Place pREG, 
								Petrinet_Transition t1, Petrinet_Transition t11, Petrinet_Transition t14, Petrinet_Transition t22,
								Petrinet_Transition tp1, Petrinet_Transition tp2, Petrinet_Transition tp3, Petrinet_Transition tp4) {
		Petrinet_Place p2 = Simulation_a.pn.place("p2");
		Petrinet_Place p3 = Simulation_a.pn.place("p3");
		Petrinet_Place p4 = Simulation_a.pn.place("p4");
		Petrinet_Place p5 = Simulation_a.pn.place("p5");
		Petrinet_Place p6 = Simulation_a.pn.place("p6");
		Petrinet_Place p7 = Simulation_a.pn.place("p7");
		Petrinet_Place p8 = Simulation_a.pn.place("p8");
		Petrinet_Place p9 = Simulation_a.pn.place("p9");
		Petrinet_Place p10 = Simulation_a.pn.place("p10");
		Petrinet_Place p11 = Simulation_a.pn.place("p11");
		Petrinet_Place p12 = Simulation_a.pn.place("p12");
		Petrinet_Place p13 = Simulation_a.pn.place("p13");
		Petrinet_Place p14 = Simulation_a.pn.place("p14");
		Petrinet_Place p15 = Simulation_a.pn.place("p15");
		Petrinet_Place p16 = Simulation_a.pn.place("p16");
		Petrinet_Place p17 = Simulation_a.pn.place("p17");
		Petrinet_Place p18 = Simulation_a.pn.place("p18");
		Petrinet_Place p19 = Simulation_a.pn.place("p19");
		Petrinet_Place p20 = Simulation_a.pn.place("p20");
		Petrinet_Place p21 = Simulation_a.pn.place("p21");
		Petrinet_Place p22 = Simulation_a.pn.place("p22");
		Petrinet_Place p23 = Simulation_a.pn.place("p23");
		Petrinet_Place p24 = Simulation_a.pn.place("p24");
		Petrinet_Place p25 = Simulation_a.pn.place("p25");
		Petrinet_Place p26 = Simulation_a.pn.place("p26");
		Petrinet_Place p27 = Simulation_a.pn.place("p27");
		Petrinet_Place p28 = Simulation_a.pn.place("p28");
		Petrinet_Place p29 = Simulation_a.pn.place("p29");
		Petrinet_Place pp1 = Simulation_a.pn.place("pp1");
		Petrinet_Place pp2 = Simulation_a.pn.place("pp2");
		Petrinet_Place pp3 = Simulation_a.pn.place("pp3");
		Petrinet_Place pp4 = Simulation_a.pn.place("pp4");

		Petrinet_Transition t2 = Simulation_a.pn.transition("t2-RG", 10.0, true);
		Petrinet_Transition t3 = Simulation_a.pn.transition("t3-TR", 17.0, true);
		Petrinet_Transition t4 = Simulation_a.pn.transition("t4-TV", 10.0, true);
		Petrinet_Transition t5 = Simulation_a.pn.transition("t5-IA", 15.0, true);
		Petrinet_Transition t6 = Simulation_a.pn.transition("t6-CT", 25.0, true);
		Petrinet_Transition t7 = Simulation_a.pn.transition("t7-CXR", 25.0, true);
		Petrinet_Transition t8 = Simulation_a.pn.transition("t8-TU", 8.0, true);
		Petrinet_Transition t9 = Simulation_a.pn.transition("t9-UR", 30.0, true);
		Petrinet_Transition t10 = Simulation_a.pn.transition("t10-RA", 20.0, true);
		Petrinet_Transition t12 = Simulation_a.pn.transition("t12-GN", 12.0, true);
		Petrinet_Transition t13 = Simulation_a.pn.transition("t13-EH", 28.5, true);
		Petrinet_Transition t15 = Simulation_a.pn.transition("t15-FORK", 0.0, true);
		Petrinet_Transition t16 = Simulation_a.pn.transition("t16-DB", 28.0, true);
		Petrinet_Transition t17 = Simulation_a.pn.transition("t17-BT", 50.0, 70.0, false);
		Petrinet_Transition t18 = Simulation_a.pn.transition("t18-EKG", 12.0, true);
		Petrinet_Transition t19 = Simulation_a.pn.transition("t19-IS", 1.0, 5.0, false);
		Petrinet_Transition t20 = Simulation_a.pn.transition("t20-IV", 140.0, 160.0, false);
		Petrinet_Transition t21 = Simulation_a.pn.transition("t21-IE", 1.0, 5.0, false);
		Petrinet_Transition t23 = Simulation_a.pn.transition("t23-AM", 20.0, true);
		Petrinet_Transition t24 = Simulation_a.pn.transition("t24-AD", 32.0, true);

		Simulation_a.pn.arc("a1", p1, t1);
		Simulation_a.pn.arc("a2", t1, p2);
		Simulation_a.pn.arc("a3", p2, t2);
		Simulation_a.pn.arc("a4", t2, p3);
		Simulation_a.pn.arc("a5", p3, t3);
		Simulation_a.pn.arc("a6", t3, p4);
		Simulation_a.pn.arc("a7", p4, t4);
		Simulation_a.pn.arc("a8", t4, p5);
		Simulation_a.pn.arc("a9", p5, t5);
		Simulation_a.pn.arc("a10", t5, p6);
		Simulation_a.pn.arc("a11", p6, t6);
		Simulation_a.pn.arc("a12", t6, p7);
		Simulation_a.pn.arc("a13", p7, t10);
		Simulation_a.pn.arc("a14", t5, p8);
		Simulation_a.pn.arc("a15", p8, t7);
		Simulation_a.pn.arc("a16", t7, p9);
		Simulation_a.pn.arc("a17", p9, t10);
		Simulation_a.pn.arc("a18", t5, p10);
		Simulation_a.pn.arc("a19", p10, t8);
		Simulation_a.pn.arc("a20", t8, p11);
		Simulation_a.pn.arc("a21", p11, t9);
		Simulation_a.pn.arc("a22", t9, p12);
		Simulation_a.pn.arc("a23", p12, t10);
		Simulation_a.pn.arc("a24", t5, p13);
		Simulation_a.pn.arc("a25", p13, t10);
		Simulation_a.pn.arc("a26", p13, t6);
		Simulation_a.pn.arc("a27", t6, p13);
		Simulation_a.pn.arc("a28", p13, t7);
		Simulation_a.pn.arc("a29", t7, p13);
		Simulation_a.pn.arc("a30", p13, t8);
		Simulation_a.pn.arc("a31", t8, p13);
		Simulation_a.pn.arc("a32", t10, p14);
		Simulation_a.pn.arc("a33", p14, tp1);
		Simulation_a.pn.arc("a34", tp1, pp1);
		Simulation_a.pn.arc("a35", pp1, t14);
		Simulation_a.pn.arc("a36", t14, p29);
		Simulation_a.pn.arc("a37", p14, tp2);
		Simulation_a.pn.arc("a38", tp2, pp2);
		Simulation_a.pn.arc("a39", pp2, t11);
		Simulation_a.pn.arc("a40", t11, p15);
		Simulation_a.pn.arc("a41", p15, t12);
		Simulation_a.pn.arc("a42", t12, p16);
		Simulation_a.pn.arc("a43", p16, t13);
		Simulation_a.pn.arc("a44", t13, p17);
		Simulation_a.pn.arc("a45", p17, t15);
		Simulation_a.pn.arc("a46", t15, p18);
		Simulation_a.pn.arc("a47", p18, t16);
		Simulation_a.pn.arc("a48", t16, p19);
		Simulation_a.pn.arc("a49", p19, t17);
		Simulation_a.pn.arc("a50", t17, p20);
		Simulation_a.pn.arc("a51", p20, t23);
		Simulation_a.pn.arc("a52", t15, p21);
		Simulation_a.pn.arc("a53", p21, t18);
		Simulation_a.pn.arc("a54", t18, p22);
		Simulation_a.pn.arc("a55", p22, t23);
		Simulation_a.pn.arc("a56", t15, p23);
		Simulation_a.pn.arc("a57", p23, t19);
		Simulation_a.pn.arc("a58", t19, p24);
		Simulation_a.pn.arc("a59", p24, t20);
		Simulation_a.pn.arc("a60", t20, p25);
		Simulation_a.pn.arc("a61", p25, t21);
		Simulation_a.pn.arc("a62", t21, p26);
		Simulation_a.pn.arc("a63", p26, t23);
		Simulation_a.pn.arc("a64", t15, p27);
		Simulation_a.pn.arc("a65", p27, t23);
		Simulation_a.pn.arc("a66", p27, t16);
		Simulation_a.pn.arc("a67", t16, p27);
		Simulation_a.pn.arc("a68", p27, t16);
		Simulation_a.pn.arc("a69", t18, p27);
		Simulation_a.pn.arc("a70", p27, t19);
		Simulation_a.pn.arc("a71", t19, p27);
		Simulation_a.pn.arc("a72", p27, t21);
		Simulation_a.pn.arc("a73", t21, p27);
		Simulation_a.pn.arc("a74", t23, p28);
		Simulation_a.pn.arc("a75", p28, tp4);
		Simulation_a.pn.arc("a76", tp4, pp4);
		Simulation_a.pn.arc("a77", pp4, t24);
		Simulation_a.pn.arc("a78", t24, p17);
		Simulation_a.pn.arc("a79", p28, tp3);
		Simulation_a.pn.arc("a80", tp3, pp3);
		Simulation_a.pn.arc("a81", pp3, t22);
		Simulation_a.pn.arc("a82", t22, p29);
		
		// connection to register
		Simulation_a.pn.arc("a201", pREG, t2);
		Simulation_a.pn.arc("a202", t2, pREG);
		
		// connection to bed
		Simulation_a.pn.arc("a203", t22, pBED);
		Simulation_a.pn.arc("a204", pBED, t11);
		
		// connection to doctor
		Simulation_a.pn.arc("a205", t10, pDOC);
		Simulation_a.pn.arc("a206", t14, pDOC);
		Simulation_a.pn.arc("a207", t21, pDOC);
		Simulation_a.pn.arc("a208", t22, pDOC);
			// multiple server
		Simulation_a.pn.arc("a209", pDOC, t10);
		Simulation_a.pn.arc("a210", pDOC, t14);
		Simulation_a.pn.arc("a211", pDOC, t21);
		Simulation_a.pn.arc("a212", pDOC, t22);
		
		// connection to nurse
		Simulation_a.pn.arc("a213", t11, pNUR);
		Simulation_a.pn.arc("a214", t12, pNUR);
		Simulation_a.pn.arc("a215", t13, pNUR);
		Simulation_a.pn.arc("a216", t16, pNUR);
		Simulation_a.pn.arc("a217", t18, pNUR);
		Simulation_a.pn.arc("a218", t19, pNUR);
		Simulation_a.pn.arc("a219", t21, pNUR);
		Simulation_a.pn.arc("a220", t24, pNUR);
		Simulation_a.pn.arc("a221", pNUR, t11);
		Simulation_a.pn.arc("a222", pNUR, t12);
		Simulation_a.pn.arc("a223", pNUR, t13);
		Simulation_a.pn.arc("a224", pNUR, t16);
		Simulation_a.pn.arc("a225", pNUR, t18);
		Simulation_a.pn.arc("a226", pNUR, t19);
		Simulation_a.pn.arc("a227", pNUR, t21);
		Simulation_a.pn.arc("a228", pNUR, t24);
	}
}