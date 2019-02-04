import java.util.Random;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		
		int N=Integer.parseInt(args[1]);
		System.out.printf("Building capacity is: %d \n ",N);
		int Nf=Integer.parseInt(args[2]);
		if(Nf >= N/3){
			System.out.printf("Invalid capacities.Please try again");
			return;	
		}
		System.out.printf("Floor capacity is: %d\n",Nf);
		int Ng=Integer.parseInt(args[3]);
		if(Ng>=N/2){
			System.out.printf("Invalid capacities.Please try again");
			return ;	
		}
		System.out.printf("Ground floor capacity is: %d\n ",Ng);
		int No=Integer.parseInt(args[4]);
		if(No >= Nf){
			System.out.printf("Invalid capacities.Please try again");
			return ;	
		}
		System.out.printf("Office capacity is: %d\n ",No);
		int Nl=Integer.parseInt(args[5]);
		if(Nl < 10||Nl>Ng){
			System.out.printf("Invalid capacities.Please try again");
			return ;	
		}
		System.out.printf("Elevator capacity is: %d\n",Nl);
		int K=Integer.parseInt(args[6]);
		System.out.printf("There are %d people waiting to be served\n",K);
		int L=Integer.parseInt(args[7]);
		System.out.printf("The elevator will make %d cycle(s)\n\n",L);
		
		Vector<Human> varray=new Vector<Human>(K);
		
		int i;
		int h;  					//randomly choose who will be a visitor or an escort
		int flag=0;					//check if last human created was an escort
		Random r=new Random();
		for(i=0;i<K;i++){
			h=r.nextInt(2)+1;
			if(i==0||flag==1) {
				h=1;
			}
			if(h==1){
				int fl_num=r.nextInt(4)+1;
				int of_num=r.nextInt(10)+1;
				varray.add(new Visitor(fl_num,of_num));
				flag=0;
			}else {
				varray.add(new Escort(varray.elementAt(i-1).getfloor(),varray.elementAt(i-1).getoffice()));
				((Escort) varray.elementAt(i)).setV(varray.elementAt(i-1));
				((Visitor) varray.elementAt(i-1)).gotEscorted();
				flag=1;
			}

		
		}
		System.out.printf("\n");
		
		Building building=new Building(N,Ng,Nl,Nf,No);		//a building is created
		
		building.operate(K,L,varray);        //the simulation starts

		System.out.println("\nEnd of simulation\n");
	}

}
