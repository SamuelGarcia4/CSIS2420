package week7;

import java.util.Stack;

public class stackAPI {
	
	public static void main (String[] args) {
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(11);
		stack.push(15);
		stack.push(19);
		System.out.println(stack);
		System.out.println("Stack Size: " + stack.size());
		System.out.println("First in Stack: " + stack.peek());
		System.out.println("Pop Pop: " + stack.pop());
		System.out.println(stack);
		stack.push(23);
		System.out.println(stack);
		System.out.println("Stack Size: " + stack.size());
		System.out.println("Pop Pop: " + stack.pop());
		System.out.println(stack);
		System.out.println("Stack Size: " + stack.size());
	}
}