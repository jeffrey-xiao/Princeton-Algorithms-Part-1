package KD_Trees;

import java.util.*;

public class Exercises {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Node root = new Node(scan.nextInt());
		root.color = false;
		for (int x = 0; x < 12; x++) {
			root = root.insert(root, scan.nextInt());
			root.color = false;
		}
		root.traverse(root);

		root = new Node(scan.nextInt());
		root.color = false;
		for (int x = 0; x < 12; x++) {
			root = root.insert(root, scan.nextInt());
			root.color = false;
		}
		root.levelTraverse(root);
	}

	static class Node {
		Integer value;
		Node left, right;
		boolean color;

		Node(int value) {
			this.value = value;
			this.color = true;
		}

		public void levelTraverse(Node root) {
			Queue<Node> q = new LinkedList<Node>();
			q.offer(root);
			while (!q.isEmpty()) {
				Node curr = q.poll();
				System.out.print(curr.value + " ");
				if (curr.left != null)
					q.offer(curr.left);
				if (curr.right != null)
					q.offer(curr.right);
			}
		}

		private void traverse(Node n) {
			if (n == null)
				return;
			traverse(n.left);
			if (n.color)
				System.out.print(n.value + " ");
			traverse(n.right);
		}

		private boolean isRed(Node n) {
			if (n == null)
				return false;
			return n.color;
		}

		private Node rotateLeft(Node n) {
			Node temp = n.right;
			n.right = temp.left;
			temp.left = n;
			temp.color = n.color;
			n.color = true;
			return temp;
		}

		private Node insert(Node n, Integer v) {
			if (n == null)
				return new Node(v);
			if (n.value > v)
				n.left = insert(n.left, v);
			else if (n.value == v)
				n.value = v;
			else
				n.right = insert(n.right, v);
			if (isRed(n.right) && !isRed(n.left))
				n = rotateLeft(n);
			if (isRed(n.left) && isRed(n.left.left))
				n = rotateRight(n);
			if (isRed(n.left) && isRed(n.right))
				flipColors(n);

			return n;
		}

		private Node rotateRight(Node n) {
			Node temp = n.left;
			n.left = temp.right;
			temp.right = n;
			temp.color = n.color;
			n.color = true;
			return temp;
		}

		private void flipColors(Node n) {
			n.color = true;
			n.left.color = false;
			n.right.color = false;
		}
	}
}
