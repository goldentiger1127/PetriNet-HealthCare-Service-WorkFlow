package Petrinet_with_GUI.Petrinet_with_GUI;

import simulation.ExponentialDistribution;
import simulation.UniformDistribution;

import java.util.*;

public class Petrinet_Transition extends PetrinetObject {

//	private Resource resource;

	//type true for common transition, false for probability transition
	private boolean type;
	private double time;
	private double time1;
	private double time2;
	//true for exponential distribution, false for uniform distribution
	private boolean distribution;
	private int sampleTime;
	PriorityQueue<SampleTime> tQ;
	Comparator comparator = new SampleTimeComparator();
	
	protected Petrinet_Transition(String name,double time, boolean distribution) {
		super(name);
		type = true;
		this.time = time;
		//label = false;
		tQ = new PriorityQueue<SampleTime>(comparator);
		// resource = new Resource("null", 0);
		this.distribution = distribution;
	}
	
	protected Petrinet_Transition(String name, double time1, double time2, boolean distribution) {
		super(name);
		type = true;
		this.time1 = time1;
		this.time2 = time2;
		time = (time1+time2)/2;
		tQ = new PriorityQueue<SampleTime>(comparator);
		this.distribution = distribution;
	}
	
	public boolean getDistribution() {
		return distribution;
	}

	public int sampleTime(){
		//every time collect time based on exponential distribution
		ExponentialDistribution exp = new ExponentialDistribution();
		sampleTime = exp.getExponential(time) + 1;
		return sampleTime;
	}

	//for multiple server: one transition has a priority queue to save the
	//the new sample time when a new token comes before the previous one
	//deal with the priority queue
	public void sampleTimeQ() {
		int newTime = sampleTime();
		SampleTime st = new SampleTime(newTime);
		tQ.add(st);
	}
	
	public int uniformSampleTime() {
		UniformDistribution uni = new UniformDistribution();
		sampleTime = uni.getUniform(time1, time2);
		//System.out.println("time: " + sampleTime);
		return sampleTime;
	}
	
	public void addTimeQ(int time) {
		SampleTime st = new SampleTime(time);
		tQ.add(st);
	}
	
	
	public PriorityQueue<SampleTime> getTimeQ(){
		return tQ;
	}
	
	public double time() {
		return time;
	}
	
	public int getTime(){
		return sampleTime;
	}
	
	public boolean getType() {
		return type;
	}
	
	public void setTimeQ() {
		SampleTime st = new SampleTime(0);
		tQ.add(st);
	}
	
	public void setNewTime(double time) {
		this.time = time;
	}
	
	public void setTime(int sampleTime) {
		this.sampleTime = sampleTime;
	}
	
	public void setType(boolean type) {
		this.type = type;
	}
	
	public void timeDecrease(int dTime)
	{
		sampleTime -= dTime;
	}
	
	public void timeQDecrease(int dTime) {
		Iterator<SampleTime> iterator = tQ.iterator();
		while(iterator.hasNext()) {
		SampleTime a = iterator.next();
			a.timeDecrease(dTime);
		}
	}
	
	// Use if need resource
	// protected Transition(String tname, String rname, double res) {
	// super(tname);
	// resource = new Resource(rname, res);
	// }

	private List<Petrinet_Arc> incoming = new ArrayList<Petrinet_Arc>();
	private List<Petrinet_Arc> outgoing = new ArrayList<Petrinet_Arc>();

	// public Resource getResource() {
	// return resource;
	// }
	//
	// public void setResource(Resource resource) {
	// this.resource = resource;
	// }

	/**
	 * @return Can the transition fire?
	 */
	public boolean canFire() {
		boolean canFire = true;
		canFire = !this.isNotConnected();// can fire: both of input and output
											// are not empty
		for (Petrinet_Arc arc : incoming) {
			canFire = canFire & arc.canFire();
			// canFire = canFire & arc.canFire() & resource.isAvaliable();
		}
		for (Petrinet_Arc arc : outgoing) {
			canFire = canFire & arc.canFire();
		}
		return canFire;
	}

	public void fireIncomingArc() {
		if(canFire())
		{
			for (Petrinet_Arc arc : incoming) {
				arc.fire();
			}
		}
	}
	
	/**
	 * Transition Is to fire
	 */
	public void fire() {
		/*
		for (Arc arc : incoming) {
			arc.fire();
			// occupies resource
			// if (resource.getName() != "null") {
			// resource.setValue(resource.getValue() - 1);
			// System.out.println(super.getName() + "fired,a new token
			// arrives,resource consumed."
			// + "current resource:" + resource.getValue());
		}
		*/
		//System.out.println("bbb");
		for (Petrinet_Arc arc : outgoing) {
			arc.fire();
			// release resource
			// if (resource.getName() != "null") {
			// resource.setValue(resource.getValue() + 1);
			// System.out.println(
			// super.getName() + "fired,resource released." + "current
			// resource:" + resource.getValue());
			// }

		}
	}

	/**
	 * @param arc
	 *            Add an incoming edge
	 */
	public void addIncoming(Petrinet_Arc arc) {
		this.incoming.add(arc);
	}

	/**
	 * @param arc
	 *            Out edge
	 */
	public void addOutgoing(Petrinet_Arc arc) {
		this.outgoing.add(arc);
	}

	/**
	 * @return Is the transition associated with no edge?
	 */
	public boolean isNotConnected() {
		return incoming.isEmpty() && outgoing.isEmpty();
	}

	@Override
	public String toString() {
		return super.toString() + (isNotConnected() ? " IS NOT CONNECTED" : "") + (canFire() ? " READY TO FIRE" : "");
	}
	
}
