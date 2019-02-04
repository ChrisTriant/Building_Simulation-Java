

public class Visitor extends Human {
	private int priority;
	private boolean escorted=false;
	public int getpriority() {
		return priority;
	}
	public void setpriority(int pr) {
		priority =pr;
	}
	
	public Visitor(int fl,int of) {
		super(fl,of);
		System.out.println( "visitor created with floor number "+fl+", office number "+of);
 		priority=0;
		isServed=false;	
	}
	
	public void gotEscorted() {
		escorted=true;
	}
	
	public boolean isEscorted() {
		return escorted;
	}
}
