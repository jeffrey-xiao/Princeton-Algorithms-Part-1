package Deques;

public class Subset {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		int n = 1;
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (n > k) {
				if (getRandom(n) <= k) {
					rq.dequeue();
					rq.enqueue(s);
				}
			} else {
				rq.enqueue(s);
			}
			n++;
		}
		for (int x = 0; x < k; x++)
			StdOut.println(rq.dequeue());
	}

	private static int getRandom(int i) {
		return (int) (Math.random() * i + 1);
	}
}
