package KD_Trees;

public class Point2D implements Comparable<Point2D> {
	private double x, y;

	public Point2D(double x, double y) {
		if (Double.isInfinite(x) || Double.isInfinite(y))
			throw new IllegalArgumentException("Coordinates must be finite");
		if (Double.isNaN(x) || Double.isNaN(y))
			throw new IllegalArgumentException("Coordinates cannot be NaN");
		this.x = x;
		this.y = y;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public double distanceTo(Point2D that) {
		return Math.sqrt(distanceSquaredTo(that));
	}

	public double distanceSquaredTo(Point2D that) {
		return (x - that.x) * (x - that.x) + (y - that.y) * (y - that.y);
	}

	public int compareTo(Point2D that) {
		if (this.y < that.y)
			return -1;
		if (this.y > that.y)
			return +1;
		if (this.x < that.x)
			return -1;
		if (this.x > that.x)
			return +1;
		return 0;
	}

	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (other.getClass() != this.getClass())
			return false;
		Point2D that = (Point2D) other;
		return this.x == that.x && this.y == that.y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public String toString() {
		return "X: " + x + "; Y: " + y;
	}
}
