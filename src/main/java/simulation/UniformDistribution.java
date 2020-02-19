package simulation;

public class UniformDistribution {
	public int getUniform(double time1, double time2) {
		int time = (int)((time2 - time1) * Math.random() + time1);
		return time;
	}
}
