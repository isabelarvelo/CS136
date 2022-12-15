import structure5.*;
import java.util.Iterator;
import java.util.Scanner;


/*
* A highly efficient implementation of a lexicon using a trie data structure
* that allows users to build up a list of lower case words by reading in files
* or providing user input. This class supports functionality for lookup of words
* and prefixes and removal of words.
*/
public class LexiconTrie implements Lexicon {

  //Keeps track of word count
  // $ Since you are initializing this in the constructor, there is no need to do it here
  private int wordcount = 0;

  //Start node
  private LexiconNode root = new LexiconNode('*', false);

  /**
  * Constructor to create an empty lexicon trie object
  */
  public LexiconTrie() {
    int wordcount = 0;
    LexiconNode root = new LexiconNode('*', false);
  }

  /**
  * Adds word to word list contained by lexicon
  *@param word string to be added to lexicon
  *@return boolean that indicates whether or not word was successfully added
  *@post word is stored in lexicon
  */
  public boolean addWord(String word) {
    //only working with lower case words
    word = word.toLowerCase();

    //start at top of trie
    LexiconNode current = root;

    //not going to add word if it is already within lexicon
    // $ Calling containsWord leads to an additional unnecessary traversal of the trie
    // $ Can you implement this without it?
    if (!containsWord(word)) {
      //iterate across word and see if it shares prefix with other words in
      //lexicon
      for (int i = 0; i < word.length(); i++) {
        //once we reach part of the word that is not already in lexicon we need to
        //make new nodes to contain the characters in the string
        if (current.getChild(word.charAt(i)) == null) {
          LexiconNode newNode = new LexiconNode(word.charAt(i), false);
          current.addChild(newNode);
      }
      current = current.getChild(word.charAt(i));
    }

    //increment word count
    wordcount++;

    //signal that the last node in the trie representation of word indicates that
    //it is the end of the word
    current.setFlag(true);
    return true;
}
//return false if the word was already contained in the lexicon
return false;

}

/**
* Reads words in from a file
*@param filename name of file that contains words we want to add to lexicon
*@return int number of words added to lexicon
*@pre filename refers to a file in the current directory
*/
public int addWordsFromFile(String filename) {
  Scanner sc = new Scanner(new FileStream(filename));
  int i = 0;
  while (sc.hasNextLine()) {
    //read in next line
    String newWordToAdd = sc.nextLine();
    newWordToAdd = newWordToAdd.toLowerCase();
    boolean didAdd = addWord(newWordToAdd);
    if (didAdd) {
      i += 1;
    }
  }
  //return number of words read in from file
  return i;
}

/**
* Removes word from lexicon
*@param word word that we would like to remove from word list
*@return boolean that indicates whether or not word was successfully removed
*@post containsWord(word) returns false
*/
public boolean removeWord(String word) {

  word = word.toLowerCase();

  //check to see if word we want to remove is in lexicon
  //$ Is this check needed? Can you detect this as you're searching for the
  //$ word below?
  if (!containsWord(word)) {
    return false;
  }

  //building a stack that contains letters of the word we want to remove
  StackList<LexiconNode> removedword = new StackList<LexiconNode>();

  //filling our stack so that end of word is at the top of the stack
  LexiconNode l = root;

  //push the start of the word first
  removedword.push(l);

  //fill stack with letters in the word
  for (int x = 0; x < word.length(); x++) {
    l = l.getChild(word.charAt(x));
    //$ checking if l == null would let you know that the word wasn't in the trie
    removedword.push(l);
  }

  //set the flag of the last node in the word to false
  removedword.get().setFlag(false);

  //decrement word counter after we have removed word
  wordcount--;

  //call helper method to remove appropriate letters in the word from the trie
  return removeWordLettersHelper(removedword, word);

  }

  //helper method to remove letters in the word we are removing that do not
  //make up other words
  private boolean removeWordLettersHelper(StackList<LexiconNode> removedword, String word) {

    //iterating across word to remove each letter
    for (int x = 1; x < word.length(); x++) {
      //removing letters of the word until we reach the end of another word or
      //we reach a node that has other children
      //$ Right idea, but getChildren never returns null
      //$ instead check for getChildren().size() == 0
      if (removedword.get().getChildren() == null && !removedword.get().isAWord()) {
        char let =  removedword.pop().getChar();
        removedword.get().removeChild(let);
      } else {
        return true;
      }
  }
    System.out.println("Failed removal");
    return false;
}

/**
* Returns the number of words contained in lexicon
*@return number of words in lexicon
*/
public int numWords() {
  return wordcount;
}

/**
* Returns boolean that indicates whether or not word is contained in lexicon
*@param word word we are searching for
*@return boolean that whether or not lexicon contains word
*@post returns true if word is in lexicon
*/
public boolean containsWord(String word) {
  word = word.toLowerCase();
  return containsWordHelper(root, word);
}

//helper method for contains word method
private boolean containsWordHelper(LexiconNode node, String word) {
  //checking to see if the first character of word is a child of root node
  if (node.getChild(word.charAt(0)) == null) {
    return false;
  }

  //base case
  if (word.length() == 1) {
    return node.getChild(word.charAt(0)).isAWord();
  } else {
    //recursively checking if the next character in word is a child of the current node
    return containsWordHelper(node.getChild(word.charAt(0)), word.substring(1, word.length()));
  }
}


/**
* Returns boolean that indicates whether or not given prefix is contained in lexicon
*@param prefix prefix we are searching for
*@return boolean that indicates whether or not a word in lexicon has given prefix
*@post returns true if prefix is a prefix of one of words stored in lexicon
*/
// $ containsWord and containsPrefix have nearly the same functionality, so if you
// $ implemented them in the same way, you could have a helper method that does
// $ most of the functionality for both.
public boolean containsPrefix(String prefix) {
  prefix = prefix.toLowerCase();

  //starting at root
  LexiconNode currentNode = root;

  for (int x = 0; x < prefix.length(); x++) {
    //iterating across word and checking if the nect character is a child of the
    //current node
    if (currentNode.getChild(prefix.charAt(x)) == null) {
      return false;
    }
    currentNode = currentNode.getChild(prefix.charAt(x));
  }
  return true;
}



/**
* Traverse the trie involves a recursive exploration of all paths through the
* trie to find and dispense all of the contained words.
*@return iterator over all words contained in the lexicon
*/
public Iterator<String> iterator() {
  //create vector to store words
  Vector<String> lexiconWords = new Vector<String>(wordcount);

  //call recrusive helper method to fill vector
  iterHelper(root, new String(""), lexiconWords);

  //return vector iterator
  return lexiconWords.iterator();
}

//creates a vector of strings(words in our trie)
private void iterHelper(LexiconNode ln, String str, Vector<String> lexiconWords) {

  if (!ln.equals(root)) {
    //add letters to word
    str += ln.getChar();
  }

  //if the letter denotes the end of word, add it to the vector
  if (ln.isAWord()) {
    lexiconWords.add(str);
  }

  if (ln.getChildren() != null) {
    Iterator<LexiconNode> iter = ln.iterator();
    while (iter.hasNext()) {
      iterHelper(iter.next(), str, lexiconWords);
    }
  }
}

/**
* Not implemented
*/
public Set<String> suggestCorrections(String target, int maxDistance) {
  return null;
}

/**
* Not implemented
*/
public Set<String> matchRegex(String pattern) {
   return null;
 }

 /**
 * Provides a string representation of words held in lexicon
 *@return string representation of lexicon
 */
public String toString() {
  String toPrint = "";

  //create iterator object
  Iterator<String> myIter = iterator();

  //print out every word contained in lexicon
  while (myIter.hasNext()) {
    toPrint = toPrint + (myIter.next()) + " ";
  }

  return toPrint;
}

/**
* Main method to test state and functionality of lexicon operations
*/
public static void main(String[] args) {
  // System.out.println("Welcome to the Lexicon Tester. Hit <return> for a list of commands.");
  //
  //
  // LexiconTrie inMain = new LexiconTrie();
  // boolean isTrue = inMain.addWord("hello");
  // boolean doescontain = inMain.containsWord("hello");
  // System.out.println(doescontain);
  // System.out.print(isTrue);
  // System.out.println("");
  // inMain.addWord("goodbye");
  // inMain.addWord("cookie");
  // System.out.println(inMain);

}
//fix contains word and check the last letter is flagged (74)
}
