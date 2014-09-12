// Primes.java
//Taylor Brockhoeft
//9/8/14
//Java, Homework one.
import java.util.*;

public class Primes
{
	public static Scanner s;
	public static int x;
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    x = -1;
System.out.println("Exercise 6.25: 'Primes'");
    System.out.print("Print Primes Up Too? (Must be a positive Integer): ");
    while(x < 0){    	
    x = s.nextInt();
    if(x <= 0){
    	System.out.println("Must be a positive Integer, please try again:");
    }
	}
	System.out.printf("Primes less than or equal too %d:\n",x);
	printPrimes(8,x);    
  }

  //checks whether an int is prime or not.
  //Based off of this example: MKYONG
  //http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
	public static boolean isPrime(int n) {
	    for(int i=2;2*i<n;i++) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}


	public static void printPrimes(int rows, int n){
		int counter = 0;
		for(int i = 2; i <= n; i++){
			if(isPrime(i)){
				if( i == 4){
					continue;
				}			
				System.out.printf("%-5d",i );
				counter++;
			}
			if(counter==rows){
				System.out.print("\n");
				counter = 0;
			}		
		}
		System.out.println("\n");
}


}