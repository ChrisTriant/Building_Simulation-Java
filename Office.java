import java.util.LinkedList;

public class Office extends Space {
	
	private int fl_num;
	private int of_num;
	LinkedList<Human> customers;

	Office(int fl,int of,int no){
		super(no);
		fl_num=fl;
		of_num=of;
		customers=new LinkedList<Human>();
		System.out.println( "Office number " +fl_num + "-" +of_num + " has been created\n");
	}

	public void enter(Human v){								//a visitor enters an office and the office counter increases
		customers.addFirst(v);
		if(v instanceof Visitor) {
			System.out.println("visitor with destination "+v.getfloor()+"-"+v.getoffice()+" has entered office number: "+fl_num+"-"+of_num+"\n");
		}else {
			System.out.println("The escort of visitor with destination "+v.getfloor()+"-"+v.getoffice()+" has entered office number: "+fl_num+"-"+of_num+"\n");
		}
		vcounter++;
	}
	public Human exit(int r,int rem){					//a visitor leaves an office and the office counter decreases
		
		if(rem==1) {
			Human alone;
			for(r=0;r<getcounter();r++) {
				alone=customers.get(r);
				if((alone instanceof Visitor)&& !((Visitor) alone).isEscorted() ) {
					vcounter--;
					return customers.remove(r);
				}
			}
			return null;
		}
		vcounter--;
		return	customers.remove(r);	
	}
	

	
}
