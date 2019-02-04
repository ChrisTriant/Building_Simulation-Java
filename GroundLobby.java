import java.util.LinkedList;
import java.util.Vector;

public class GroundLobby extends Entrance {
	private int priority;
	private LinkedList<Human> gf_queue;
	public GroundLobby(int ng){
		super(ng);
		System.out.println("waiting lobby on ground floor has been created\n");
		gf_queue=new LinkedList<Human>();
		System.out.println("A queue in the ground floor has been created\n");
		priority=1;
	}
	

	public int enter(Vector<Human>varray,int voutside,int gfcounter,boolean grfull,int bcounter ){		//a visitor enters the queue in the ground floor waiting lobby
		
		if(!grfull){	
			while( (gf_queue.size()!=max_cap)&& (voutside!=0)){
				if(max_cap-gf_queue.size()>1) {
					gf_queue.addFirst(varray.elementAt(0));
					if(varray.elementAt(0) instanceof Visitor) {
						System.out.println("visitor with destination "+varray.elementAt(0).getfloor()+"-"+varray.elementAt(0).getoffice()+" has entered the building\n");
						wait(varray.elementAt(0));
						gfcounter++;
						voutside--;
						bcounter++;
						varray.removeElementAt(0);
						if(((Visitor) gf_queue.getFirst()).isEscorted()) {
							gf_queue.addFirst(varray.elementAt(0));
							System.out.println("The escort of visitor with destination "+gf_queue.getFirst().getfloor()+"-"+gf_queue.getFirst().getoffice()+" has entered the building\n");
							gfcounter++;
							voutside--;
							bcounter++;
							varray.removeElementAt(0);
						}						
					}
				}else{
					gf_queue.addFirst(enter1(varray));
					System.out.println("visitor with destination "+gf_queue.getFirst().getfloor()+"-"+gf_queue.getFirst().getoffice()+" has entered the building\n");
					gfcounter++;
					voutside--;
					bcounter++;
				}
			}
		}else{
			System.out.println("Please wait outside\n");
		}
		return voutside;
	}
	
	public void wait(Human v){			//a visitor gets a priority number
			((Visitor) v).setpriority(priority);
					priority++;
	}
	
	public Human exit(){			// a visitor leaves the ground floor waiting queue
		return	gf_queue.removeLast();
	}
	
	public Human enter1(Vector<Human>varray) {
		Human v;
		for(int i=0;i<varray.size();i++) {
			v=varray.elementAt(i);
			if(v instanceof Visitor && !((Visitor) v).isEscorted()) {
				varray.removeElementAt(i);
				return v;
			}
		}
		return null;
	}
	public Human exit1(int f) {
		Human v;
		for(int i=0;i<gf_queue.size();i++) {
			v=gf_queue.getFirst();
			if(v instanceof Visitor && !((Visitor) v).isEscorted()) {
				gf_queue.removeFirst();
				return v;
			}
		}
		return null;
	}
}
