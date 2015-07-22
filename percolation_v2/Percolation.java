package ValidPercolation;

public class Percolation {
	private WeightedQuickUnionUF qf;
	private WeightedQuickUnionUF qf1;
	private boolean[][] open;
	private int[] dirx = { 0, 0, -1, 1 };
	private int[] diry = { -1, 1, 0, 0 };
	private int n;

	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		this.n = n;
		open = new boolean[n][n];
		qf = new WeightedQuickUnionUF(n * n + 2);
		qf1 = new WeightedQuickUnionUF(n * n + 2);
		for (int x = 1; x <= n; x++) {
			qf.union(0, get1D(1, x));
			qf1.union(0, get1D(1, x));
			qf.union(n * n - 1, get1D(n, x));
		}
	}

	public boolean isFull(int x, int y) {
		if (x < 1 || y < 1 || x > n || y > n)
			throw new java.lang.IndexOutOfBoundsException();
		return qf1.find(0) == qf1.find(get1D(x, y)) && open[x - 1][y - 1];
	}

	private int get1D(int x, int y) {
		return (x - 1) * n + y;
	}

	// x and y start at 1
	public void open(int x, int y) {
		if (x < 1 || y < 1 || x > n || y > n)
			throw new java.lang.IndexOutOfBoundsException();
		// if(y == 1)
		// qf.union(0, get1D(x,1));
		open[x - 1][y - 1] = true;
		for (int z = 0; z < 4; z++) {
			int adjX = dirx[z] + x;
			int adjY = diry[z] + y;
			if (adjX < 1 || adjY < 1 || adjX > n || adjY > n)
				continue;
			if (isOpen(adjX, adjY)
					&& !qf1.connected((x - 1) * n + y, (adjX - 1) * n + adjY)) {
				qf.union((x - 1) * n + y, (adjX - 1) * n + adjY);
				qf1.union((x - 1) * n + y, (adjX - 1) * n + adjY);
			}
		}
	}

	public boolean isOpen(int x, int y) {
		if (x < 1 || y < 1 || x > n || y > n)
			throw new java.lang.IndexOutOfBoundsException();
		return open[x - 1][y - 1];
	}

	public boolean percolates() {
		if (n == 1)
			return isOpen(1, 1);
		if (qf.find(n * n - 1) == qf.find(0))
			return true;
		return false;
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(3);
		p.open(1, 3);
		p.open(2, 3);
		p.open(3, 3);
		p.open(3, 1);
		System.out.println(p.isFull(3, 1));
		p.open(3, 2);
		System.out.println(p.isFull(3, 1));
	}
}
