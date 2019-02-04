

public class Human {
	private	int fl_num,of_num;
	boolean isServed;
	
	Human(int fl,int of){
		fl_num=fl;
		of_num=of;
	}
	
	public 	void gotserved() {
		isServed=true;
	}
	public boolean getisServed() {
		return isServed;
	}
	public int getfloor() {
		return fl_num;
	}
	public int getoffice() {
		return of_num;
	}
}
