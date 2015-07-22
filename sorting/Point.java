/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/
package Sorting;

import java.util.Comparator;

public class Point implements Comparable<Point> {

	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
		@Override
		public int compare(Point arg0, Point arg1) {
			double s1 = slopeTo(arg0);
			double s2 = slopeTo(arg1);
			if (s1 == s2)
				return 0;
			if (s1 < s2)
				return -1;
			return 1;
		}
	};

	private final int x; // x coordinate
	private final int y; // y coordinate

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		// StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		// StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public double slopeTo(Point that) {
		if (that == null) {
			throw new NullPointerException();
		}
		if (that.x == x) {
			if (that.y == y)
				return Double.NEGATIVE_INFINITY;
			else
				return Double.POSITIVE_INFINITY;
		}
		if (that.y == y)
			return 0.0d;
		return (double) (that.y - y) / (that.x - x);
	}

	public int compareTo(Point that) {
		if (y == that.y && x == that.x)
			return 0;
		if (y > that.y || (y == that.y && x > that.x))
			return 1;
		return -1;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public static void main(String[] args) {
	}
}