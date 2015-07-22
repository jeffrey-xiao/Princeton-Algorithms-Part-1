package Puzzle;

import java.util.*;

public class Solver {
	private static Scanner scan = new Scanner(System.in);
	private HashSet<String> visited = new HashSet<String>();
	private LinkedList<Board> solution = new LinkedList<Board>();
	private boolean isSolvable;
	private int minMoves;

	public Solver(Board initial) {
		MinPQ<State> pq = new MinPQ<State>();
		pq.insert(new State(initial, 0, null, true));
		pq.insert(new State(initial.twin(), 0, null, false));
		visited.add(initial.toString());
		State curr = null;
		while (!pq.isEmpty()) {

			curr = pq.delMin();
			// System.out.println(curr.b.toString() + " " + visited.size());
			if (curr.b.isGoal()) {
				minMoves = curr.moves;
				break;
			}
			for (Board b : curr.b.neighbors()) {
				// if(!visited.contains(b.toString())){
				// visited.add(b.toString());
				if (!visited(b, curr)) {
					pq.insert(new State(b, curr.moves + 1, curr, curr.original));
				}
			}
		}
		if (curr.original) {
			isSolvable = true;
			while (!curr.b.equals(initial)) {
				solution.addFirst(curr.b);
				curr = curr.prev;
			}
			solution.addFirst(initial);
		} else {
			minMoves = -1;
			isSolvable = false;
		}
	}

	private boolean visited(Board b, State curr) {
		while (curr != null) {
			if (b.equals(curr.b))
				return true;
			curr = curr.prev;
		}
		return false;
	}

	public boolean isSolvable() {
		return isSolvable;
	}

	public int moves() {
		return minMoves;
	}

	public Iterable<Board> solution() {
		if (!isSolvable())
			return null;
		return solution;
	}

	private static class State implements Comparable<State> {
		private Board b;
		private int moves;
		private State prev;
		private boolean original;

		State(Board b, int moves, State prev, boolean original) {
			this.b = b;
			this.moves = moves;
			this.prev = prev;
			this.original = original;
		}

		@Override
		public int compareTo(State o) {
			return (moves + b.manhattan()) - (o.moves + o.b.manhattan());
		}
	}

	public static void main(String[] args) {
		short N = scan.nextShort();
		int[][] b = new int[N][N];
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++)
				b[x][y] = scan.nextShort();
		}
		// System.out.println(new Board(b).hamming());
		Solver s = new Solver(new Board(b));
		if (s.isSolvable()) {
			System.out.println("Minimum number of moves = " + s.moves());
			for (Board board : s.solution()) {
				System.out.println(board);
			}
		} else {
			System.out.println("No solution possible");
		}
	}
}
