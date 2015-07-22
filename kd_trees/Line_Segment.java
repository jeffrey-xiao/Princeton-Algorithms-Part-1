package KD_Trees;

import java.util.*;

public class Line_Segment {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		PriorityQueue<Event> pq = new PriorityQueue<Event>();
		Node root = null;
		for (int x = 0; x < 5; x++) {
			Character c = scan.next().charAt(0);
			int x1 = scan.nextInt();
			int x2 = scan.nextInt();
			int y = scan.nextInt();
			pq.offer(new Event(c, x1, y, true));
			pq.offer(new Event(c, x2, y, false));
		}
		while (!pq.isEmpty()) {
			Event curr = pq.poll();
			if (curr.x > 11)
				break;
			if (curr.add)
				root = new Node().insert(root, curr.key, curr.y);
			else
				root = new Node().delete(root, curr.y);
		}
		root.traverse(root);
	}

	static class Event implements Comparable<Event> {
		char key;
		int x, y;
		boolean add;

		Event(char key, int x, int y, boolean add) {
			this.key = key;
			this.x = x;
			this.y = y;
			this.add = add;
		}

		@Override
		public int compareTo(Event o) {
			return x - o.x;
		}
	}

	static class Node {
		Character key;
		Integer value;
		Node right, left;

		Node() {
		}

		Node(Character key, Integer value) {
			this.key = key;
			this.value = value;
		}

		private void traverse(Node n) {
			if (n == null)
				return;
			traverse(n.left);
			System.out.print(n.key + " ");
			traverse(n.right);
		}

		private Node insert(Node n, Character c, Integer v) {
			if (n == null)
				return new Node(c, v);
			if (n.value > v)
				n.left = insert(n.left, c, v);
			else if (n.value == v)
				n.value = v;
			else
				n.right = insert(n.right, c, v);
			return n;
		}

		private Node delete(Node n, Integer v) {
			if (n.value == v) {
				if (n.right == null && n.left == null) {
					n = null;
				} else if (n.right != null && n.left == null) {
					n = n.right;
				} else if (n.right == null && n.left != null) {
					n = n.left;
				} else {
					Node left = n.left;
					Node right = n.right;
					Node min = n.min(n.right);
					n = min;
					n.left = left;
					n.right = right;
				}
			} else {
				if (n.value > v)
					n.left = delete(n.left, v);
				else
					n.right = delete(n.right, v);
			}
			return n;
		}

		private Node min(Node n) {
			if (n.left.left != null)
				return min(n.left);
			else {
				Node curr = n.left;
				n.left = null;
				return curr;
			}
		}
	}
}
