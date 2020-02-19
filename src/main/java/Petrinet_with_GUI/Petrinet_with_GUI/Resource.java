package Petrinet_with_GUI.Petrinet_with_GUI;

public class Resource {
	private int arrvrate;
	private int doctor;
	private int nurse;
	private int registration;
	private int bed;
	private double delay;
	
	public Resource(int arrvrate, int doctor, int nurse, int bed, int registration) {
		this.arrvrate = arrvrate;
		this.doctor = doctor;
		this.nurse = nurse;
		this.bed = bed;
		this.registration = registration;
	}
	
	
	
	public double getDelay() {
		return delay;
	}



	public void setDelay(double delay) {
		this.delay = delay;
	}



	public int getArrv() {
		return arrvrate;
	}
	
	public int getDoc() {
		return doctor;
	}
	
	public int getNur() {
		return nurse;
	}
	
	public int getBed() {
		return bed;
	}
	
	public int getReg() {
		return registration;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("   Arrival Time:").append(arrvrate).append("   Doctor:").append(doctor).
		append("   Nurse").append(nurse).append("   bed").append(bed).append("   registration").append(registration);
		
		return stringBuffer.toString();
	}
}

