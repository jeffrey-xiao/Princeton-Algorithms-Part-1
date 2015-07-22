package Sorting;

import java.util.*;

public class Fast {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// StdDraw.setXscale(0, 32768);
		// StdDraw.setYscale(0, 32768);

		// In input = new In(args[0]);
		int n = input.nextInt();
		Point[] points = new Point[n];

		for (int x = 0; x < n; x++) {
			points[x] = new Point(input.nextInt(), input.nextInt());
			points[x].draw();
		}
		Point[] ori = Arrays.copyOf(points, points.length);

		for (int x = 0; x < n; x++) {
			sortUp(points, ori[x].SLOPE_ORDER);
			ArrayList<Point> p = new ArrayList<Point>(n);
			boolean slopeWorks = true;
			boolean first = true;
			double slope = 0.0;
			for (int y = 1; y < n; y++) {
				if (!slopeWorks && points[0].slopeTo(points[y]) == slope)
					continue;
				if (first) {
					first = false;
					slope = points[0].slopeTo(points[y]);
					p.add(points[y]);
					if (points[0].compareTo(points[y]) > 0)
						slopeWorks = false;
				} else if (!first && points[0].slopeTo(points[y]) == slope) {
					p.add(points[y]);
					if (points[0].compareTo(points[y]) > 0)
						slopeWorks = false;
				} else {
					if (slopeWorks)
						print(points, p, slopeWorks);
					p.clear();
					slopeWorks = true;
					if (points[0].compareTo(points[y]) > 0)
						slopeWorks = false;
					p.add(points[y]);
					slope = points[0].slopeTo(points[y]);
				}
			}
			if (slopeWorks)
				print(points, p, slopeWorks);
		}
	}

	private static void print(Point[] points, ArrayList<Point> p,
			boolean slopeWorks) {
		if (p.size() > 2) {
			p.add(points[0]);
			Collections.sort(p);
			for (int i = 0; i < p.size() - 1; i++) {
				System.out.print(p.get(i));
				System.out.print(" -> ");
			}
			System.out.println(Collections.max(p));
			Collections.min(p).drawTo(Collections.max(p));
		}
	}

	private static void sortUp(Point[] a, Comparator<Point> c) {
		int length = a.length;
		Point[] aux = new Point[length];
		for (int gap = 1; gap < length; gap *= 2) {
			for (int low = 0; low < length - gap; low += gap * 2) {
				merge(a, aux, low, low + gap - 1,
						Math.min(length - 1, low + gap + gap - 1), c);
			}
		}
	}

	private static void merge(Point[] a, Point[] aux, int low, int mid,
			int high, Comparator<Point> c) {
		for (int i = low; i <= high; i++)
			aux[i] = a[i];
		int x = low;
		int y = mid + 1;
		for (int i = low; i <= high; i++) {
			if (x > mid)
				a[i] = aux[y++];
			else if (y > high)
				a[i] = aux[x++];
			else if (c.compare(aux[x], aux[y]) <= 0)
				a[i] = aux[x++];
			else
				a[i] = aux[y++];
		}
	}
}
