import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private ArrayList<LineSegment> foundSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException();
        }

        foundSegments = new ArrayList<LineSegment>();

        // sort points by natural order

        Point[] pointsCopy = new Point[points.length];
        Point[] sortedPoints = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }

            sortedPoints[i] = points[i];
            pointsCopy[i] = points[i];
        }

        Arrays.sort(pointsCopy);

        for (int i = 0; i < pointsCopy.length - 1; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        // for loop over each point p
        for (int i = 0; i < pointsCopy.length; i++) {
            // get a list of points sorted by slope wrt p, break ties using natural order
            Point pointCompare = pointsCopy[i];
            PointComparator pComp = new PointComparator(pointCompare);
            Comparator<Point> slopeComp = pointCompare.slopeOrder();

            Arrays.sort(sortedPoints, pComp);

            int segmentLength = 2;
            Point startPoint = pointCompare;
            Point endPoint = pointCompare;

            for (int j = 1; j < sortedPoints.length - 1; j++) {
                Point p = sortedPoints[j];
                Point q = sortedPoints[j + 1];

                if (slopeComp.compare(p, q) == 0) {
                    segmentLength++;

                    if (p.compareTo(startPoint) < 0) {
                        startPoint = p;
                    }

                    if (q.compareTo(endPoint) > 0) {
                        endPoint = q;
                    }
                } else {
                    if ((segmentLength > 3) && !(startPoint.compareTo(pointCompare) < 0)) {
                        LineSegment ls = new LineSegment(startPoint, endPoint);
                        foundSegments.add(ls);
                    }

                    segmentLength = 2;
                    startPoint = pointCompare;
                    endPoint = pointCompare;
                }
                if ((j + 1) == (sortedPoints.length - 1) && segmentLength > 3
                        && !(startPoint.compareTo(pointCompare) < 0)) {
                    LineSegment ls = new LineSegment(startPoint, endPoint);
                    foundSegments.add(ls);
                }

            }
        }

    }

    private class PointComparator implements Comparator<Point> {
        public final Point pointCompare;

        public PointComparator(Point point) {
            this.pointCompare = point;
        }

        public int compare(Point p1, Point p2) {
            Comparator<Point> slopeOrder = pointCompare.slopeOrder();

            int slopeOrderCompare = slopeOrder.compare(p1, p2);

            if (slopeOrderCompare == 0) {
                return p1.compareTo(p2);
            }

            else {
                return slopeOrderCompare;
            }

        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return foundSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return foundSegments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        // In in = new In(args[0]);
        In in = new In("./week_3/test_inputs/input400.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
