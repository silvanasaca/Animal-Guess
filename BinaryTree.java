/**
 *  Implements binary trees.
 *
 *  @author  Nicholas R. Howe
 *  @version CSC 112, 3 April 2006
 */
public class BinaryTree<E> {
    /** The value at this node */
    private E data;

    /** Left child of this node */
    private BinaryTree<E> left;

    /** Right child of this node */
    private BinaryTree<E> right;

    /** This constructor creates a leaf node */
    public BinaryTree(E data) {
	this.data = data;
	left = right = null;
    }
    
    /** This constructor creates a branch node */
    public BinaryTree(E data, BinaryTree<E> left, BinaryTree<E> right) {
	this.data = data;
	this.right = right;
	this.left = left;
    }

    /** This constructor creates a deep copy of the entire tree structure */ 
    public BinaryTree(BinaryTree<E> tree) {
	this.data = tree.data;
	this.left = (tree.left == null)? null:(new BinaryTree<E>(tree.left));
	this.right = (tree.right == null)? null:(new BinaryTree<E>(tree.right));
    }

    /** Accessor for node data */
    public E getData() { 
	return data;
    }

    /** Accessor for left child */
    public BinaryTree<E> getLeft() {  
	return left;
    }

    /** Accessor for right child */
    public BinaryTree<E> getRight() {  
	return right;
    }

    /** Manipulator for node data */
    public void setData(E data) { 
	this.data = data;
    }

    /** Manipulator for left child */
    public void setLeft(BinaryTree<E> left) { 
	this.left = left;
    }

    /** Manipulator for right child */
    public void setRight(BinaryTree<E> right) { 
	this.right = right;
    }

    /** Determines whether a tree is empty */
    public static boolean isEmpty(BinaryTree node) {
	return (node == null);
    }

    /** Determines whether this tree is a leaf */
    public boolean isLeaf() {
	//returns true if left is null and if right is null
	return (left == null)&&(right ==null);
  
    }

    /** Determines whether this tree is a branch */
    public boolean isBranch() {
	//returns true if left is not null or if right is not null
	return (left != null)||(right != null);

    }

    /** 
     *  Indents by the specified number of spaces 
     *
     *  @param n  Number of spaces
     */
    private static void indent(int n) {
	for (int i = 0; i < n; i++) {
	    System.out.print(' ');
	}
    }

    /** Prints the tree nodes with indentation indicating level */
    public void print() {
	System.out.print("Root:  ");
	printIndented(0);
    }

    /** 
     *  Prints the tree with indentation corresponding to level.
     *  Basically a fancy version of preorder.
     *
     *  @param depth  Level of this node, for indentation 
     */
    public void printIndented(int depth) {
	System.out.println(data);
	depth++;
	if (left != null) {
	    indent(2*depth);
	    System.out.print("Left:  ");
	    left.printIndented(depth);
	}
	//print the tree with indentation on right also
	if (right != null) {
	    indent(2*depth);
	    System.out.print("Right: ");
	    right.printIndented(depth);
	}
    }
}
