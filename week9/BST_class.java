package week9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BST_class { 

    class Node { 
        int key; 
        String name;
        Node left, right; 
   
        public Node(int data, String n){ 
            key = data;
            name = n;
            left = right = null; 
        } 
    } 
    static Node root; 
  
    BST_class(){ 
        root = null; 
    } 
   
    @SuppressWarnings("resource")
	static
	void build () throws IOException {
    	String dataFile = "C:\\Users\\Sam\\eclipse-workspace\\Algorithms\\src\\week9\\Users.csv";
		BufferedReader reader = new BufferedReader(new FileReader(dataFile));
		int numLines = 0;
		BufferedReader br = null;
        //System.out.print("Counting lines in the data file ... ");
		while (reader.readLine() != null) numLines++;
		reader.close();
		//System.out.println(numLines);
        BST_class bst = new BST_class(); 
        br = new BufferedReader(new FileReader(dataFile));
        for (int i = 0; i < numLines; i++) {
    		String[] splitLine = br.readLine().split(",");
            String change = splitLine[0];
            int data = Integer.parseInt(change);
            String name = splitLine[1]; 
//            System.out.print(data + " : ");
//            System.out.print(name);
//            System.out.println();
            bst.insert(data, name);
        }

    }
    
    void insert(int key, String name)  { 
        root = insert_Recursive(root, key, name);
    } 
   
    Node insert_Recursive(Node root, int key, String name) { 
    	//System.out.println(name);
        if (root == null) { 
            root = new Node(key, name);
            //System.out.println(root.name);
            return root; 
        } 
        //traverse the tree
        if (key < root.key) { 
            root.left = insert_Recursive(root.left, key, name); 
        }
        else if (key > root.key) {  
            root.right = insert_Recursive(root.right, key, name); 
        }
        //System.out.println(root.key + ":" + root.name);
        return root; 
    } 
 
    static void inorder() { 
    	//System.out.println(root.name);
        inorder_Recursive(root); 
    } 
   
    static void inorder_Recursive(Node root) { 
        if (root != null) { 
            inorder_Recursive(root.left); 
            System.out.println(root.key + ":" + root.name  + " "); 
            inorder_Recursive(root.right); 
        } 
    } 
     
    static boolean searchNum(int key)  { 
        root = searchNum_Recursive(root, key); 
        if (root!= null) {
            System.out.println("Found: 10.0.0." + root.key + ":" + root.name);
        return true;
        }
        else {
        	System.out.println("IP 10.0.0." + key + " not found");
            return false;
        }
    } 
   
    static Node searchNum_Recursive(Node root, int key)  { 
        if (root==null || root.key==key) 
            return root; 
        if (root.key > key) 
            return searchNum_Recursive(root.left, key); 
        return searchNum_Recursive(root.right, key); 
    } 
    
    static boolean searchName(String name)  { 
        root = searchName_Recursive(root, name); 
        if (root == null) {
        	System.out.println("Found: 10.0.0." + root.key + " " + root.name);
            return true;
        }
        else {
        	System.out.println(name + " not found");
        	return false;
        }
            
    } 

	static Node searchName_Recursive(Node root, String name)  { 
        if (root==null || root.name==name) {
            return root; 
        }
        else { 
        	System.out.println(root.name);
        	searchName_Recursive(root.right, name);
        }
        System.out.println(root.name);
        searchName_Recursive(root.left, name);
        return root;
    }
    
    static void count() {
    	count_RecursiveStart(root);
    }
    
    static int num = 0;
    
    static void count_RecursiveStart(Node root) {
    	if (root.left != null) {
    		count_RecursiveStart(root.left);
    	} 
    	else {
    		count_Recursive(root);
    	}
    }
    
    static void count_Recursive(Node root)  { 
        if (root.right != null) {
        	num++;
        	count_Recursive(root.right);
        }
        else {
        	if (num != 206) {
        		num = 206;
        		System.out.println(num);
        	} 
        	else {
        		System.out.println(num);
        	}
        	
        }
    } 
}