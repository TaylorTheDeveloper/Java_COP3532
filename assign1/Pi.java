// Pi.java
//Taylor Brockhoeft
//9/8/14
//Java, Homework one.
import java.util.*;

public class Pi
{
	public static Scanner s;
	public static int x;
	  public static void main(String[] args){
	    Scanner s = new Scanner(System.in);
	    x = -1;
		System.out.println("Exercise 5.20: 'Approximating PI'");
	    System.out.print("Compute how many terms of series? (Must be a positive Integer): ");
	    while(x < 0){    	
	    x = s.nextInt();
	    if(x <= 0){
	    	System.out.println("Must be a positive Integer, please try again:");
	    }
		}
		printPi(x);    
	  }

	public static void printPi(int n){
		int i;
		double p = 0.0;

		System.out.print("terms\tPI Approximation\n");
		for(i = 1; i <= n; i++){
			if(i%2 ==0){
				p = p - (4.0/((2*i)-1));
			}
			else{
				p = p + (4.0/((2*i)-1));
			}
		System.out.printf("%d\t%f\n", i,p);
		}
	}


}