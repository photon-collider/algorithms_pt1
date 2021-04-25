
public class Percolation {

    private int[][] grid; 
    private int n;
    private int numOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        this.n = n;
        this.grid = new int[n][n];
        
        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= n; col++){
                // this.grid[row-1][col-1] = (col + n*(row-1) );
                this.grid[row-1][col-1] = -1;
            }

        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        this.grid[row-1][col-1] = (col + n*(row-1) );
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return this.grid[row-1][col-1] != -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return 0;
    }

    // does the system percolate?
    public boolean percolates(){
        return false;
    }

    public void printGrid(){
        for(int row = 1; row <= this.n; row++){
            String rowStr = "";
            for(int col = 1; col <= this.n; col++){
                rowStr = rowStr + " " + this.grid[row-1][col-1];
            }
            System.out.println(rowStr);
        }
        System.out.println("");
    }

    // test client (optional)
    public static void main(String[] args){
        int n = 5;

        Percolation p = new Percolation(n);
        p.printGrid();
        p.open(4,5);
        p.open(3,2);
        p.printGrid();
    }
}