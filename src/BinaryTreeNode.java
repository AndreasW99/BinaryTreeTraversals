/**
 * BinaryTreeNode represents a node in a binary tree with a left and right
 * child.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

public class BinaryTreeNode<T> {
	private T element;
	private BinaryTreeNode<T> left, right;

	/**
	 * Creates a new tree node with the specified data.
	 *
	 * @param obj
	 *            the element that will become a part of the new tree node
	 */
	BinaryTreeNode(T obj) {
		element = obj;
		left = null;
		right = null;
	}

	/**
	 * Returns the number of non-null children of this node. This method may be able
	 * to be written more efficiently.
	 *
	 * @return the integer number of non-null children of this node
	 */
	public int numChildren() {
		int children = 0;

		if (getLeft() != null)
			children = 1 + getLeft().numChildren();

		if (getRight() != null)
			children = children + 1 + getRight().numChildren();

		return children;
	}

	/**
	 * @return the element
	 */
	public T getElement() {
		return element;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * @return the left
	 */
	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public BinaryTreeNode<T> getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}

}
