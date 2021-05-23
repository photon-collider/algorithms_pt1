import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int nSegments;
    private final ArrayList<LineSegment> foundSegments;

    public BruteCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }

        nSegments = 0;
        foundSegments = new ArrayList<LineSegment>();

        int nPoints = points.length;

        for (int i = 0; i < nPoints; i++) {
            for (int j = i + 1; j < nPoints; j++) {
                double slopeToJ = points[i].slopeTo(points[j]);
                if (slopeToJ == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }

                for (int k = j + 1; k < nPoints; k++) {

                    if (points[i].equals(points[j])) {
                        throw new IllegalArgumentException();
                    }

                    double slopeToK = points[i].slopeTo(points[k]);

                    if (slopeToK == Double.NEGATIVE_INFINITY) {
                        throw new IllegalArgumentException();
                    }

                    if (slopeToJ != slopeToK) {
                        continue;
                    }

                    for (int l = k + 1; l < nPoints; l++) {
                        double slopeToL = points[i].slopeTo(points[l]);

                        if (slopeToL == Double.NEGATIVE_INFINITY) {
                            throw new IllegalArgumentException();
                        }

                        if ((slopeToJ == slopeToK) && (slopeToJ == slopeToL)) {
                            Point[] collinearPoints = new Point[4];
                            collinearPoints[0] = points[i];
                            collinearPoints[1] = points[j];
                            collinearPoints[2] = points[k];
                            collinearPoints[3] = points[l];

                            Arrays.sort(collinearPoints);

                            LineSegment ls = new LineSegment(collinearPoints[0], collinearPoints[3]);
                            foundSegments.add(ls);
                            nSegments++;
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return nSegments;
    }

    public LineSegment[] segments() {
        return foundSegments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        // In in = new In(args[0]);
        In in = new In("./week_3/test_inputs/input10.txt");

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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
