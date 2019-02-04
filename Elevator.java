import java.util.LinkedList;

public class Elevator extends Space{
	
	private LinkedList<Human>[] stoparray=new LinkedList[5];
	

	Elevator(int Nel){
		super(Nel);
		for(int i=0;i<5;i++) {
			stoparray[i]=new LinkedList<Human>();
		}
		System.out.println("A lift has been created\n\n");
		vcounter=0;
	}
	
	
	public void enter(Human v){			//a visitor enters the elevator 
		if(v!=null) {
			if(!v.getisServed()){					//if he is not served he enters a queue for the floor he wants to go to
				int f=v.getfloor();
				stoparray[f].addFirst(v); 
				if(v instanceof Visitor) {
					System.out.println("visitor with destination "+v.getfloor()+"-"+v.getoffice()+" has entered the elevator\n");
				}else {
					System.out.println("Escort to visitor with destination "+v.getfloor()+"-"+v.getoffice()+" has entered the elevator\n");
				}
			}else{									//if he is served he enters a queue to leave to building
				stoparray[0].addFirst(v);
				System.out.println("A served customer has entered the elevator\n");
			}
			vcounter++;
		}
	}
		
	public Human exit(int stop){			//a visitor exits the elevator
		vcounter--;
		return stoparray[stop].removeLast();
	}
	
	public Human exit1(int f) {
		vcounter--;
		Human v;
		for(int i=0;i<stoparray[f].size();i++) {
			v=stoparray[i].get(i);
			if(v instanceof Visitor && !((Visitor) v).isEscorted()) {
				return stoparray[f].remove(i);
			}
		}
		return null;
	}
	
	public void stop_up(Floor[] flarray){		//the elevator goes up,leaving visitors at each floor
		for(int i=0;i<4;i++){
			while(!flarray[i].isFull()&&!stoparray[i+1].isEmpty()){
				if(flarray[i].getcap()-flarray[i].getcounter()>1) {
					Human v=exit(i+1);
					flarray[i].enter(v);
					if(((Visitor) v).isEscorted()) {
						v=exit(i+1);
						flarray[i].enter(v);
					}
				}else {
					flarray[i].enter(exit1(i+1));          //a visitor without escort enters
				}
			}
		}
	}
	public void stop_down(Floor[] flarray){		//the elevator goes down and served visitors from each floor get aboard
		for(int i=3;i>=0;i--){
			while(!flarray[i].get_warray_isempty(0)&&!(stoparray[0].size()==capacity)){
				enter(flarray[i].exit(0));
			}
		}
	}
	
	public int operate(Floor[] flarray,Groundfloor groundfloor){		//stop up - call to start the floor operations-stop down-served visitors exit
		stop_up(flarray);
		System.out.println("\n");
		for(int i=0;i<4;i++){
			flarray[i].floor_operate(capacity-vcounter);
		}
		stop_down(flarray);
		int c=0;
		while(!stoparray[0].isEmpty()){
			groundfloor.exit(exit(0));
			c++;
		}
		return c;					//number of served visitors
	}
	


}
