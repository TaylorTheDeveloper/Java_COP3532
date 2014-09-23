//IntegerSet.java
//Taylor Brockhoeft
//9/19/14
//Java, Homework Two.

//Class that handles sets of integers from 0 to 100
// as well as intersections and unions

import java.util.*;
import java.lang.*;

public class IntegerSet{
	final int ARYSIZE = 101;
	boolean[] set;

	//Creates a new instance of an IntegetSet, and initilizes all items to false
	public IntegerSet(){
		set = new boolean[ARYSIZE];//Initilize boolean set array to empty set
		for(int i = 0; i< ARYSIZE; i++){
			set[i] = false;
		}

	}
	
	//Returns The String Representation of the set.
	public String toString(){
		boolean isEmpty = true;
		StringBuilder setstuff = new StringBuilder();
		for (int i=0; i<ARYSIZE; i++) {
		  if (set[i]) {
		    setstuff.append(Integer.toString(i)).append(" ");
		    isEmpty = false;
		  }
		}
		if (isEmpty){
			return "Empty set";
		} 
		return setstuff.toString();
	}

	//Returns True if both sets are equal, false if otherwise
	public boolean isEqualTo(IntegerSet otherSet){
	    for (int i=0; i<ARYSIZE; i++){
	      if (set[i] != otherSet.set[i]) return false;
	    }
	    return true;
  	}

  	//Returns an Union of the set and otherSet
  	public IntegerSet union(IntegerSet otherSet){
	    IntegerSet newSet = new IntegerSet();
	    for (int i=0; i<ARYSIZE; i++){
	      newSet.set[i] = (set[i] || otherSet.set[i]);
	    }
	    return newSet;
  	}
  
  	//Returns an Intersection of the set and otherSet
  	public IntegerSet intersection(IntegerSet otherSet){
	    IntegerSet newSet = new IntegerSet();
	    for (int i=0; i<ARYSIZE; i++){
	      newSet.set[i] = (set[i] && otherSet.set[i]);
	    }
	    return newSet;
  	}

  	//Adds element i to set
  	public IntegerSet insertElement(int i){
	    if ((i>=0)&&(i<ARYSIZE)){
	      set[i] = true;
	    }
	    else{
	      System.out.println("ERROR: Out of Bounds!");
	      return this;
	    }
	    return this;
  	}
  
  	//Delete Element From Set
  	public IntegerSet deleteElement(int i){
	    if ((i>=0)&&(i<ARYSIZE)){
	      set[i] = false;
	    }
	    else{
	      System.out.println("ERROR: Nothing to Delete!");
	      return this;
	    }
	    return this;
  	}

}