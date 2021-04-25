import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {

    private boolean[] openStates;
    private boolean[] fullStates;
    private boolean[] connectedBottom;
    private boolean percolateState;

    private final WeightedQuickUnionUF quickUnion;
    private final int n;
    private int numOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.percolateState = false;
        this.fullStates = new boolean[n * n];
        this.quickUnion = new WeightedQuickUnionUF(n * n);

        this.n = n;
        this.openStates = new boolean[n * n];
        this.connectedBottom = new boolean[n * n];
        this.numOpenSites = 0;
    }

    private void union(int siteInd1, int siteInd2) {
        int rootSiteInd1 = this.quickUnion.find(siteInd1);
        int rootSiteInd2 = this.quickUnion.find(siteInd2);

        boolean connected = rootSiteInd1 == rootSiteInd2;

        if (isOpen(siteInd1) && isOpen(siteInd2) && !connected) {

            this.quickUnion.union(rootSiteInd1, rootSiteInd2);
            // System.out.println("Uniting " + (siteInd1 + 1) + " with " + (siteInd2 + 1));
            // System.out.println(isConnected(siteInd1, siteInd2));

            if (this.fullStates[rootSiteInd1]) {
                this.fullStates[rootSiteInd2] = true;
                this.fullStates[siteInd2] = true;
                this.fullStates[siteInd1] = true;

            } else if (this.fullStates[rootSiteInd2]) {
                this.fullStates[rootSiteInd1] = true;
                this.fullStates[siteInd2] = true;
                this.fullStates[siteInd1] = true;
            }

            if (this.connectedBottom[rootSiteInd1]) {
                this.connectedBottom[rootSiteInd2] = true;
                this.connectedBottom[siteInd2] = true;
                this.connectedBottom[siteInd1] = true;

            } else if (this.connectedBottom[rootSiteInd2]) {
                this.connectedBottom[rootSiteInd1] = true;
                this.connectedBottom[siteInd2] = true;
                this.connectedBottom[siteInd1] = true;
            }

            if (this.connectedBottom[rootSiteInd1]) {
                this.connectedBottom[rootSiteInd2] = true;
                this.connectedBottom[siteInd2] = true;
                this.connectedBottom[siteInd1] = true;

            } else if (this.connectedBottom[rootSiteInd2]) {
                this.connectedBottom[rootSiteInd1] = true;
                this.connectedBottom[siteInd2] = true;
                this.connectedBottom[siteInd1] = true;
            }

            if (this.connectedBottom[rootSiteInd1]) {
                if (this.fullStates[rootSiteInd1]) {
                    this.percolateState = true;
                }
            }

        }
    }

    private int getSiteInd(int row, int col) {
        return (col - 1) + n * (row - 1);
    }

    private void checkInput(int row, int col) {
        if ((row <= 0) || (row > this.n)) {
            throw new IllegalArgumentException();
        }

        if ((col <= 0) || (col > this.n)) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkInput(row, col);

        int rowId = row - 1;
        int colId = col - 1;

        int siteInd = getSiteInd(row, col);

        if (!isOpen(row, col)) {
            this.numOpenSites++;
            this.openStates[siteInd] = true;
        }

        if (rowId == 0) {
            this.fullStates[siteInd] = true;
        }

        if (rowId == this.n - 1) {
            this.connectedBottom[siteInd] = true;
        }

        // check for site above
        if (rowId != 0) {
            int siteIndUp = siteInd - this.n;
            union(siteInd, siteIndUp);
        }

        // check for site below
        if (rowId != (this.n - 1)) {
            int siteIndDown = siteInd + this.n;
            union(siteInd, siteIndDown);
        }

        // check for site to the left
        if (colId != 0) {
            int siteIndLeft = siteInd - 1;
            union(siteInd, siteIndLeft);
        }

        // check for site to the right
        if (col != this.n) {
            int siteIndRight = siteInd + 1;
            union(siteInd, siteIndRight);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkInput(row, col);
        return openStates[getSiteInd(row, col)];
    }

    private boolean isOpen(int siteInd) {
        return this.openStates[siteInd];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkInput(row, col);

        int siteInd = getSiteInd(row, col);
        if (this.fullStates[siteInd]) {
            return true;
        } else {
            int rootInd = this.quickUnion.find(siteInd);
            // System.out.println(rootId);
            // System.out.println(site.siteId);

            this.fullStates[siteInd] = this.fullStates[rootInd];
            return this.fullStates[rootInd];
        }

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.percolateState;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 3;
        Percolation perc = new Percolation(n);

        for (int i = 0; i < 20; i++) {
            int openRow = StdRandom.uniform(1, n + 1);
            int openCol = StdRandom.uniform(1, n + 1);
            // System.out.println("Opening " + openRow + " " + openCol);
            perc.open(openRow, openCol);
            // perc.printGridIsOpen();
        }
    }
}