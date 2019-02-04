import java.util.Vector;

public class Groundfloor extends Level{
	
	Groundfloor(int ng){	
		super(ng);
		vcounter=0;
		entr=new GroundLobby(capacity);
		System.out.println("Ground floor has been created\n"); 
	}

	
	public int enter(Vector<Human> varray,int voutside,int bcounter){	//sends visitor into the ground floor waiting lobby
		int temp=voutside;
		voutside=entr.enter(varray,voutside,vcounter,isFull(),bcounter);
		vcounter+=temp-voutside;
		return voutside;
	}
	
	public Human exit(){					//gets a visitor from the ground floor waiting lobby
		vcounter--;
		return entr.exit();
	}
	
	public Human exit1() {
		vcounter--;
		return entr.exit1(0);
	}
	
	public void exit(Human v){					//a visitor has been served
		if(v instanceof Visitor) {
			System.out.println("I finally finished!!! .Order number: "+((Visitor) v).getpriority());
		}else {
			System.out.println("Escort of visitor "+((Visitor) ((Escort) v).getV()).getpriority()+" exits");
			}
	}
}
