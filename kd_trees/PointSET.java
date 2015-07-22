package KD_Trees;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
	private TreeSet<Point2D> ts;

	public PointSET() {
		ts = new TreeSet<Point2D>();
	}

	public boolean isEmpty() {
		return ts.isEmpty();
	}

	public int size() {
		return ts.size();
	}

	public void insert(Point2D p) {
		ts.add(p);
	}

	public boolean contains(Point2D p) {
		return ts.contains(p);
	}

	public void draw() {
		for (Point2D p : ts) {
			StdDraw.point(p.x(), p.y());
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		ArrayList<Point2D> a = new ArrayList<Point2D>();
		for (Point2D p : ts) {
			if (rect.contains(p))
				a.add(p);
		}
		return a;
	}

	public Point2D nearest(Point2D p) {
		if (isEmpty())
			return null;
		Point2D n = null;
		double dist = Double.POSITIVE_INFINITY;
		for (Point2D x : ts) {
			double d = p.distanceTo(x);
			if (d < dist) {
				dist = d;
				n = x;
			}
		}
		return n;
	}

}
