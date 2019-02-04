import java.util.Vector;

public class Entrance {
	protected
		  int max_cap;
		
	public Entrance(int mcap) {
			max_cap=mcap;
		}
			
	public void enter(Human v) {
				
		}
	public int enter(Vector<Human> varray,int voutside,int gfcounter,boolean grfull,int bcounter) {
			System.out.println("This is from super");
				return -1;
		}
	public Human exit() {
			return null;
		}
	public Human exit(int f) {
			return null;	
		}

	public boolean is_arr_empty(int f) {
		System.out.println("this is from super\n");
		return false;
	}

	public Human exit1(int f) {
		System.out.println("This is Super");
		return null;
	}

}
