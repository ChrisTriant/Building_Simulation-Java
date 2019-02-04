
public class Level extends Space {
	
	protected Entrance entr;
	public Level(int cap) {
		super(cap);
	}

	public void enter(Human v) {
		entr.enter(v);
	}
	public void enter(Visitor[] varray,int voutside,int bcounter) {
			
	}
	public Human exit(){
		return entr.exit();
	}
	public void exit(Human v) {
			
	}
	public Human exit(int f){
			return null;
	}
}
