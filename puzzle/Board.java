package Puzzle;

import java.util.*;

public class Board {
	private final int N;
	private final short[] grid;

	public Board(int[][] blocks) {
		N = blocks.length;
		grid = new short[N * N];
		for (int x = 0; x < N; x++)
			for (int y = 0; y < N; y++)
				grid[get2d(x, y)] = (short) blocks[x][y];
	}

	private Board(int[] blocks) {
		N = (int) Math.sqrt(blocks.length);
		grid = new short[N * N];
		for (int x = 0; x < N * N; x++)
			grid[x] = (short) blocks[x];
	}

	private Board(short[][] blocks) {
		N = blocks.length;
		grid = new short[N * N];
		for (int x = 0; x < N; x++)
			for (int y = 0; y < N; y++)
				grid[get2d(x, y)] = (short) blocks[x][y];
	}

	private Board(short[] blocks) {
		N = (int) Math.sqrt(blocks.length);
		grid = new short[N * N];
		for (int x = 0; x < N * N; x++)
			grid[x] = (short) blocks[x];
	}

	private int get2d(int x, int y) {
		return x * N + y;
	}

	public int dimension() {
		return N;
	}

	public int hamming() {
		int count = 0;
		for (int x = 0; x < N; x++)
			for (int y = 0; y < N; y++) {
				int targetValue = (1 + y + x * N) % (N * N);
				if (targetValue != 0 && targetValue != grid[get2d(x, y)])
					count++;
			}
		return count;
	}

	public int manhattan() {
		int count = 0;
		for (int x = 0; x < N; x++)
			for (int y = 0; y < N; y++) {
				int value = grid[get2d(x, y)];
				if (value == 0)
					continue;
				int gx = (value - 1) / N;
				int gy = (value - 1) % N;
				count += (Math.abs(x - gx) + Math.abs(y - gy));
			}
		return count;
	}

	public boolean isGoal() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				int targetValue = (1 + y + x * N) % (N * N);
				// System.out.println(targetValue);
				if (targetValue != 0 && targetValue != grid[get2d(x, y)])
					return false;
			}
		}
		return true;
	}

	public Board twin() {
		Board twin = new Board(grid);

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N - 1; y++) {
				if (grid[get2d(x, y)] != 0 && grid[get2d(x, y + 1)] != 0) {
					twin.ex(x, y, x, y + 1);
					return twin;
				}
			}
		}

		return twin;
	}

	private boolean ex(int x, int y, int x2, int y2) {
		if (x2 < 0 || x2 >= N || y2 < 0 || y2 >= N)
			return false;
		short temp = grid[get2d(x, y)];
		grid[get2d(x, y)] = grid[get2d(x2, y2)];
		grid[get2d(x2, y2)] = temp;
		return true;
	}

	public boolean equals(Object o) {
		if (o instanceof Board) {
			Board b1 = (Board) o;
			short[] b = b1.grid;
			if (b.length != N * N)
				return false;
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if (b[get2d(x, y)] != grid[get2d(x, y)])
						return false;
				}
			}
			return true;
		}
		return false;
	}

	public Iterable<Board> neighbors() {
		int posx = 0, posy = 0;
		main: for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (grid[get2d(x, y)] == 0) {
					posx = x;
					posy = y;
					break main;
				}
			}
		}
		LinkedList<Board> boards = new LinkedList<Board>();
		Board b = new Board(grid);
		if (b.ex(posx, posy, posx - 1, posy))
			boards.add(b);
		b = new Board(grid);
		if (b.ex(posx, posy, posx + 1, posy))
			boards.add(b);
		b = new Board(grid);
		if (b.ex(posx, posy, posx, posy - 1))
			boards.add(b);
		b = new Board(grid);
		if (b.ex(posx, posy, posx, posy + 1))
			boards.add(b);
		return boards;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				s.append(String.format("%2d ", grid[get2d(x, y)]));
			}
			s.append("\n");
		}
		return s.toString();
	}
}
