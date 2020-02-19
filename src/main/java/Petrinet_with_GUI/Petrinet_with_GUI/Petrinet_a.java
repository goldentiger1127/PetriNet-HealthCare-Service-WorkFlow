package Petrinet_with_GUI.Petrinet_with_GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Petrinet_a extends PetrinetObject {

	private static final String nl = "\n";
	private List<Petrinet_Place> places = new ArrayList<Petrinet_Place>();
	private List<Petrinet_Transition> transitions = new ArrayList<Petrinet_Transition>();
	private List<Petrinet_Arc> arcs = new ArrayList<Petrinet_Arc>();
	ArrayList<Petrinet_Transition> tList = new ArrayList<Petrinet_Transition>();
	Random rd = new Random();

	private double timeConsumed;// used to analyze the total time consumed
	private int EDtimes;
	private int ADtimes;

	public Petrinet_a(String name) {
		super(name);
		timeConsumed = 0;
		EDtimes = 0;
		ADtimes = 0;
	}
	
	public void displayTokens() {
		for(Petrinet_Place p : places) {
			System.out.println(p.getName() + " " + p.getTokens());
		}
	}

	// add petri net elements
	public void add(PetrinetObject o) {
		if (o instanceof Petrinet_Arc) {
			arcs.add((Petrinet_Arc) o);
		} else if (o instanceof Petrinet_Place) {
			places.add((Petrinet_Place) o);
		} else if (o instanceof Petrinet_Transition) {
			transitions.add((Petrinet_Transition) o);
		}
	}
	
	public List<Petrinet_Transition> getTransitionList(){
		return transitions;
	}
	
	public void addT1(Petrinet_Transition t1) {
		tList.add(t1);
	}
	
	//get fireable transitions arraylist
	public ArrayList<Petrinet_Transition> getTransitionsAbleToFire() {
		Petrinet_Transition[] tArr = new Petrinet_Transition[200];
		int count = 0;
		boolean flag = false;
		for (Petrinet_Transition t : transitions) {
			if (t.canFire() && t.getType() && t.time() != 0) {
				tArr[count] = t;
				count++;
			}
		}
		while (!flag) {
			for (int i = 0; i < count; i++) {
				if (tArr[i] != null && tArr[i].canFire()) {
					if(tArr[i].getDistribution()) {
						tArr[i].sampleTime();
					}
					else
					{
						tArr[i].uniformSampleTime();
					}
				} else {
					tArr[i] = null;
				}
			}
			Petrinet_Transition tempT = new Petrinet_Transition("tempT", 0, true);
			tempT.setTime(Integer.MAX_VALUE);
			for (int i = 0; i < count; i++) {
				if (tArr[i] != null && tArr[i].getTime() <= tempT.getTime()) {
					tempT = tArr[i];
				}
			}
			if (tempT.getTime() != Integer.MAX_VALUE) {
				tempT.fireIncomingArc();
				tempT.addTimeQ(tempT.getTime());
				if (!tList.contains(tempT)) {
					tList.add(tempT);
				}
			}
			else {
				flag = true;
			}
		}
		return tList;
	}

	public Petrinet_Transition transition(String name, double time, boolean  distribution) {
		Petrinet_Transition t = new Petrinet_Transition(name, time, distribution);
		transitions.add(t);
		// System.out.println(t.getName()+" added, current size:"+transitions.size());
		return t;
	}
	
	public Petrinet_Transition transition(String name, double time1, double time2, boolean distribution) {
		Petrinet_Transition t = new Petrinet_Transition(name, time1, time2, distribution);
		transitions.add(t);
		// System.out.println(t.getName()+" added, current size:"+transitions.size());
		return t;
	}

	// public Transition transition(String name,String rname,double res){
	// Transition t = new Transition(name);
	// transitions.add(t);
	// return t;
	// }

	public Petrinet_Place place(String name) {
		Petrinet_Place p = new Petrinet_Place(name);
		places.add(p);
		return p;
	}

	public Petrinet_Place place(String name, int initial) {
		Petrinet_Place p = new Petrinet_Place(name, initial);
		places.add(p);
		return p;
	}

	public Petrinet_Arc arc(String name, Petrinet_Place p, Petrinet_Transition t) {
		Petrinet_Arc arc = new Petrinet_Arc(name, p, t);
		arcs.add(arc);
		return arc;
	}

	public Petrinet_Arc arc(String name, Petrinet_Transition t, Petrinet_Place p) {
		Petrinet_Arc arc = new Petrinet_Arc(name, t, p);
		arcs.add(arc);
		return arc;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Petrinet ");
		sb.append(super.toString()).append(nl);
		sb.append("---Transitions---").append(nl);
		for (Petrinet_Transition t : transitions) {
			sb.append(t).append(nl);
		}
		sb.append("---Places---").append(nl);
		for (Petrinet_Place p : places) {
			sb.append(p).append(nl);
		}
		return sb.toString();
	}

	public List<Petrinet_Place> getPlaces() {
		return places;
	}

	public List<Petrinet_Transition> getTransitions() {
		return transitions;
	}

	public List<Petrinet_Arc> getArcs() {
		return arcs;
	}

	public void addTime(double currentTime) {
		timeConsumed += currentTime;
	}

	public double getTime() {
		return timeConsumed;
	}

	public int getEDtimes() {
		return EDtimes;
	}

	public void addEDtimes() {
		EDtimes++;
	}

	public int getADtimes() {
		return ADtimes;
	}

	public void addADtimes() {
		ADtimes++;
	}
}
