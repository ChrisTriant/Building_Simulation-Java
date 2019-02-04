import java.util.Vector;

public class Building extends Space {
	private int Ng;
	private int Nl;
	private int Nf;
	private	int No;
	int voutside;
	Floor[] flarray;
	Groundfloor groundfloor;
	Elevator elevator;
	
	
	Building(int n,int ng,int nl,int nf,int no){
		super(n);
		Ng=ng;
		Nl=nl;
		Nf=nf;
		No=no;
		groundfloor=new Groundfloor(Ng);
		elevator=new Elevator(Nl);
		flarray=new Floor[4];
		for(int i=0;i<4;i++){
			flarray[i]=new Floor(i+1,Nf,No);
		}
		System.out.println("A new building is ready for serving citizens!\n");
		vcounter=0;
	}

	
	public int enter(Vector<Human> varray,int voutside,int vcounter){		//a visitor enters the building and is guided to the ground floor waiting lobby
		voutside=groundfloor.enter(varray,voutside,vcounter);
		return voutside;
	}
	public void enter(Human v){
	}
	
	void el_enter(){										//visitors enter the elevator
		while(!(elevator.isFull())&&(getgfcounter()!=0)){
			if(Nl-elevator.getcounter()>1) {
				Human v=groundfloor.exit();
				elevator.enter(v);
				if(((Visitor) v).isEscorted()) {
					v=groundfloor.exit();
					elevator.enter(v);
				}
			}else {
				elevator.enter(groundfloor.exit1());
			}
		}	
	}
	int getgfcounter(){
		return groundfloor.getcounter();
	}
	int getelcounter(){
		return elevator.getcounter();
	}
	void operate(int K,int L,Vector<Human> varray){
	int voutside=K;
	int x;
	for(int l=0;l<L;l++){						//elevator cycles
		System.out.println("Cycle: "+l);
		if(voutside!=0&&!isFull()){				//if there are visitor outside they enter the building
			x=voutside;
			System.out.println(x+" visitors are outside\n");
			voutside=enter(varray,voutside,vcounter);
			System.out.println((x-voutside)+" visitors have entered  the building\n");
			System.out.println(voutside+"  visitors are waiting outside\n");
			vcounter+=x-voutside;
			System.out.println("There are "+vcounter+" in the building\n\n");
		}else if(isFull()){
			System.out.println("Building is full.Please come tomorrow\n");
		}
				el_enter();						//call to get visitors into the elevator
		System.out.println("\n\n");
			int t=elevator.operate(flarray,groundfloor);		
			vcounter=vcounter-t;				//served visitors leave the building and the building counter is decreased
		}

	}
}
