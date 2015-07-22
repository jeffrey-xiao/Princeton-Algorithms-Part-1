package ValidPercolation;

import java.util.ArrayList;

public class PercolationStats {
	private double total = 0;
	private double deviationTotal = 0;
	private double e = 0;
	private int T;
	private double diff;

	public PercolationStats(int N, int T) {
		if (N < 1 || T < 1)
			throw new IllegalArgumentException();
		this.T = T;
		// long c = System.currentTimeMillis();
		for (int x = 0; x < T; x++) {
			double d = getOperations(N) / (double) (N * N);
			e += (-2 * d);
			deviationTotal += d * d;
			total += d;
		}
		total /= T;
		e += (T * total);
		e *= total;
		diff = (1.96d * (stddev()) / (Math.sqrt(T)));
		// System.out.println(System.currentTimeMillis()-c);
	}

	private static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private int getOperations(int N) {
		Percolation p = new Percolation(N);
		int counter = 0;
		ArrayList<Point> points = new ArrayList<Point>();
		for (int x = 1; x <= N; x++)
			for (int y = 1; y <= N; y++)
				points.add(new Point(x, y));
		while (!p.percolates()) {
			int ranIndex = (int) (Math.random() * points.size());
			int ranX = points.get(ranIndex).x;
			int ranY = points.get(ranIndex).y;
			points.remove(ranIndex);
			p.open(ranX, ranY);
			counter++;
			/*
			 * System.out.println(counter); for(int x = 0; x < N; x++){ for(int
			 * y = 0; y < N; y++) System.out.print(p.isOpen(x+1,y+1)?"1 ":"0 ");
			 * System.out.println(); }
			 */
		}
		return counter;

	}

	public double mean() {
		return total;
	}

	public double stddev() {
		if (T == 1)
			return total;
		return Math.sqrt((e + deviationTotal) / (T - 1));
	}

	public double confidenceLo() {
		if (T == 1)
			return total;
		return total - diff;
	}

	public double confidenceHi() {
		if (T == 1)
			return total;
		return total + diff;
	}

	public static void main(String[] args) throws IllegalArgumentException {
		PercolationStats ps = new PercolationStats(2, 100);
		/*
		 * int N = Integer.parseInt(args[0]); int T = Integer.parseInt(args[1]);
		 * PercolationStats ps = new PercolationStats(N,T);
		 */
		/*
		 * if(N < 1 || T < 1) throw new IllegalArgumentException();
		 */
		System.out.printf("%-23s = %f\n", "mean", ps.mean());
		System.out.printf("%-23s = %f\n", "stddev", ps.stddev());
		System.out.printf("%-23s = %f, %f\n", "95% confidence interval",
				ps.confidenceLo(), ps.confidenceHi());
	}
}
