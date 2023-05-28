
public class TreeWork {

	public static void main(String[] args) {
		// Create a tree with a root with a random int
		Tree binaryTree = new Tree((int)(Math.random()*10000));
		int rand = 0;
		// Creat 1023 nodes on the tree
		for (int i=0; i<1023; i++) {
			rand = (int)(Math.random()*10000);
			binaryTree.treeInsert(rand);
		}
		// Run and output The max Tree Depth and average depth with explainer
		System.out.println("The max depth of the Tree is " + binaryTree.maxDepth(binaryTree.root));
		System.out.println("The average depth of the Tree is " + binaryTree.sumDepths(0,binaryTree.root)/binaryTree.countLeaves(binaryTree.root));

	}
	/**
	* An object of type TreeNode represents one node in a binary tree of strings.
	* Basic TreeNode Class from the book but changes from string to integer
	*/
	private static class TreeNode {
		int item;
		// The data in this node.
		TreeNode left;
		// Pointer to left subtree.
		TreeNode right;
		// Pointer to right subtree.
		TreeNode(int num) {
			// Constructor. Make a node containing str.
			// Note that left and right pointers are null.
			item = num;
		}
	} // end class TreeNode
	/*
	 * Tree class is a representation of a tree. With the root of the tree being the starting point 
	 * for all operations. Includes methods to add item to the tree, Sum the depths of all the leaves,
	 * Count the total number of leaves and find the maximum depth of the tree.
	 */
	private static class Tree {
		TreeNode root;
		private Tree() {
			root = null;
		} // end Tree() constructor
		private Tree(int num) {
			root = new TreeNode(num);
		} // end Tree(int num) constructor
		private void treeInsert(int num) {
			if (root == null) {
				root = new TreeNode(num);
				return;
			}
			TreeNode runner = root;
			while (true) {
				if (num < runner.item) {
					if (runner.left == null) {
						runner.left = new TreeNode(num);
						break;
					}
					else {
						runner = runner.left;
					}
				}
				else {
					if (runner.right == null) {
						runner.right = new TreeNode(num);
						break;
					}
					else {
						runner = runner.right;
					}
				}
			}
		}// End treeInsert()
		private int sumDepths(int currentDepth, TreeNode root) {
			if (root.right == null && root.left == null) {
				return currentDepth;
			}
			int depth = 0;
			if (root.right != null) {
				depth += sumDepths(currentDepth+1, root.right);
			}
			if (root.left != null) {
				depth += sumDepths(currentDepth+1,root.left);
			}
			
			return depth;
		}// End sumDepths
		private int countLeaves(TreeNode root) {
			if (root.right == null && root.left == null) {
				return 1;
			}
			int count = 0;
			if (root.right != null){
				count += countLeaves(root.right);
			}
			if (root.left != null) {
				count += countLeaves(root.left);
			}
			return count;
		}// End countLeaves
		private int maxDepth(TreeNode root) {
			int right=0;
			int left=0;
			if (root.right != null) {
				right = 1 + maxDepth(root.right);
			}
			if (root.left != null) {
				left = 1 + maxDepth(root.left);
			}
			if (root.right == null && root.left == null) {
				return 0;
			}
			
			if (left> right) {
				return left;
			}
			else {
				return right;
			}
		}//End Max Depth
	}
	

}
