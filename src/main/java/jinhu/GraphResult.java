package jinhu;

public class GraphResult {
	private String delay;
	
	private String ParamaterName;
	
	private String Arrival_Time;
	
	public GraphResult(Double delay,int ParamaterName,int Arrival_Time) {
		// TODO Auto-generated constructor stub
		this.delay = String.valueOf(delay);
		this.ParamaterName = String.valueOf(ParamaterName);
		this.Arrival_Time = String.valueOf(Arrival_Time);
	}
	
	
	public String getDelay() {
		return delay;
	}

	public void setDelay(Double delay) {
		this.delay = String.valueOf(delay);
	}

	public String getParamaterName() {
		return ParamaterName;
	}

	public void setParamaterName(String paramaterName) {
		this.ParamaterName = String.valueOf(ParamaterName);
	}

	public String getArrival_Time() {
		return Arrival_Time;
	}

	public void setArrival_Time(String arrival_Time) {
		this.Arrival_Time = String.valueOf(Arrival_Time);
	}

	@Override
	public String toString() {
		return "GraphResult [delay=" + delay + ", ParamaterName=" + ParamaterName + ", Arrival_Time=" + Arrival_Time
				+ "]";
	}

	
}
