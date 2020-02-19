package jinhu;

import java.util.Date;

public class PetrinetTime {
	
	
	private int tick;
	
	private long startTime;
	
	public PetrinetTime(int tick) {
		this.tick = tick;
		startTime = new Date().getTime();
	}
	
	public long getTime() {
		return (new Date().getTime() - startTime)/tick;
	}
	
}
