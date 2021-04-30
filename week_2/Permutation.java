package week_2;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = StdIn.readInt();
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        int j = 0;

        for (String s : rq) {
            if (j < k) {
                System.out.println(s);
            }
        }

    }
}
