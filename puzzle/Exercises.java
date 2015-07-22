package Puzzle;

import java.util.Scanner;

public class Exercises {
	static int N;
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Integer[] array = new Integer[13];
		for (int x = 0; x < 13; x++)
			array[x] = scan.nextInt();
		for (int x = 10; x < 13; x++) {
			promote(array, x + 1);
		}
		for (int x : array)
			System.out.print(x + " ");
		System.out.println();

		Integer[] a = new Integer[10];
		N = 9;
		for (int x = 0; x < 10; x++)
			a[x] = scan.nextInt();
		delMax(a);
		delMax(a);
		delMax(a);
		for (int x = 0; x < 10; x++)
			System.out.print(a[x] + " ");
		System.out.println();

	}

	private static Integer delMax(Integer[] array) {
		Integer max = array[0];
		ex(array, 0, N--);
		array[N + 1] = null;
		demote(array, 1);

		return max;
	}

	private static void demote(Integer[] array, int x) {
		while (2 * x <= array.length) {
			int y = 2 * x;
			if (y < array.length && array[y] != null && array[y - 1] < array[y])
				y++;
			if (array[y - 1] == null || array[x - 1] >= array[y - 1])
				break;
			ex(array, x - 1, y - 1);
			x = y;
		}
	}

	private static void promote(Integer[] array, int x) {
		while (x > 1 && array[x - 1] > array[x / 2 - 1]) {
			// System.out.println("here");
			ex(array, x - 1, x / 2 - 1);
			x = x / 2;
		}
	}

	private static void ex(Integer[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
}