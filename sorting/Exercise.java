package Sorting;

public class Exercise {
	static int count = 0;

	public static void main(String[] args) {
		int[] a = new int[] { 1, 5, 7, 8, 32, 7, 8, 2, 36, 26, 6, 23, 42, 7687,
				2, 2, 3, 46, 7, 83, 3, 56, 633, 76, 8 };
		sortUp(a);
		for (int x : a)
			System.out.print(x + " ");
	}

	public static void quickSort() {
		Integer[] a = new Integer[] { 30, 54, 17, 92, 61, 27, 34, 11, 80, 65,
				58, 51 };
		partition(a, 0, a.length - 1);
		for (int x : a)
			System.out.print(x + " ");
		String[] b = "B A A A A B B B B B A B".split(" ");
		partition(b, 0, b.length - 1);
		System.out.println();
		for (String x : b)
			System.out.print(x + " ");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void partition(Comparable[] a, int lo, int hi) {
		int x = lo;
		int y = hi + 1;
		while (true) {
			while (a[++x].compareTo(a[lo]) == -1)
				if (x == hi)
					break;
			while (a[lo].compareTo(a[--y]) == -1)
				if (y == lo)
					break;
			if (x >= y)
				break;
			ex(a, x, y);
		}
		ex(a, lo, y);
	}

	@SuppressWarnings("rawtypes")
	private static void ex(Comparable[] a, int x, int y) {
		Comparable temp = a[x];
		a[x] = a[y];
		a[y] = temp;

	}

	public static void mergeSort() {
		int[] a = new int[] { 52, 13, 86, 68, 49, 33, 57, 23, 39, 26, 69, 47 };
		sort(a, new int[a.length], 0, a.length - 1);
		for (int x : a)
			System.out.print(x + " ");
		a = new int[] { 86, 77, 30, 79, 27, 99, 19, 45, 84, 72 };
		count = 0;
		sortUp(a);
		System.out.println();
		for (int x : a)
			System.out.print(x + " ");
		System.out.println();
	}

	public static void sortUp(int[] a) {
		int length = a.length;
		int[] aux = new int[length];
		for (int gap = 1; gap < length; gap *= 2) {
			for (int low = 0; low < length - gap; low += gap * 2) {
				// if(count >= 7)
				// return;
				merge(a, aux, low, low + gap - 1,
						Math.min(length - 1, low + gap + gap - 1));
				count++;
			}
		}
	}

	public static void sort(int[] a, int[] aux, int low, int high) {
		if (high <= low)
			return;
		int mid = low + (high - low) / 2;
		sort(a, aux, low, mid);
		sort(a, aux, mid + 1, high);
		if (count >= 7)
			return;
		merge(a, aux, low, mid, high);
		// System.out.println(low + " " + mid + " " + high);
		count++;
	}

	private static void merge(int[] a, int[] aux, int low, int mid, int high) {
		for (int i = low; i <= high; i++)
			aux[i] = a[i];
		int x = low;
		int y = mid + 1;
		for (int count = low; count <= high; count++) {
			if (x > mid)
				a[count] = aux[y++];
			else if (y > high)
				a[count] = aux[x++];
			else if (aux[x] < aux[y])
				a[count] = aux[x++];
			else
				a[count] = aux[y++];
		}
	}
}
