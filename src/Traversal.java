//Andreas Wisofschi
//Student # 250975913
//CS-1027, John Barron
//December &, 2018

import java.util.*;
import java.io.*;

public class Traversal {
	// private variable accessible to all methods
	private static BinaryTreeNode<String> root = null;
	private static BinaryTreeNode<String> root2 = null;
	private static BinaryTreeNode<String> root3 = null;
	private static BinaryTreeNode<String> node = null;
	private static BinaryTreeNode<String> node2 = null;
	private static BinaryTreeNode<String> node3 = null;
	private static final boolean DEBUG = false;

	public static void main(String[] args) {
		System.out.println("Number of arguments: " + args.length);
		if (args.length != 1 && args.length != 3) {
			System.out.println("\nUsage: 1 or 3 arguments required\n");
			System.out.println("1 argument: java Traversal <listn.txt> for n=0:6");
			System.out.println("Example: java Traversal list0.txt\n");
			System.out.println("3 arguments: java Traversal <listn.txt> <listm.txt> <root string> ");
			System.out.println("for n,m=0:6, n,m can be the same");
			System.out.println("Example: java Traversal list0.txt list1.txt \"Q\"");
			System.out.println("\n");
		}

		if (args.length == 1 || args.length == 3) {
			String listFileName = args[0];
			// Create listReader Object
			InStringFile listReader = new InStringFile(listFileName);
			StringTokenizer tokenizer;
			String line = listReader.read();
			tokenizer = new StringTokenizer(line);
			int numTokens = tokenizer.countTokens();
			for (int i = 0; i < numTokens; i++) {
				String element = tokenizer.nextToken();
				if (DEBUG)
					System.out.println("element read: " + element);
				node = new BinaryTreeNode<String>(element);
				root = insertInBinaryTree(root, node);
			}
			listReader.close();

			int n = countBinaryTreeNodes(root);
			System.out.println("\n\nNumber of nodes in the tree:" + n);
			int h = findHeight(root);
			System.out.format("Height of tree: %d\n", h);
			System.out.format("Number of nodes in complete binary tree with height %d: %d\n", h,
					(int) (Math.pow(2.0, h + 1.0) - 1));
			PrettyPrintTree.printNode(root);
			System.out.println("inOrder traversal of tree: " + inOrder(root, ""));
			System.out.println("preOrder traversal of tree: " + preOrder(root, ""));
			System.out.println("postOrder traversal of tree: " + postOrder(root, ""));
			System.out.println("levelOrder traversal of tree: " + levelOrder(root, ""));
		}

		if (args.length == 3) // args.length==1 cose has already run
		{
			String listFileName = args[1];
			String rootString = args[2];
			// Create listReader Object
			InStringFile listReader = new InStringFile(listFileName);
			StringTokenizer tokenizer;
			String line = listReader.read();
			tokenizer = new StringTokenizer(line);
			int numTokens = tokenizer.countTokens();
			for (int i = 0; i < numTokens; i++) {
				String element = tokenizer.nextToken();
				if (DEBUG)
					System.out.println("element read: " + element);
				node2 = new BinaryTreeNode<String>(element);
				root2 = insertInBinaryTree(root2, node2);
			}
			listReader.close();

			int n2 = countBinaryTreeNodes(root2);
			System.out.println("\n\nNumber of nodes in the tree:" + n2);
			int h2 = findHeight(root2);
			System.out.format("Height of tree: %d\n", h2);
			System.out.format("Number of nodes in complete binary tree with height %d: %d\n", h2,
					(int) (Math.pow(2.0, h2 + 1.0) - 1));
			PrettyPrintTree.printNode(root2);
			System.out.println("inOrder traversal of tree: " + inOrder(root2, ""));
			System.out.println("preOrder traversal of tree: " + preOrder(root2, ""));
			System.out.println("postOrder traversal of tree: " + postOrder(root2, ""));
			System.out.println("levelOrder traversal of tree: " + levelOrder(root2, ""));

			System.out.println("\n\n");
			System.out.println("-----------------------------------------------------");
			System.out.println(" 1st and 2nd trees become the left and right subtrees");
			System.out.println(" of the 3rd tree with root labeled " + rootString);
			System.out.println("-----------------------------------------------------");
			System.out.println("The value of rootString is " + rootString);
			root3 = joinTrees(root, root2, rootString);
			int n3 = countBinaryTreeNodes(root3); // could compute n+n2+1
			System.out.println("Number of nodes in the tree:" + n3);
			int h3 = findHeight(root3);
			System.out.format("Height of tree: %d\n", h3); // could compute as max(h,h2)+1
			System.out.format("Number of nodes in complete binary tree with height %d: %d\n", h3,
					(int) (Math.pow(2.0, h3 + 1.0) - 1));
			PrettyPrintTree.printNode(root3);
			System.out.println("inOrder traversal of tree: " + inOrder(root3, ""));
			System.out.println("preOrder traversal of tree: " + preOrder(root3, ""));
			System.out.println("postOrder traversal of tree: " + postOrder(root3, ""));
			System.out.println("levelOrder traversal of tree: " + levelOrder(root3, ""));
		} // if(args.length==4)

	} // main

	////////////////////////////////////////////////////
	// Join 2 binary trees into a 3D binary tree
	// The leftsubtree is root, the right subtree is root2
	// and the new root's label is rootString
	////////////////////////////////////////////////////
	public static BinaryTreeNode<String> joinTrees(BinaryTreeNode<String> leftSubtree,
			BinaryTreeNode<String> rightSubtree, String rootString) {
		
		root = new BinaryTreeNode<String>(rootString);			//Here a string variable via BinaryTreeNode is created called root.
		root.setLeft(leftSubtree);								////Because when searching through the binary tree the program must go left to right thus this line of code sets left of the two. 
		root.setRight(rightSubtree);							////here the right side of the tree is set. 

		return root;
	}

	////////////////////////////////////////////////////
	// insert a BinaryTreeNode<String> node in a binary tree
	// rooted by BinaryTreeNode<String> root
	////////////////////////////////////////////////////
	public static BinaryTreeNode<String> insertInBinaryTree(BinaryTreeNode<String> root, BinaryTreeNode<String> node) {
		if (root == null) {
			root = node;
			if (DEBUG)
				System.out.format("\nroot set to node: %s\n", root.getElement());
			return (root);
		} else if (node.getElement().compareTo(root.getElement()) <= 0) {
			if (DEBUG)
				System.out.format("\n%s <= %s using root.left\n", node.getElement(), root.getElement());
			root.setLeft(insertInBinaryTree(root.getLeft(), node));
		} else {
			if (DEBUG)
				System.out.format("\n%s > %s using root.right\n", node.getElement(), root.getElement());
			root.setRight(insertInBinaryTree(root.getRight(), node));
		}
		return (root);
	}

	////////////////////////////////////////////////////
	// The height of a tree is the length of the path from
	// the root to the deepest node in the tree
	// An empty tree has height -1
	// A tree with just 1 node (the root) has height 0
	////////////////////////////////////////////////////
	public static int findHeight(BinaryTreeNode<String> node) {
		if (node == null)			//Here this line of code, if the binary tree node is empty, the program returns -1.
			return -1;

		if (node.getLeft() == null && node.getRight() == null)					// A tree with just one node (the base case) has height 0 and it is declared here.
			return 0;

		int left = findHeight(node.getLeft());					//In simple terms these next two lines, it finds left and right subtrees recursively and add 1 to their maximum
		int right = findHeight(node.getRight());				//right height of the tree is stored into "right" here.

		if (left > right)					//Here if the left specific height is larger then the right, it returns the height +1.
		{
			return left + 1;
		}

		else			//If left is not larger then right, then the right height is returned, +1.
			return right + 1;

	}

	////////////////////////////////////////////////////
	// The number of nodes in a binary tree is the
	// number of nodes in the root's left subtree
	// plus the number of nodes in its right subtree
	// plus one (for the root itself).
	////////////////////////////////////////////////////
	public static int countBinaryTreeNodes(BinaryTreeNode<String> root) {
		
		if (root == null)			//Here if the binary tree nodes are empty (empty tree), 0 is returned
			return (0);

		int count = 0;					//integer count is used to literally count th number of nodes. 
										// by counting the number of nodes in the left and right non-null subtrees of a node and then adding 1 for that node.

		count = count + countBinaryTreeNodes(root.getLeft());			//Here all left side nodes are counted and stored 
		count = count + countBinaryTreeNodes(root.getRight());			//Here all right side nodes are counted and added together.
		return count + 1;					//total count is then added between all TOTAL right and left nodes and then the program returns a total of the tree's nodes. 
	}

	////////////////////////////////////////////////////
	// inOrder travesal of binary tree, stg contains
	// the node in inOrder order.
	////////////////////////////////////////////////////
	public static String inOrder(BinaryTreeNode<String> root, String stg) {
		stg = "";			// a String stg variable that contains the traversal of the nodes
		if (root != null) {			////If root are not done.

			stg += inOrder(root.getLeft(), stg);				//The InOrder traversal is also known as left-node-right or left-root-right traversal
			stg += root.getElement()+" ";						//Because the program must go from left to right, this code begings by getting the left then right stg
			stg += inOrder(root.getRight(), stg);
			
		}
		return stg;
	} // inOrder

	////////////////////////////////////////////////////
	// preOrder travesal of binary tree, stg contains
	// the node in preOrder order.
	////////////////////////////////////////////////////
	public static String preOrder(BinaryTreeNode<String> root, String stg) {
		stg = "";			// a String stg variable that contains the traversal of the nodes
		
		if (root != null) {								//If root are not done. 
			stg += root.getElement()+" ";				//form of graph traversal and refers to the process of visiting (checking and/or updating) each node in a tree data structure, exactly once
			stg += preOrder(root.getLeft(), stg);		//Simlarly to inorder the program gets the roots beginning from left to right.
			stg += preOrder(root.getRight(), stg);

		}
		return stg;
	} // preOrder

	////////////////////////////////////////////////////
	// postOrder travesal of binary tree, stg contains
	// the node in postOrder order.
	////////////////////////////////////////////////////
	public static String postOrder(BinaryTreeNode<String> root, String stg) {
		stg = "";			 //a String stg variable that contains the traversal of the nodes
		if (root != null) {			//If root are not done.

			stg += postOrder(root.getLeft(), stg);					//In post order traversal, you first visit left subtree. 
																	//then right subtree and finally you print the value of node or root.
			stg += postOrder(root.getRight(), stg);
			stg += root.getElement()+" ";
		}
		return stg;
	} // postOrder

	////////////////////////////////////////////////////
	// levelOrder travesal of binary tree, stg contains
	// the node in levelOrder order.
	////////////////////////////////////////////////////
	public static String levelOrder(BinaryTreeNode<String> root, String stg) {
		if (root == null)
			return stg;
		LinkedQueue<BinaryTreeNode<String>> queue = new LinkedQueue<BinaryTreeNode<String>>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			BinaryTreeNode<String> node = queue.dequeue();
			stg = stg + node.getElement() + " ";
			if (node.getLeft() != null)
				queue.enqueue(node.getLeft());
			if (node.getRight() != null)
				queue.enqueue(node.getRight());
		}
		return stg;
	}

} 