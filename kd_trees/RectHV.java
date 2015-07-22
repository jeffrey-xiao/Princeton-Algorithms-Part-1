package KD_Trees;

public class RectHV {
	private double xmin, ymin, xmax, ymax;

	public RectHV(double xmin, double ymin, double xmax, double ymax) {
		if (xmax < xmin || ymax < ymin)
			throw new IllegalArgumentException("Invalid rectangle");
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	}

	public double xmin() {
		return xmin;
	}

	public double ymin() {
		return ymin;
	}

	public double xmax() {
		return xmax;
	}

	public double ymax() {
		return ymax;
	}

	public boolean intersects(RectHV that) {
		if (that == null)
			throw new NullPointerException();
		return this.xmax >= that.xmin && this.ymax >= that.ymin
				&& that.xmax >= this.xmin && that.ymax >= this.ymin;
	}

	public boolean contains(Point2D p) {
		if (p == null)
			throw new NullPointerException();
		return (p.x() >= xmin) && (p.x() <= xmax) && (p.y() >= ymin)
				&& (p.y() <= ymax);
	}

	public double distanceTo(Point2D p) {
		return Math.sqrt(distanceSquaredTo(p));
	}

	public double distanceSquaredTo(Point2D p) {
		double dx = 0.0, dy = 0.0;
		if (contains(p))
			return 0.0d;
		if (p.x() < xmin)
			dx = p.x() - xmin;
		else if (p.x() > xmax)
			dx = p.x() - xmax;
		if (p.y() < ymin)
			dy = p.y() - ymin;
		else if (p.y() > ymax)
			dy = p.y() - ymax;
		return dx * dx + dy * dy;
	}

	public void draw() {
		// StdDraw.line(xmin, ymin, xmax, ymin);
		// StdDraw.line(xmax, ymin, xmax, ymax);
		// StdDraw.line(xmax, ymax, xmin, ymax);
		// StdDraw.line(xmin, ymax, xmin, ymin);
	}

	public String toString() {
		return "Xmin: " + xmin + "; Xmax: " + xmax + "; Ymin: " + ymin
				+ "; Ymax: " + ymax + ";";
	}
}
