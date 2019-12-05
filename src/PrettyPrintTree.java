import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrettyPrintTree {

	public static <T> void printNode(BinaryTreeNode<T> root) {
		int maxLevel = PrettyPrintTree.maxLevel(root);

		printNodeInternal(Collections.singletonList(root), 1, maxLevel);
	}

	private static <T> void printNodeInternal(List<BinaryTreeNode<T>> nodes, int level, int maxLevel) {
		if (nodes.isEmpty() || PrettyPrintTree.isAllElementsNull(nodes))
			return;

		int floor = maxLevel - level;
		int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int firstSpaces = (int) Math.pow(2, (floor)) - 1;
		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

		PrettyPrintTree.printWhitespaces(firstSpaces);

		List<BinaryTreeNode<T>> newBinaryTreeNodes = new ArrayList<BinaryTreeNode<T>>();
		for (BinaryTreeNode<T> node : nodes) {
			if (node != null) {
				System.out.print(node.getElement());
				newBinaryTreeNodes.add(node.getLeft());
				newBinaryTreeNodes.add(node.getRight());
			} else {
				newBinaryTreeNodes.add(null);
				newBinaryTreeNodes.add(null);
				System.out.print(" ");
			}
			PrettyPrintTree.printWhitespaces(betweenSpaces);
		}
		System.out.println("");

		for (int i = 1; i <= endgeLines; i++) {
			for (int j = 0; j < nodes.size(); j++) {
				PrettyPrintTree.printWhitespaces(firstSpaces - i);
				if (nodes.get(j) == null) {
					PrettyPrintTree.printWhitespaces(endgeLines + endgeLines + i + 1);
					continue;
				}

				if (nodes.get(j).getLeft() != null)
					System.out.print("/");
				else
					PrettyPrintTree.printWhitespaces(1);

				PrettyPrintTree.printWhitespaces(i + i - 1);

				if (nodes.get(j).getRight() != null)
					System.out.print("\\");
				else
					PrettyPrintTree.printWhitespaces(1);

				PrettyPrintTree.printWhitespaces(endgeLines + endgeLines - i);
			}

			System.out.println("");
		}

		printNodeInternal(newBinaryTreeNodes, level + 1, maxLevel);
	}

	private static void printWhitespaces(int count) {
		for (int i = 0; i < count; i++)
			System.out.print(" ");
	}

	private static <T> int maxLevel(BinaryTreeNode<T> node) {
		if (node == null)
			return 0;

		return Math.max(PrettyPrintTree.maxLevel(node.getLeft()), PrettyPrintTree.maxLevel(node.getRight())) + 1;
	}

	private static <T> boolean isAllElementsNull(List<T> list) {
		for (Object object : list) {
			if (object != null)
				return false;
		}

		return true;
	}

}

class PrettyPrintTreeTest {

	private static BinaryTreeNode<String> test1() {
		BinaryTreeNode<String> root = new BinaryTreeNode<String>("2");
		BinaryTreeNode<String> n11 = new BinaryTreeNode<String>("7");
		BinaryTreeNode<String> n12 = new BinaryTreeNode<String>("5");
		BinaryTreeNode<String> n21 = new BinaryTreeNode<String>("2");
		BinaryTreeNode<String> n22 = new BinaryTreeNode<String>("6");
		BinaryTreeNode<String> n23 = new BinaryTreeNode<String>("3");
		BinaryTreeNode<String> n24 = new BinaryTreeNode<String>("6");
		BinaryTreeNode<String> n31 = new BinaryTreeNode<String>("5");
		BinaryTreeNode<String> n32 = new BinaryTreeNode<String>("8");
		BinaryTreeNode<String> n33 = new BinaryTreeNode<String>("4");
		BinaryTreeNode<String> n34 = new BinaryTreeNode<String>("5");
		BinaryTreeNode<String> n35 = new BinaryTreeNode<String>("8");
		BinaryTreeNode<String> n36 = new BinaryTreeNode<String>("4");
		BinaryTreeNode<String> n37 = new BinaryTreeNode<String>("5");
		BinaryTreeNode<String> n38 = new BinaryTreeNode<String>("8");

		root.setLeft(n11);
		root.setRight(n12);

		n11.setLeft(n21);
		n11.setRight(n22);
		n12.setLeft(n23);
		n12.setRight(n24);

		n21.setLeft(n31);
		n21.setRight(n32);
		n22.setLeft(n33);
		n22.setRight(n34);
		n23.setLeft(n35);
		n23.setRight(n36);
		n24.setLeft(n37);
		n24.setRight(n38);

		return root;
	}

	private static BinaryTreeNode<String> test2() {
		BinaryTreeNode<String> root = new BinaryTreeNode<String>("2");
		BinaryTreeNode<String> n11 = new BinaryTreeNode<String>("7");
		BinaryTreeNode<String> n12 = new BinaryTreeNode<String>("5");
		BinaryTreeNode<String> n21 = new BinaryTreeNode<String>("2");
		BinaryTreeNode<String> n22 = new BinaryTreeNode<String>("6");
		BinaryTreeNode<String> n23 = new BinaryTreeNode<String>("9");
		BinaryTreeNode<String> n31 = new BinaryTreeNode<String>("5");
		BinaryTreeNode<String> n32 = new BinaryTreeNode<String>("8");
		BinaryTreeNode<String> n33 = new BinaryTreeNode<String>("4");

		root.setLeft(n11);
		root.setRight(n12);

		n11.setLeft(n21);
		n11.setRight(n22);

		n12.setRight(n23);
		n22.setLeft(n31);
		n22.setRight(n32);

		n23.setLeft(n33);

		return root;
	}

	private static BinaryTreeNode<String> test3() {
		BinaryTreeNode<String> root = new BinaryTreeNode<String>("A");
		BinaryTreeNode<String> nB = new BinaryTreeNode<String>("B");
		BinaryTreeNode<String> nC = new BinaryTreeNode<String>("C");
		BinaryTreeNode<String> nJ = new BinaryTreeNode<String>("J");
		BinaryTreeNode<String> nD = new BinaryTreeNode<String>("D");
		BinaryTreeNode<String> nH = new BinaryTreeNode<String>("H");
		BinaryTreeNode<String> nK = new BinaryTreeNode<String>("K");
		BinaryTreeNode<String> nE = new BinaryTreeNode<String>("E");
		BinaryTreeNode<String> nF = new BinaryTreeNode<String>("F");
		BinaryTreeNode<String> nI = new BinaryTreeNode<String>("I");

		root.setLeft(nB);
		root.setRight(nC);
		nB.setLeft(nJ);
		nB.setRight(null);
		nC.setLeft(nD);
		nC.setRight(nH);
		nJ.setLeft(nK);
		nJ.setRight(null);
		nD.setLeft(nE);
		nD.setRight(null);
		nH.setLeft(nF);
		nH.setRight(nI);
		nK.setLeft(null);
		nK.setRight(null);
		nE.setLeft(null);
		nE.setRight(null);
		nF.setLeft(null);
		nF.setRight(null);
		nI.setLeft(null);
		nI.setRight(null);

		return root;
	}

	public static void main(String[] args) {

		PrettyPrintTree.printNode(test1());
		PrettyPrintTree.printNode(test2());
		PrettyPrintTree.printNode(test3());
	}
}
