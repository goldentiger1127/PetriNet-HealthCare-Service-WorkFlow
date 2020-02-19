package jinhu;

public class Apple {

	
	
	private int size;
	
	public Apple(int size) {
		this.size =size;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	public void setApple(Apple apple) {
		System.out.print("father:"+this.size+"son:"+apple.size);
	}
	
	
}
