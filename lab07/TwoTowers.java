//I am the sole author of the work in this repository
//This class uses the SubsetIterator to iterate through heights of
//   different squares and finds the combination where the heights of the two towers are closest
import java.util.Scanner;
import structure5.*;


public class TwoTowers {

  protected double height;
  private double num;
  private Vector<Double> myVec = new Vector<Double>();

  /**
  *constuructor that ceates how many blocks we have
  */
  public TwoTowers(double n) {
    num = n;
  }

  /**
  * @returns total height of all the blocks together
  */
  public double getHeight() {
    return height;
  }

  /**
  * @param num is a double that represents how many total blocks are in the towers
  * @pre num is non-null
  * @returns a vector with all the blocks heights
  */
  public Vector<Double> createVector(double num) {
    for (double i = 1; i <= num; i++) {
      myVec.add(Math.sqrt(i));
      height = height + Math.sqrt(i);
    }
    return myVec;
  }

  /**
  * @param v is a vector that represents a Tower. We want to find the height
  *   of it which is the sum of the values in the vector
  * @pre v is of type Vector<Double>
  * @returns a double that indicates the height of the tower
  */
  public double sumofVector(Vector<Double> v) {
    double vectorSum = 0;
    for (Double d: v) {
      vectorSum += d;
    }
    return vectorSum;
  }


  /**
  * @param v is a vector that represents a Tower
  * @pre v is non-null
  * @returns a vector squaring each element
  */
  public Vector<Double> getBackToArea(Vector<Double> v) {
    for (int i = 0; i < v.size(); i++) {
      //squaring each value in the vector to get back to the area of the square
      Double squared = (double) Math.round(Math.pow(v.get(i), 2));
      v.set(i, squared);
    }
    return v;
  }

  /**
  * @param v is a vector that represents a Tower
  * @pre v is non-null
  * @returns a string in the format we desire to print out a tower
  */
  public String toString(Vector<Double> v) {
    String result = "[";
    for (Double d: v) {
      result = result + (int) Math.round(d) + ", ";
    }
    if (v.size() == 0) {
      return "[]";
    }
    result = result.substring(0, result.length() - 2); //removing the last comma in the string
    return result + "]";
  }

  //$ a bit long---the main loop would be better in a 
  //$ helper method 
  /**
  * main method to create the necessary vectors
  */
  public static void main(String[] args) {
    //$ Good!
    double num = Double.parseDouble(args[0]);
    TwoTowers inMain = new TwoTowers(num);

    Vector<Double> bestOutcome = new Vector<Double>();
    double sumOfBest = 0;
    double sumOfSecondBest = 0;
    double currentSum = 0;
    Vector<Double> secondBest = new Vector<Double>();
    Vector<Double> myVector = inMain.createVector(num);
    SubsetIterator<Double> myIt = new SubsetIterator<Double>(myVector);

    while (myIt.hasNext()) {
      Vector<Double> current = myIt.next();
      currentSum = inMain.sumofVector(current);

      if (currentSum < inMain.getHeight() / 2 && currentSum > sumOfSecondBest) {
        if (currentSum > sumOfBest) {
          sumOfSecondBest = sumOfBest;
          secondBest = bestOutcome;
          sumOfBest = currentSum;
          bestOutcome = current;
//$ Great! Caught the edge case!
        } else {
          sumOfSecondBest =  currentSum;
          secondBest = current;
        }
      }
    }
    System.out.println("There are " + (int) Math.round(num) + " total blocks.");
    System.out.println("The half height (h/2) is: " + inMain.getHeight() / 2);
    System.out.println("The best subset (left stack) is: " + inMain.toString(inMain.getBackToArea(bestOutcome)) + " = " + sumOfBest);
    System.out.println("The second best subset (left stack) is: " + inMain.toString(inMain.getBackToArea(secondBest)) + " = " + sumOfSecondBest);


  }


}
