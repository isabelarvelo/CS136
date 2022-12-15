import structure5.*;
import java.util.Iterator;

/*
* A class supporting a lexicon trie element.  Each element
* contains a letter, end of word flag, and maintains an ordered vector of
* children that represent the next letters in words they represent.
*/
class LexiconNode implements Comparable<LexiconNode> {

  /* single letter stored in this node */
  protected char letter;

  /* true if this node ends some path that defines a valid word */
  protected boolean isWord;

  /* a data structure for keeping track of the children of this LexiconNode */
  //provides necessary functionality with minimal overhead
  protected OrderedVector<LexiconNode> children;


  /**
  * Constructor to create a lexicon node object
  *@param letter letter stored in lexicon node
  *@param isWord true if this node ends some path that defines a valid word
  */
  public LexiconNode(char letter, boolean isWord) {
    this.isWord = isWord;
    this.letter = letter;
    children = new OrderedVector<LexiconNode>();
  }

  /**
  * Compares this LexiconNode to another by comparing the characters stored
  * at the Lexicon
  *@param o other LexiconNode that we are comparing this node to
  *@return integer less than 0 is if the char in this node comes before char in
  * other node in the alphabet and integer greater than 0 if the char in this node
  * comes after char in other node in the alphabet
  */
  public int compareTo(LexiconNode o) {
    if (o.equals(null)) {
      // $ Do you really want to a null node to return the same thing as when the
      // $ nodes are equal?
      return 0;
    }
    return letter - o.getChar();
  }

  //helper method that returns letter stored by this LexiconNode
  protected char getChar() {
    return letter;
  }

  /**
  * Indicates whether or not this node denotes the end of a word
  *@post returns true if this node is the end of a word
  */
  public boolean isAWord() {
    return isWord;
  }

  /**
  * Returns vector of children nodes in alphabetical order of letters they store
  *@return ordered vector that contains children nodes of this node in
  *alphabetical order
  */
  public OrderedVector<LexiconNode> getChildren() {
    return children;
  }

  /**
  * Sets this letter to denote the end of a word
  *@param isTrue boolean value that indicates whether char contained by node is
  * last letter in a word held in the lexicon
  *@return boolean that indicates end of word
  */
  public void setFlag(boolean isTrue) {
    isWord = isTrue;
  }

  /**
  * Adds LexiconNode child to correct position in child data structure
  *@param ln child node to be added to children of this node
  *@post ln is a child of this node
  */
  public void addChild(LexiconNode ln) {

    //checking if node already has this node as a child
    // $ You do not really need to check this here, since you already check if
    // $ getChild is null before calling addChild in LexiconTrie
    if (getChild(ln.getChar()) == null) {
      //do not want to add null nodes
      //$ This check should be ln != null, since, if ln is null, .equals cannot
      //$ be called on ln....
      if (!ln.equals(null)) {
        //add node to vector of children in its correct location
        children.add(ln);
      }
    }
  }


  /**
  * Get LexiconNode child for 'ch' out of child data structure
  *@param ch letter stored in child we would like to access
  *@return child LexiconNode that stores char ch
  */
  public LexiconNode getChild(char ch) {
    for (LexiconNode n : children) {
      if (n.getChar() == ch) {
        return n;
      }
    }
    return null;
  }

  /**
  * Removes LexiconNode child for 'ch' from child data structure
  *@param ch character held in child node we would like to remove
  *@pre node has at least one child
  *@post ch is not contained by any of this node's children
  */
  public void removeChild(char ch) {
    for (LexiconNode n : children) {
      if (n.getChar() == ch) {
        children.remove(n);
      }
    }
  }

  /**
  * An Iterator that iterates over children in alphabetical order
  *@return iterator over all nodes contained in children vector
  */
  public Iterator<LexiconNode> iterator() {
    return children.iterator();
  }


  /**
  * Returns a string Representation of the state of the node
  *@return String representation of state of node
  */
  public String toString() {
    String toPrint = "";
    for (LexiconNode child: children) {
      toPrint = toPrint + "char" + child.getChar() + "\n" + "IsWord" + child.isAWord();
    }
    return "Character" + letter + "\n" + "isWord" + isWord + "\n"
    + "Vector of children" + toPrint;

  }

  /**
  * Main method to test state and functionality of lexicon node implementation
  */
  public static void main(String[] args) {

  }
}
