package week7;

public class stackDemo {

	public static void main(String[] args) {

		Stack tS = new Stack();
		tS.push(11);
		tS.push(15);
		tS.push(19);
		tS.printStack(tS.head);
		System.out.println("Stack Size: " + tS.size());
		System.out.println("First in Stack: " + tS.peek());
		System.out.println("Pop Pop: " + tS.pop());
		tS.printStack(tS.head);
		tS.push(23);
		tS.printStack(tS.head);
		System.out.println("Stack Size: " + tS.size());
		System.out.println("Pop Pop: " + tS.pop());
		tS.printStack(tS.head);
		System.out.println("Stack Size: " + tS.size());
		

	}
}