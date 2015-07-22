package Percolation;

public class PercolationStats {
	private double total = 0;
	private double deviationTotal = 0;
	private double e = 0;
	private int N;
	private int T;
	private double diff;

	public PercolationStats(int N, int T) {
		Percolation p;
		this.N = N;
		this.T = T;
		// long c = System.currentTimeMillis();
		for (int x = 0; x < T; x++) {
			p = new Percolation(N);
			double d = p.getOperations() / (double) (N * N);
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

	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(5, 10);
		/*
		 * int N = Integer.parseInt(args[0]); int T = Integer.parseInt(args[1]);
		 * PercolationStats ps = new PercolationStats(N,T);
		 */
		System.out.printf("%-23s = %f\n", "mean", ps.mean());
		System.out.printf("%-23s = %f\n", "stddev", ps.stddev());
		System.out.printf("%-23s = %f, %f\n", "95% confidence interval",
				ps.confidenceLo(), ps.confidenceHi());
	}
}
