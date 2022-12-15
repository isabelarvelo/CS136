// I am the sole author of the work in this repository.
import structure5.*;
import java.util.Scanner;

//$ Great work!
public class WordGen {

  public static String readText(Scanner in) {
    StringBuffer textBuffer = new StringBuffer();

    while (in.hasNextLine()) {
      String line = in.nextLine();
      textBuffer.append(line);
      textBuffer.append("\n");
    }

    String text = textBuffer.toString();
    // 'text' now contains the full contents of the input.

    return text;
  }

  //table for all of them
  public static void processText(String text, int k, Table table) {

    //making text circular so that it is impossible to encounter a sequence that has no known successor
    String addCircle = text.substring(0, k);
    text += addCircle;

    String key;

    //move across text and populate table with frequency list for characters that follow each k-gram of text
    for (int i = 0; i < text.length() - k; i++) {
      key = text.substring(i, i + k);
      table.add(key, String.valueOf(text.charAt(i + k)));
    }

  }

  //$ Make sure to include comments within blocks of code!
  public static String generateText(Table table, String output) {

    int k = output.length();

    while (output.length() < 500) {
      String key = output.substring(output.length() - k);
      output += table.choose(key);
    }

    return output;

  }

  public static void main(String[] args) {
    if (args.length == 0) {
    // no args, so print usage line and do nothing else
      System.out.println("Usage: java WordGen k");
    } else {
      // convert first argument to k
    int k = Integer.parseInt(args[0]);


    Table table = new Table(); //new Table

    String text = readText(new Scanner(System.in));
    //String text = "asdfghjk";
    processText(text, k, table);

    String output = text.substring(0, k);

    String newText = generateText(table, output);

    System.out.println(newText); //Output string

  }
}
}
