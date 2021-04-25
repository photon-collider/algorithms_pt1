
public class PercolationImp {

    private Site[][] grid;

    private int n;
    private int numOpenSites;

    private class Coord {
        public int rowId;
        public int colId;

        public Coord(int rowId, int colId) {
            this.rowId = rowId;
            this.colId = colId;
        }
    }

    private class Site {
        public int siteId;
        public Coord position;
        public Coord rootPosition;
        private boolean openState;
        private boolean fullState;

        private int numChildren;

        public Site(int row, int col, int n) {
            this.openState = false;
            this.siteId = (col + n * (row - 1));
            this.numChildren = 0;

            int rowId = row - 1;
            int colId = col - 1;

            this.position = new Coord(rowId, colId);
            this.rootPosition = new Coord(rowId, colId);

            if (rowId == 0) {
                this.fullState = true;
            } else {
                this.fullState = false;
            }
        }

        public boolean isFull() {
            return this.fullState;
        }

        private void makeFull() {
            this.fullState = true;
        }

        public void open() {
            this.openState = true;
        }

        public boolean isOpen() {
            return this.openState;
        }

        public void union(Site site2) {
            if (this.isFull()) {
                site2.makeFull();
            } else if (site2.isFull()) {
                this.makeFull();
            }

            if (this.numChildren < site2.numChildren) {
                this.rootPosition = site2.position;
            } else {
                site2.rootPosition = this.position;
                this.numChildren = 1 + site2.numChildren;
            }
        }

        public boolean isEqual(Site site2) {
            return this.siteId == site2.siteId;
        }
    }

    // creates n-by-n grid, with all sites initially blocked
    public PercolationImp(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.grid = new Site[n][n];

        this.numOpenSites = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                // this.grid[row-1][col-1] = (col + n*(row-1) );
                this.grid[row - 1][col - 1] = new Site(row, col, n);
            }
        }
    }

    private Site getSite(Coord coord) {
        return this.grid[coord.rowId][coord.colId];
    }

    private Site getRootSite(Site site) {
        Site rootSite = getSite(site.rootPosition);

        if (rootSite.siteId != site.siteId) {
            return getRootSite(rootSite);
        } else {
            return rootSite;
        }
    }

    private void union(Site site1, Site site2) {

        Site rootSite1 = getRootSite(site1);
        Site rootSite2 = getRootSite(site2);
        boolean connected = rootSite1.isEqual(rootSite2);

        if (site1.isOpen() && site2.isOpen() && !connected) {
            rootSite1.union(rootSite2);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if ((row <= 0) || (row > this.n)) {
            throw new IllegalArgumentException();
        }

        if ((col <= 0) || (col > this.n)) {
            throw new IllegalArgumentException();
        }

        int rowId = row - 1;
        int colId = col - 1;

        Site openSite = this.grid[rowId][colId];
        openSite.open();

        this.numOpenSites++;

        // check for site above
        if (rowId != 0) {
            Site siteUp = this.grid[rowId - 1][colId];
            union(openSite, siteUp);
        }

        // check for site below
        if (rowId != (this.n - 1)) {
            Site siteDown = this.grid[rowId + 1][colId];
            union(openSite, siteDown);
        }

        // check for site to the left
        if (colId != 0) {
            Site siteLeft = this.grid[rowId][colId - 1];
            union(openSite, siteLeft);
        }

        // check for site to the right
        if (col != this.n) {
            Site siteRight = this.grid[rowId][colId + 1];
            union(openSite, siteRight);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return getSite(new Coord(row - 1, col - 1)).isOpen();
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        Site site = this.grid[row - 1][col - 1];
        return site.isFull();
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {

        // check if any sites at the bottom are full
        for (int i = 0; i < this.n; i++) {
            Site bottomSite = this.grid[this.n - 1][i];
            if (bottomSite.isFull()) {
                return true;
            }
        }
        return false;
    }

    public void printGrid() {
        for (int row = 1; row <= this.n; row++) {
            String rowStr = "";
            for (int col = 1; col <= this.n; col++) {
                Site site = this.grid[row - 1][col - 1];

                if (site.isOpen()) {
                    // rowStr = rowStr + " " + site.siteId;
                    rowStr = rowStr + " " + getRootSite(site).siteId;
                } else {
                    rowStr = rowStr + " " + "b";
                }
            }
            System.out.println(rowStr);
        }
        if (percolates()) {
            System.out.println("This percolates!");
        } else {
            System.out.println("This doesn't percolate");
        }
        System.out.println("");

    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 5;

        PercolationImp p = new PercolationImp(n);
        p.printGrid();

        p.open(4, 5);
        p.printGrid();

        p.open(3, 1);
        p.printGrid();

        p.open(4, 3);
        p.printGrid();

        p.open(4, 4);
        p.printGrid();

        p.open(2, 1);
        p.printGrid();

        p.open(1, 1);
        p.printGrid();

        p.open(3, 2);
        p.printGrid();

        p.open(3, 3);
        p.printGrid();

        p.open(5, 2);
        p.printGrid();
    }
}