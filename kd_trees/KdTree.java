package KD_Trees;

import java.util.*;

public class KdTree {
	private int size;
	private Node root;

	public KdTree() {
		root = null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void insert(Point2D p) {
		if (p == null)
			return;
		root = insert(root, p, 0, 0.0, 0.0, 1.0, 1.0);
	}

	public boolean contains(Point2D p) {
		return search(root, p, 0);
	}

	public void draw() {
		Stack<Node> moves = new Stack<Node>();
		moves.push(root);
		while (!moves.isEmpty()) {
			Node curr = moves.pop();
			RectHV a = curr.area;
			// System.out.println("DRAWING " + a.xmin() + " " + a.xmax() + " " +
			// a.ymin() + " " +a.ymax());
			if (curr.height % 2 == 0) {
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.line(curr.value.x(), a.ymin(), curr.value.x(), a.ymax());
				StdDraw.setPenColor(StdDraw.BLACK);
			} else {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(a.xmin(), curr.value.y(), a.xmax(), curr.value.y());
				StdDraw.setPenColor(StdDraw.BLACK);
			}
			StdDraw.rectangle((a.xmin() + a.xmax()) / 2,
					(a.ymin() + a.ymax()) / 2, (a.xmax() - a.xmin()) / 2,
					(a.ymax() - a.ymin()) / 2);
			if (curr.left != null)
				moves.push(curr.left);
			if (curr.right != null)
				moves.push(curr.right);
		}
	}

	public Point2D nearest(Point2D p) {
		if (isEmpty())
			return null;
		return nearest(root, p, null);
	}

	private Point2D nearest(Node curr, Point2D p, Point2D min) {
		if (curr == null
				|| (min != null && min.distanceSquaredTo(p) <= curr.area
						.distanceSquaredTo(p)))
			return min;
		if (min == null
				|| curr.value.distanceSquaredTo(p) < min.distanceSquaredTo(p))
			min = curr.value;
		if (curr.right != null && curr.right.area.contains(p)) {
			min = nearest(curr.right, p, min);
			min = nearest(curr.left, p, min);
		} else {
			min = nearest(curr.left, p, min);
			min = nearest(curr.right, p, min);
		}
		return min;
	}

	public Iterable<Point2D> range(RectHV rect) {
		ArrayList<Point2D> ps = new ArrayList<Point2D>();
		Stack<Node> moves = new Stack<Node>();
		if (root != null)
			moves.push(root);
		while (!moves.isEmpty()) {
			Node curr = moves.pop();
			if (curr == null || !rect.intersects(curr.area))
				continue;
			if (rect.contains(curr.value))
				ps.add(curr.value);
			moves.push(curr.left);
			moves.push(curr.right);
		}
		return ps;
	}

	private Node insert(Node n, Point2D v, int height, double xmin,
			double ymin, double xmax, double ymax) {
		if (n == null) {
			size++;
			return new Node(v, height, new RectHV(xmin, ymin, xmax, ymax));
		}
		double compare = compare(n.value, v, height);

		if (compare > 0) {
			if (height % 2 == 0)
				n.left = insert(n.left, v, height + 1, xmin, ymin, n.value.x(),
						ymax);
			else
				n.left = insert(n.left, v, height + 1, xmin, ymin, xmax,
						n.value.y());
		} else if (compare < 0) {
			if (height % 2 == 0)
				n.right = insert(n.right, v, height + 1, n.value.x(), ymin,
						xmax, ymax);
			else
				n.right = insert(n.right, v, height + 1, xmin, n.value.y(),
						xmax, ymax);
		}
		return n;
	}

	private int compare(Point2D p1, Point2D p2, int height) {
		if (height % 2 == 0) {
			int result = new Double(p1.x()).compareTo(p2.x());
			if (result == 0)
				return new Double(p1.y()).compareTo(p2.y());
			return result;
		}
		int result = new Double(p1.y()).compareTo(p2.y());
		if (result == 0)
			return new Double(p1.x()).compareTo(p2.x());
		return result;
	}

	private boolean search(Node n, Point2D v, int height) {
		if (n == null)
			return false;
		double compare = compare(n.value, v, height);
		if (compare > 0)
			return search(n.left, v, height + 1);
		else if (compare < 0)
			return search(n.right, v, height + 1);
		else
			return true;
	}

	private static class Node {
		int height;
		Point2D value;
		Node left, right;
		RectHV area;

		Node(Point2D value, int height, RectHV area) {
			this.value = value;
			this.height = height;
			this.area = area;
		}
	}

	public static void main(String[] args) {
		KdTree k = new KdTree();
		// k.insert(new Point2D(0,0));
		// k.insert(new Point2D(0,0));
		// k.insert(new Point2D(0,0));
		// k.insert(new Point2D(0,0));
		// k.insert(new Point2D(0,0.1));
		for (Point2D p : k.range(new RectHV(0.0, 0.0, 0.6, 0.6))) {
			System.out.println(p);
		}
		System.out.println(k.size());
	}
}
