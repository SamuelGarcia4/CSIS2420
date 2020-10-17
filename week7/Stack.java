package week7;

public class Stack 
{
	Node head;
 
	public Stack() {
		head = null;
	}
 
	public void push(int data) {
		Node head1 = head;
		head = new Node(data);
		head.next = head1;
	}
 
	public int pop() {
		if (head == null) {
			return -1;
		}
		else {
			int value = head.data;
			head = head.next;
			return value;
		}
	}
 
	public int peek() {
		if (!isEmpty()) { 
			return head.data; 
		} 
		else { 
			System.out.println("Stack is empty"); 
			return -1; 
		} 
	}
	
	public int size() {
		Node temp = head;
		int nodeCount = 0;
		while (temp != null) {
			temp = temp.next;
			nodeCount++;
		}
		return nodeCount;
	}
	
	public boolean isEmpty() {
		return head == null;
	}

	public void printStack(Node head)  {
		Node temp = head;
		System.out.print("\nStack: ");
		while (temp != null) {
			
			System.out.format("%d ", temp.data);
			temp = temp.next;
			
		}
		System.out.println();
	}

}