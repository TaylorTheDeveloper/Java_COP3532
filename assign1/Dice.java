// Pi.java
//Taylor Brockhoeft
//9/8/14
//Java, Homework one.
import java.util.*;

public class Dice
{
	public static Scanner s;
	public static int x;
	public static int[] sums = {2,3,4,5,6,7,8,9,10,11,12};
	public static int[] accounting = {0,0,0,0,0,0,0,0,0,0,0};
	
	  public static void main(String[] args){
	    Scanner s = new Scanner(System.in);
	    x = -1;
		System.out.println("Exercise 7.17: 'Rolling the Dice'");
	    System.out.print("How many rolls of the dice? (Must be a positive Integer): ");
	    while(x < 0){    	
	    x = s.nextInt();
	    if(x <= 0){
	    	System.out.println("Must be a positive Integer, please try again:");
	    }
		}

		runSim(x);
		printSummary(x);   
	  }

	public static void runSim(int rolls){
		int num;
		for(int i =0; i< rolls; i++){
			num = rollDie() + rollDie();
			accountEach(num);
		}
	}

	public static void printSummary(int rolls){
		System.out.printf("Sum\t# Of Times\tPercentage\n");
		float percentage;
		for(int i = 0; i < accounting.length; i++){
			percentage = ((float)accounting[i])/((float)rolls)*100;
		System.out.printf("%d\t%d\t\t%.2f %%\n", sums[i], accounting[i], percentage);
		}
		System.out.printf("\nTotal Number of Rolls: %d\n", rolls);
	}

	public static void accountEach(int sum){
		switch(sum){
			case 2: accounting[0] +=1;
			break;
			case 3: accounting[1] +=1;
			break;
			case 4: accounting[2] +=1;
			break;
			case 5: accounting[3] +=1;
			break;
			case 6: accounting[4] +=1;
			break;
			case 7: accounting[5] +=1;
			break;
			case 8: accounting[6] +=1;
			break;
			case 9: accounting[7] +=1;
			break;
			case 10: accounting[8] +=1;
			break;
			case 11: accounting[9] +=1;
			break;
			case 12: accounting[10] +=1;
			break;

		}
	}


	//6 is the maximum and the 1 is our minimum 
	public static int rollDie( ){
		Random rand = new Random();
		int n = rand.nextInt(6) + 1;
		return n;
		}


}