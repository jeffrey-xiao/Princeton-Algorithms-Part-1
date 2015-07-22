package Sorting;

import java.util.ArrayList;
import java.util.Collections;

public class Brute {
	public static void main(String[] args) {
		// StdDraw.setXscale(0, 32768);
		// StdDraw.setYscale(0, 32768);

		In input = new In(args[0]);
		int n = input.readInt();
		Point[] points = new Point[n];
		for (int x = 0; x < n; x++) {
			points[x] = new Point(input.readInt(), input.readInt());
			points[x].draw();
		}
		for (int p = 0; p < n; p++) {
			for (int q = p + 1; q < n; q++) {
				double sq = points[p].slopeTo(points[q]);
				for (int r = q + 1; r < n; r++) {
					double sr = points[p].slopeTo(points[r]);
					if (sq == sr) {
						for (int s = r + 1; s < n; s++) {
							double ss = points[p].slopeTo(points[s]);
							if (sq == ss) {
								ArrayList<Point> pts = new ArrayList<Point>(4);
								pts.add(points[p]);
								pts.add(points[q]);
								pts.add(points[r]);
								pts.add(points[s]);
								Collections.sort(pts);
								for (int i = 0; i < 3; i++) {
									StdOut.print(pts.get(i));
									StdOut.print(" -> ");
								}
								StdOut.println(pts.get(3));
								pts.get(0).drawTo(pts.get(3));
							}
						}
					}
				}
			}
		}
	}
}
