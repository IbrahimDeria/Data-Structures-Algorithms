import java.util.ArrayList;
import java.util.List;

public class KClosest {

    public static List<Point> find(List<Point> points, Point p, int k) {

        MinHeap<Point> mh = new MinHeap<>();

        for (int i = 0; i < points.size(); i++) {

            points.get(i);

            double pointDistance = distance(points.get(i), p);

            mh.offer(points.get(i), pointDistance);

        }

        ArrayList<Point> closePoint = new ArrayList<>();

        for (int i = 0; i < k; i++) {

            Point x = mh.poll();

            closePoint.add(x);

        }

        return closePoint;

    }

    private static double distance(Point p1, Point p2) {

        double length = Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));

        return length;

    }
}