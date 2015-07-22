package KD_Trees;

import java.util.Scanner;

public class Interval {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Node root = null;
		for (int x = 0; x < 8; x++) {
			char c = scan.next().charAt(0);
			int lo = scan.nextInt();
			int hi = scan.nextInt();
			root = new Node().insert(root, c, lo, hi);
		}
		root.search(root, 18, 20);
	}

	static class Node {
		int lo, hi, max;
		char c;
		Node left, right;

		Node() {
		}

		Node(char c, int lo, int hi) {
			this.c = c;
			this.lo = lo;
			this.hi = hi;
			this.max = hi;
		}

		public void search(Node n, int lo, int hi) {
			if (n == null)
				return;
			System.out.print(n.c + " ");
			if ((n.lo < hi && n.hi > lo) || (lo < n.hi && hi > n.lo)) {
				// System.out.println("ASD");
				return;
			}
			if (n.left == null)
				search(n.right, lo, hi);
			else if (n.left.max < lo) {
				// System.out.println(n.c + " " + n.left.max);
				search(n.right, lo, hi);
			} else {
				// System.out.println(n.c);
				search(n.left, lo, hi);
			}
		}

		public Node insert(Node n, char c, int lo, int hi) {
			if (n == null)
				return new Node(c, lo, hi);
			double compare = lo - n.lo;
			if (compare < 0) {
				n.left = insert(n.left, c, lo, hi);
				n.max = Math.max(n.max, n.left.max);
			} else if (compare == 0)
				n.lo = lo;
			else {
				n.right = insert(n.right, c, lo, hi);
				n.max = Math.max(n.max, n.right.max);
			}
			return n;
		}
	}
}
