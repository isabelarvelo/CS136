import structure5.*;
import java.util.Iterator;

/**
* An implementation of a basic stack-based language PostScript interpreter.
* The interpreter takes in a set of immutable objects that contain a double,
* boolean, or symbol and implements basic math operations by popping operands
* off the stack and pushing on the result.
*/


public class Interpreter {

	private StackList<Token> stack;
	private SymbolTable table;

	/**
	*Constructs an Interpreter object.
	*/
	public Interpreter() {
		stack = new StackList<Token>();
		table = new SymbolTable();
	}

	/**
	*Reads in and interprets tokens to carry out appropriate mathematical
	*procedures.
	*@param r Reader parses postscript files and generates a stream of Tokens
	*@post - user inputs are evaluated on the stack if possible
	*/
	public void interpret(Reader r) {
		Token t;
		//look through file and turn each thing into token
		while (r.hasNext()) {
			t = (Token)r.next();
			if (t.isSymbol() && t.getSymbol().equals("quit")) {
				break;
			}
			//case handling to decide what to do with token
			switch (t.kind()) {
				case Token.NUMBER_KIND:
				case Token.BOOLEAN_KIND:
				case Token.PROCEDURE_KIND:
				push(t);
				break;
				case Token.SYMBOL_KIND:
				processSymbol(t);
				break;
				default:
				Assert.fail("Unknown token types");


			}
		}
	}
	/**
	*if the token is a symbol, we process it
	*@pre t is a token of type Symbol
	*@post t is procesessed and symbol is executed if possible
	*/
	public void processSymbol(Token t){
		//check if symbol is predetermined method and if so, execute it
		boolean tocheck = menu(t);
		//if it is a predetermined operation, execute and move on
		if (!tocheck) {
			//if defining a new symbol, push it onto stack
			if (t.getSymbol().substring(0, 1).equals("/")) {
				push(t);
			}
			else{
				//retrieve symbol from symbol table
				if (table.contains(t.getSymbol()))
				{
					Token symbolName = table.get(t.getSymbol());

					//check if its in the table
					//if token is a procedure, treat it as its own stack and interpet it
					if (symbolName.isProcedure()) {
						interpret(new Reader(symbolName));
					} else {
						//pushing Symbol's value from Symbol table on stack
						push(symbolName);
					}
				}
				else{
					System.out.print("Symbol not found");
				}
			}
		}
	}

	/**
	*Determines if token is a predetermined mathematical existing Operation and if it is,
	*the appropriate method is called to execute the operation.
	*@param t symbol token
	*@return true if symbol token is a predetermined method
	*@pre - t is a symbol token
	*@post -if t is a predetermined method it will add the appropriate values onto the stack.
	*If it isn't a predetermined method menu wont put anything onto the stack.
	*/
	public boolean menu(Token t) {
		boolean existingOperation = true;
		switch (t.getSymbol()) {
			case "lt":
			lt();
			break;
			case "rev":
			reverse();
			break;
			case "copy":
			copy();
			break;
			case "if":
			checkif();
			break;
			case "pop":
			stack.pop();
			break;
			case "add":
			add();
			break;
			case "pstack":
			pstack();
			break;
			case "ptable":
			ptable();
			break;
			case "sub":
			sub();
			break;
			case "div":
			div();
			break;
			case "eq":
			eq();
			break;
			case "mul":
			mul();
			break;
			case "dup":
			dup();
			break;
			case "exch":
			exch();
			break;
			case "ne":
			ne();
			break;
			case "def":
			def();
			break;
			default:
			existingOperation = false;
		}
		return existingOperation;
	}

	/**
	*Command that prints symbol table to console
	*/
	public void ptable() {
		System.out.print(table);
	}

	/**
	*Compares top two elements of stack and returns if the older value is greater
	*than the newer one
	*@pre top two elements of stack are numbers
	*@post boolean value pushed onto the stack
	*/
	public void lt()
	{
		Token token1 = stack.pop();
		Token token2 = stack.pop();
		if (isValid(token1, token2))
		{
		    //$ It would have been simpler
		    //$ to just push (new Token(token1.getNumber() > token2.getNumber())
		    //$ onto the stack directly.
			//$ On top of that, this returns "true" if the numbers are
			//$ equal, whereas it should return "false"
			if (Double.compare(token2.getNumber(), token1.getNumber())>0){
				Token answer = new Token(false);
				stack.push(answer);
			}
			else{
				Token answer = new Token(true);
				stack.push(answer);
			}
		}
		else {
			System.out.print("Cannot preform less than operation");
		}
	}
	/**
	*Does the procedure if the previous value on the stack is true
	*@pre top element on the stack is a procedure and the second to top one is a
	* boolean value
	*@post procedure is pushed onto the stack if the previous value was true
	*/
	public void checkif(){
		Token token1 = stack.pop(); //procedure
		Token token2 = stack.pop(); //boolean value
		if(token2.isBoolean())
		{
		    //$ No need to have a nested if statement. If
		    //$ token2.getBoolean() is not a boolean, then
		    //$ the if statement would evaluate as false
		    //$ anyway when using &&. Also, checking if a boolean is equal
		    //$ to a boolean can be skipped
			if(token2.getBoolean()==true)
			{
				Reader symbolReader = new Reader(token1);
				interpret(symbolReader);
			}
		}
		else{
			System.out.print("Cannot preform if statement");
		}
	}

	/**
	*Pushes a token onto the stack
	*@param element the element that is to be pushed on to the top of the stack
	*/
	public void push(Token element) {
		stack.push(element);
	}

	/**
	*Command that causes that prints entire stack to the console
	*/
	public void pstack() {
		for (Token t: stack) {
			System.out.println(t);
		}

	}

	/**
	*Checks to see if the top two elements of stack are numbers
	*@param  token1 first operand
	*@param  token2 second operand
	*@return True iff both operands are numbers
	*/
	public boolean isValid(Token token1, Token token2) {
		boolean valid = false;
		//$ It's enough to just return your if condition
		if (token1.isNumber() && token2.isNumber()) {
			valid = true;
		}
		return valid;
	}

	/**
	*Pops top two elements of stack and pushes their sum back onto stack
	*@pre top two elements of stack are numbers
	*@post top element of the stack is the sum of previous top two elements
	*/
	public void add() {
		//$ should check to make sure there are two elements;
		//$ this will give an uncaught error otherwise
		//$ (should probably be tied into isValid())
		Token token1 = stack.pop();
		Token token2 = stack.pop();

		if (isValid(token1, token2)) {
			double val1 = token1.getNumber();
			double val2 = token2.getNumber();
			Token answer = new Token(val2 + val1);
			stack.push(answer);
		} else {
			System.out.println("Unable to perform add operation on this stack");
		}
	}

	/**
	*Pops top two elements of stack and pushes their difference back onto stack
	*@pre top two elements of stack are numbers
	*@post top element of the stack is the difference of previous top two elements
	*/
	public void sub() {

		Token token1 = stack.pop();
		Token token2 = stack.pop();

		if (isValid(token1, token2)) {
			double val1 = token1.getNumber();
			double val2 = token2.getNumber();
			Token answer = new Token(val2 - val1);
			stack.push(answer);
		} else {
			System.out.println("Unable to preform subtract operations on this stack");
		}

	}

	/**
	*Pops top two elements of stack and pushes their product back onto stack
	*@pre top two elements of stack are numbers
	*@post top element of the stack is the product of previous top two elements
	*/
	public void mul() {

		Token token1 = stack.pop();
		Token token2 = stack.pop();

		if (isValid(token1, token2)) {
			double val1 = token1.getNumber();
			double val2 = token2.getNumber();
			Token multi = new Token(val2 * val1);
			stack.push(multi);
		} else {
			System.out.println("Unable to preform multiply operations on this stack");
		}

	}

	/**
	*Pops top two elements of stack and pushes their quotient back onto stack
	*@pre top two elements of stack are numbers
	*@post top element of the stack is the quotient of previous top two elements
	*/
	public void div() {
		Token token1 = stack.pop();
		Token token2 = stack.pop();

		if (isValid(token1, token2)) {
			double val1 = token1.getNumber();

			double val2 = token2.getNumber();

			Token answer = new Token(val2 / val1);

			stack.push(answer);
		} else {
			System.out.println("Unable to preform divide operations on this stack");
		}

	}

	/**
	*Duplicates top element of stack
	*@pre stack is not empty
	*@post top two elements in the stack are the same
	*/
	public void dup() {
		stack.push(stack.peek());
	}

	/**
	*Exchanges last two elements of stack
	*@pre stack has size of at least 2
	*/
	public void exch() {

		Token val1 = stack.pop();
		Token val2 = stack.pop();
		stack.push(val1);
		stack.push(val2);

	}

	/**
	*Pops top two elements off the stack and pushes on whether they are equal
	*@pre stack has size of at least 2
	*@post top value of stack is a boolean
	*/
	public void eq() {

		Token val1 = stack.pop();
		Token val2 = stack.pop();
		Token topush = new Token(val1.equals(val2));
		stack.push(topush);

	}

	/**
	*Pops top two elements off the stack and pushes on whether they are not equal
	*@pre stack has size of at least 2
	*@post top value of stack is a boolean
	*/
	public void ne() {

		Token val1 = stack.pop();
		Token val2 = stack.pop();
		Token topush = new Token(!val1.equals(val2));
		stack.push(topush);

	}

	/**
	*Defines a new symbol and adds it to the symbol table
	*@pre symbol is not already defined in symbol table
	*@post symbol table has a new key value association
	*/
	public void def() {
		//value that will be associated with symbol
		Token t = stack.pop();
		//string that will be associated with symbol
		String s = stack.pop().getSymbol().substring(1);

		table.add(s, t);

	}

	/**
	*Creates a copy of stack for fun
	*/

	public StackList<Token> copy() {
		StackList<Token> copyStack = new StackList<Token>();
		StackList<Token> temp = new StackList<Token>();

		while (!stack.isEmpty()) {
			temp.push(stack.pop());
		}

		//temp holds all of the elements of the original stack in reverse order

		while (!temp.isEmpty()) {
			Token item = temp.pop();
			stack.push(item);
			copyStack.push(item);
		}

		return copyStack;

	}

	/**
	*Reverses stack for fun
	*/
	public void reverse() {

		//temporary stack
		StackList<Token> temp = new StackList<Token>();

		//leave method if secretStack is empty
		if (stack.isEmpty()) {
			return;
		}

		//After ith iteration, the first i elements of the stack are in reversed
		//order and the last n-i elements are still in their original orientation

		for (int i = 0; i < stack.size(); i++) {
			Token item = stack.pop();
			for (Token t: stack) {
				temp.push(t);
			}

			stack.push(item);

			for (Token t: temp) {
				stack.push(t);
			}

		}

	}

	/**
	*Main method will implement a PostScript-based calculator that reads in Tokens
	*and performs corresponding mathematical operations
	*/

	public static void main(String[] args) {
		//create interpreter
		Interpreter maininterpret = new Interpreter();
		//create reader to pass into interpret method
		Reader r = new Reader();
		//interpret input stream
		maininterpret.interpret(r);



	}

}
