package Puzzle;

import java.util.*;

public class ExerciseBST {
	private static Queue<Integer> q = new LinkedList<Integer>();
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		search();
		removal();

	}

	static void levelOrder() {
		Node root = new Node(scan.nextInt());
		for (int x = 0; x < 9; x++)
			root.insert(scan.nextInt());
		root.level();
	}

	static void search() {
		Node root = new Node(scan.nextInt());
		for (int x = 0; x < 9; x++)
			root.insert(scan.nextInt());
		root.get(root, scan.nextInt());
	}

	static void removal() {
		Node root1 = new Node(scan.nextInt());
		for (int x = 0; x < 11; x++)
			root1.insert(scan.nextInt());
		root1 = root1.deleteFromRoot(root1, scan.nextInt());
		// root1.level();
		root1 = root1.deleteFromRoot(root1, scan.nextInt());
		// root1.level();
		root1 = root1.deleteFromRoot(root1, scan.nextInt());
		root1.level();
	}

	static class Node {
		Integer value;
		Node left;
		Node right;

		Node() {
		}

		public void get(Node curr, Integer i) {
			while (curr != null) {
				System.out.print(curr.value + " ");
				if (i == curr.value) {
					return;
				} else if (i < curr.value) {
					curr = curr.left;
				} else
					curr = curr.right;
			}
		}

		public Node deleteFromRoot(Node root, Integer i) {
			return delete(root, i);

		}

		private Node delete(Node root, Integer i) {
			if (root.value != i) {
				if (i < root.value)
					root.left = delete(root.left, i);
				else
					root.right = delete(root.right, i);
			} else {
				if (root.left == null && root.right == null)
					root = null;
				else if (root.left != null && root.right == null)
					root = root.left;
				else if (root.right != null && root.left == null)
					root = root.right;
				else {

					Node min = getMin(root.right);
					// System.out.println(root.value + " " + min.value);
					Node currLeft = root.left;
					Node currRight = root.right;
					currRight = deleteMin(currRight);
					root = min;

					root.left = currLeft;
					root.right = currRight;

				}
			}
			return root;
		}

		private Node deleteMin(Node curr) {
			if (curr.left.left != null) {
				curr.left = deleteMin(curr.left);
				return curr;
			}
			if (curr.left.right != null) {
				curr.left = curr.left.right;
				return curr;
			}
			curr.left = null;
			return curr;

		}

		private Node getMin(Node root) {
			while (root.left != null)
				root = root.left;
			return root;
		}

		Node(Integer value) {
			this.value = value;

		}

		void insert(Integer k) {
			if (k > value) {
				if (right == null)
					right = new Node(k);
				else
					right.insert(k);
			} else if (k < value) {
				if (left == null)
					left = new Node(k);
				else
					left.insert(k);
			}
		}

		void traverse() {
			if (left != null)
				left.traverse();
			q.offer(value);
			if (right != null)
				right.traverse();
		}

		void level() {
			Queue<Node> qq = new LinkedList<Node>();
			qq.offer(this);
			while (!qq.isEmpty()) {
				Node curr = qq.poll();
				if (curr == null)
					continue;
				System.out.print(curr.value + " ");
				/*
				 * System.out.print(curr.value + " ");
				 * 
				 * if(curr.right != null) System.out.print(curr.right.value +
				 * " "); else System.out.print("null "); if(curr.left != null)
				 * System.out.print(curr.left.value + " "); else
				 * System.out.print("null "); System.out.println();
				 */
				if (curr.left != null)
					qq.offer(curr.left);
				if (curr.right != null)
					qq.offer(curr.right);

			}
			System.out.println();
		}
	}
}
