package KD_Trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tree_2D {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Node root = null;
		for (int z = 0; z < 8; z++) {
			char c = scan.next().charAt(0);
			double x = scan.nextDouble();
			double y = scan.nextDouble();
			root = new Node().insert(root, c, new Point(x, y), 0);
		}
		new Node().levelTraverse(root);
	}

	static class Node {
		int height;
		Character key;
		Point value;
		Node left, right;

		Node() {
		}

		Node(Character key, Point value, int height) {
			this.key = key;
			this.value = value;
			this.height = height;
		}

		public void levelTraverse(Node root) {
			Queue<Node> q = new LinkedList<Node>();
			q.offer(root);
			while (!q.isEmpty()) {
				Node curr = q.poll();
				System.out.print(curr.key + " ");
				if (curr.left != null) {
					q.offer(curr.left);
					// System.out.print(curr.left.key + " ");
				}
				if (curr.right != null) {
					q.offer(curr.right);
					// System.out.print(curr.right.key + " ");
				}
				// System.out.println();

			}
		}

		private Node insert(Node n, Character c, Point v, int height) {
			if (n == null)
				return new Node(c, v, height);
			double compare = 0;
			if (height % 2 == 0)
				compare = (n.value.x - v.x);
			else
				compare = (n.value.y - v.y);
			if (compare >= 0)
				n.left = insert(n.left, c, v, height + 1);
			else if (compare < 0)
				n.right = insert(n.right, c, v, height + 1);
			return n;
		}
	}

	static class Point {
		double x, y;

		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

}
