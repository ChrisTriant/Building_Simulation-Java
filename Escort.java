
public class Escort extends Human {
	
	Escort(int fl, int of) {
		super(fl, of);
		System.out.println("escort created");
		
	}

	private Human v;
	

	public Human getV() {
		return v;
	}

	public void setV(Human v) {
		this.v =  v;
		System.out.println("escort attached to visitor with floor number "+ v.getfloor()+" and office number "+v.getoffice());
	}
}
