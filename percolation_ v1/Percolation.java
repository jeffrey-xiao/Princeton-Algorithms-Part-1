package Percolation;

import java.util.ArrayList;

public class Percolation {
	private WeightedQuickUnionUF qf;
	private boolean[][] open;
	private int[] dirx = { 0, 0, -1, 1 };
	private int[] diry = { -1, 1, 0, 0 };
	private int n;
	private ArrayList<Point> points = new ArrayList<Point>();

	private static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public Percolation(int n) {
		this.n = n;
		open = new boolean[n][n];
		qf = new WeightedQuickUnionUF(n * n + 2);

		for (int x = 1; x <= n; x++) {
			for (int y = 1; y <= n; y++)
				points.add(new Point(x, y));
			qf.union(0, get1D(1, x));
			qf.union(n * n - 1, get1D(n, x));
		}
	}

	public boolean isFull(int x, int y) {
		return qf.find(n * n + 1) == qf.find(get1D(x, y));
	}

	private int get1D(int x, int y) {
		return (x - 1) * n + y;
	}

	// x and y start at 1
	public void open(int x, int y) {
		// if(y == 1)
		// qf.union(0, get1D(x,1));
		open[x - 1][y - 1] = true;
		for (int z = 0; z < 4; z++) {
			int adjX = dirx[z] + x;
			int adjY = diry[z] + y;
			if (isOpen(adjX - 1, adjY - 1)
					&& !qf.connected((x - 1) * n + y, (adjX - 1) * n + adjY)) {
				qf.union((x - 1) * n + y, (adjX - 1) * n + adjY);
			}
		}
	}

	public boolean isOpen(int adjX, int adjY) {
		if (adjX < 0 || adjY < 0 || adjX >= n || adjY >= n)
			return false;
		return open[adjX][adjY];
	}

	public boolean percolates() {
		if (qf.find(n * n - 1) == qf.find(0))
			return true;
		return false;
	}

	public int getOperations() {
		int counter = 0;
		if (n == 1)
			return 1;
		while (!percolates()) {
			int ranIndex = (int) (Math.random() * points.size());
			int ranX = points.get(ranIndex).x;
			int ranY = points.get(ranIndex).y;
			points.remove(ranIndex);
			open(ranX, ranY);
			counter++;
			System.out.println(counter);
			/*
			 * for(int x = 0; x < n; x++){ for(int y = 0; y < n; y++)
			 * System.out.print(open[x][y]?"1 ":"0 "); System.out.println(); }
			 */
		}
		return counter;
	}
}
