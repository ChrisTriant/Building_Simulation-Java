

public abstract class Space {

			protected int capacity;
			protected int vcounter;

			public Space(int cap){
				capacity=cap;
			}
			public abstract void enter(Human v);
			
			public int getcounter() {
				return vcounter;
			}
			public boolean isFull() {
				return vcounter==capacity;
			}

			public int getcap(){
				return capacity;
			}
}
