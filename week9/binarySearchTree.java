package week9;

import java.io.IOException;
import java.util.Scanner;

public class binarySearchTree{

	public static void main(String[] args) throws IOException { 
		start();
     } 
	
	public static void start() throws IOException {
		System.out.print("-------------------------" + "\r\n" +
				"1 Build Users Tree" + "\r\n" +
				"2 Find by IP Address" + "\r\n" +
				"3 Find by Username" + "\r\n" +
				"4 Report Number of Nodes" + "\r\n" +
				"5 Print Entire Tree" + "\r\n" +
				"6 Exit" + "\r\n" +
				"-------------------------" + "\r\n" +
				"Enter 1, 2, 3, 4, 5 or 6: ");
        @SuppressWarnings("resource")
		Scanner keyIn = new Scanner(System.in);
        String c = keyIn.next();
        int key = Integer.parseInt(c);
		switchUp(key);
	}
	
	public static void switchUp(int key) throws IOException {
		switch (key) {
		  case 1:
			 BST_class.build();
		    break;
		  case 2:
			System.out.println("Enter last three digits of IP address");
		    @SuppressWarnings("resource")
		    Scanner keyIn = new Scanner(System.in);
		    String c = keyIn.next();
		    int num = Integer.parseInt(c);
		    BST_class.searchNum(num);
		    break;
		  case 3:
			System.out.println("Enter the name you would like to search");
		    @SuppressWarnings("resource")
		    Scanner keyIn1 = new Scanner(System.in);
		    @SuppressWarnings("resource")
		    String name = keyIn1.next();
		    BST_class.searchName(name);
		    break;
		  case 4:
		    BST_class.count();
		    break;
		  case 5:
			BST_class.inorder();
		    break;
		  case 6:
			System.exit(0);
		    break;
		}
		start();
	}
}

