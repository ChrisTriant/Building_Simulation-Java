import java.util.*;

public class WaitingLobby extends Entrance {
	private	int fl_num;
	private LinkedList<Human>[] wait_array = new LinkedList[11];
	public
		WaitingLobby(int fl,int nf) {
			super(nf);
			fl_num=fl;
			for(int i=0;i<11;i++) {
				wait_array[i]=new LinkedList<Human>();
			}
			System.out.println("A waiting lobby on floor "+fl_num+" has been created");
		}
		public void enter(Human v) {
			if(!v.getisServed()){						//if he is not served he will be placed in a queue for the office he wants
				int f=v.getoffice();
				wait_array[f].addFirst(v);
				if(v instanceof Visitor) {
					System.out.println("visitor with destination "+v.getfloor()+"-"+v.getoffice()+" has entered floor number: "+fl_num+"\n");
				}else {
					System.out.println("The escort of visitor with destination "+v.getfloor()+"-"+v.getoffice()+" has entered floor number: "+fl_num+"\n");
				}
				
			}else{										//if he is served he will be placed in a "special" queue to return to the elevator
				wait_array[0].addFirst(v);
				if(v instanceof Visitor) {
				System.out.println("A customer from office "+fl_num+"-"+v.getoffice()+" has been served\n");
				}else {
					System.out.println("The escort of customer from office "+fl_num+"-"+v.getoffice()+" has been served\n");
				}
			}
			
		}
		public Human exit(int f) {
			return wait_array[f].removeLast();
		}
		public Human exit1(int f) {							//one non escorted visitor enters
			Human v;
			for(int i=0;i<wait_array[f].size();i++) {
				v=wait_array[i].get(i);
				if(v instanceof Visitor && !((Visitor) v).isEscorted()) {
					return wait_array[f].remove(i);
				}
			}
			return null;
		}
		public boolean is_arr_empty(int f) {
			return wait_array[f].isEmpty();
		}
}
