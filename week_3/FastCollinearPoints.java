public class FastCollinearPoints {

    private int numSegments;
    private LineSegment[] foundSegments;
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points){

        // sort points by natural order

        // for loop over each point p
        //      get a list of points sorted by slope wrt p, break ties using natural order
        //      find line segments with at least 4 points
        //      if initial point q in segment is less than p in terms of natural order, this is a duplicate segment!
        
    }
    
    // the number of line segments
    public int numberOfSegments() {
        return numSegments;
    }
    
    // the line segments
    public LineSegment[] segments(){
        return foundSegments;
    }                
 }
