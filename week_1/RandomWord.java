import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int i = 1;

        while (!StdIn.isEmpty()) {
            double p = 1.0 / i;
            String newWord = StdIn.readString();
            if (StdRandom.bernoulli(p)) {
                champion = newWord;
            }
            i++;
        }

        StdOut.println(champion);
    }
}
