package Petrinet_with_GUI.Petrinet_with_GUI;

public class SampleTime {
	private int time;
	
	public SampleTime(int sampleTime) {
		time = sampleTime;
	}
	
	public void timeDecrease(int step) {
		time -= step;
	}
	
	public int getTime() {
		return time;
	}
}
