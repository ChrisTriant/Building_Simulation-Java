import java.util.Random;

public class Floor extends Level {
	
	int No;
	int fl_num;
	Office[] ofarray;

	Floor(int fl,int nf,int no){
		super(nf);
		fl_num=fl;
		No=no;
		vcounter=0;
		System.out.println("Floor number: "+fl_num +" has been created\n");
		ofarray=new Office[10];
		for(int i=0;i<10;i++){
			ofarray[i]=new Office(fl_num,i+1,No);
		}
		entr= new WaitingLobby(fl_num,capacity);
	}
	
	public void floor_operate(int rem) {
		for(int i=0;i<10;i++){
			while(!ofarray[i].isFull()&&!entr.is_arr_empty(i+1)){
				if(No-ofarray[i].getcounter()>1) {
					Human v=entr.exit(i+1);
					office_enter(v,i);						//visitor enters 
					if(((Visitor) v).isEscorted()) {
						v=entr.exit(i+1);					//escort follows
						office_enter(v,i);
					}
					
				}else {
					office_enter(((WaitingLobby) entr).exit1(i+1),i);
					vcounter--;
				}
			}

			if(ofarray[i].isFull()){
				System.out.println("Office "+fl_num+(i+1)+" is full.Please wait\n");
			}
		}
		Random rand= new Random();
		Human served;
		for(int i=0;i<10;i++){
			int s=rand.nextInt(10)+1;			// 1/10 chance of no one getting served 
			if(s!=0&&rem!=0&&ofarray[i].getcounter()!=0){
				int r=rand.nextInt(ofarray[i].getcounter());
				if(rem==1) {
					served=ofarray[i].exit(r, rem);
					if(served!=null) {
						served.gotserved();
						entr.enter(served);
						rem--;
					}
				}else {
					served=ofarray[i].exit(r,rem);
					served.gotserved();
					entr.enter(served);
					rem--;
					if(served instanceof Visitor) {
						if(((Visitor) served).isEscorted()) {		
							served=ofarray[i].exit(r-1, rem+1);		//escort follows visitor
							served.gotserved();
							entr.enter(served);
							rem--;
						}
					}else {
						served=ofarray[i].exit(r, rem+1);			//visitor follows escort
						served.gotserved();	
						entr.enter(served);
						rem--;
					}
				}
			}
		}
	}
	
	public void enter(Human v) {
		entr.enter(v);
		vcounter++;
	}
	public void office_enter(Human v,int i) {
		ofarray[i].enter(v);
	}
	public Human exit(int f){
		vcounter--;
		return entr.exit(f);
	}
	public boolean get_warray_isempty(int f) {
		return entr.is_arr_empty(f);
	}

	
}
