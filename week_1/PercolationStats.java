import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] fracDist;
    private final int trials;
    private final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;

        if ((n <= 0) || (trials <= 0)) {
            throw new IllegalArgumentException();
        }

        this.fracDist = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percTrial = new Percolation(n);

            // continue opening sites until it percolates!
            while (!percTrial.percolates()) {
                int openRow = StdRandom.uniform(1, n + 1);
                int openCol = StdRandom.uniform(1, n + 1);
                percTrial.open(openRow, openCol);
            }

            fracDist[i] = (double) percTrial.numberOfOpenSites() / (n * n);
            // System.out.println(fracDist[i]);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.fracDist);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.fracDist);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(this.trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(this.trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        System.out.println("Running " + trials + " trials for " + n + "x" + n + " grid");
        PercolationStats percTest = new PercolationStats(n, trials);

        System.out.println("mean = " + percTest.mean());
        System.out.println("stddev = " + percTest.stddev());

        double confLo = percTest.confidenceLo();
        double confHi = percTest.confidenceHi();

        System.out.println("95% confidence interval = [" + confLo + "," + confHi + "]");
    }

}