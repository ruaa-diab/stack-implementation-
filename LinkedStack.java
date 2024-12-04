import java.util.Stack;

import javax.print.attribute.SupportedValuesAttribute;

public class LinkedStack<T extends Comparable<T>> implements Stackable<T> {

	
	private Node<T> topNode;
	
	@Override
	 public void push(T data) {
		 Node<T> newNode = new Node<T>(data);
		 newNode.setNext(topNode);
		 topNode = newNode;
		 }

	@Override
	public T pop() {
		 T toDel = topNode.getData();
		 if(topNode != null)
		 topNode = topNode.getNext();
		 return toDel;
		 }
	
	public int length() {
		 int length = 0;
		 Node<T> curr = topNode;
		 while (curr != null) {
		 length++;
		 curr = curr.getNext();
		 }
		 return length;
		 } 

	
	@Override
	public boolean isEmpty() { return (topNode == null);
	}
	@Override
	 public void clear() {
		topNode = null; 
	 }

	@Override
	public T peek() { 
		if (isEmpty()) return null;
		return topNode.getData();
	}
	
	
	
	
	
	
	public static int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();
 
         // Stack for numbers: 'values'
        LinkedStack<Integer> values = new
                              LinkedStack<Integer>();
 
        // Stack for Operators: 'ops'
       LinkedStack<Character> ops = new
                              LinkedStack<Character>();
 
        for (int i = 0; i < tokens.length; i++)
        {
             
            // Current token is a 
            // whitespace, skip it
            if (tokens[i] == ' ')
                continue;
 
            // Current token is a number, 
            // push it to stack for numbers
            if (tokens[i] >= '0' && 
                 tokens[i] <= '9')
            {
                StringBuffer sbuf = new
                            StringBuffer();
                 
                // There may be more than one 
                // digits in number
                while (i < tokens.length && 
                        tokens[i] >= '0' && 
                          tokens[i] <= '9') {
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.
                         toString()));
                }
                  i--;
            }
 
            // Current token is an opening brace, 
            // push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            // Closing brace encountered, 
            // solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(') {
                  values.push(applyOp(ops.pop(), 
                                   values.pop(), 
                                 values.pop()));
                }
                ops.pop();
            }
 
            // Current token is an operator.
            else if (tokens[i] == '+' || 
                     tokens[i] == '-' ||
                     tokens[i] == '*' || 
                        tokens[i] == '/')
            {
                
                while (!ops.isEmpty() && 
                       hasPrecedence(tokens[i], 
                                    ops.peek())) {
                  values.push(applyOp(ops.pop(), 
                                   values.pop(),
                                 values.pop()));}
 
                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }
 
        // Entire expression has been 
        // parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.isEmpty()) {
            values.push(applyOp(ops.pop(), 
                             values.pop(), 
                           values.pop()));
        }
        // Top of 'values' contains 
        // result, return it
        return values.pop();
    }
 
  
    public static boolean hasPrecedence(
                           char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && 
            (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
 
    public static int applyOp(char op, 
                           int b, int a)
    {
        switch (op)
        {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            if (b == 0)
                throw new
                UnsupportedOperationException(
                      "Cannot divide by zero");
            return a / b;
        }
        return 0;
    }
 
	
	
	}
