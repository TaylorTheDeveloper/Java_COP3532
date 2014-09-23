// class Fraction
// Author:  Bob Myers
//
// For COP3252, Java Programming
//
//
// Modified 9/19/14 by Taylor Brockhoeft for Java Homework 2
public class Fraction
{
  private int numerator = 0;		// numerator (and keeps sign)
  private int denominator = 1;		// always stores positive value

  public Fraction()
  {
  }

  public Fraction(int n, int d)
  {
    if (set(n,d)==false)
	set(0,1);
  }

  public boolean set(int n, int d)
  {
    if (d > 0)
    {
	numerator = n;
	denominator = d;
	return true;
    }
    else
	return false;
  }
  
  public String toString()
  {
    return (numerator + "/" + denominator);
  }

  public int getNumerator()
  {
    return numerator;
  }

  public int getDenominator()
  {
    return denominator;
  }

  public double decimal()
  {
    return (double)numerator / denominator;
  }

//Calculate Greatest Common Denominator. This function is based off Euclids Algorithm
  public int GCD(int a, int b){
    if(b==0){
      return a;
    }
    else{
      return GCD(b, a%b);
    }
  }
//Simplifies a Fraction, returns the result
  public Fraction simplify(){
    int gcd = GCD(numerator,denominator);

    if((denominator/gcd != 0) && (numerator/gcd !=0)){
      numerator = numerator/gcd;
      denominator = denominator/gcd;
    }
    else{
      numerator = 0;
      denominator = 0;
    }

    return this;

  }

//Add Two Fractions
  public Fraction add(Fraction other){
    int resNum, resDen;
    Fraction res;

    resNum = (numerator * other.denominator) + (other.numerator *denominator);
    resDen = denominator * other.denominator;
    if(resDen<0){
      resNum = -1*resNum;
      resDen = -1*resDen;
    }
    res = new Fraction(resNum,resDen);
    res.simplify();
    return res;
  }

//Subtract Two Fractions
  public Fraction subtract(Fraction other){
    int resNum, resDen;
    Fraction res;

    resNum = (numerator * other.denominator) - (other.numerator *denominator);
    resDen = denominator * other.denominator;
    if(resDen<0){
      resNum = -1*resNum;
      resDen = -1*resDen;
    }
    res = new Fraction(resNum,resDen);
    res.simplify();
    return res;
  }

//Multiply Two Fractions
  public Fraction multiply(Fraction other){
    int resNum, resDen;
    Fraction res;

    resNum = numerator * other.numerator;
    resDen = denominator * other.denominator;
    if(resDen<0){
      resNum = -1*resNum;
      resDen = -1*resDen;
    }
    res = new Fraction(resNum,resDen);
    res.simplify();
    return res;
  }

//Divide Two Fractions
  public Fraction divide(Fraction other){
    int resNum, resDen;
    Fraction res;

    resNum = numerator * other.denominator;
    resDen = denominator * other.numerator;
    if(resDen<0){
      resNum = -1*resNum;
      resDen = -1*resDen;
    }
    res = new Fraction(resNum,resDen);
    res.simplify();
    return res;
  }




}