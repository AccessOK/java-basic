package RecordTest;

import java.util.Date;

public class RecordTest {
    public static void main(String[] args) {
        var p = new Point(3, 4);
        System.out.println(p.distanceFromOrigin());
        var pt=new PointInTime(3,4,new Date());
        System.out.println(pt);
        pt.when().setTime(0);
        System.out.println(pt);
        var r=new Range(11,10);
        System.out.println(r);
    }
}

record Point(int x, int y) {
    public Point(){
        this(0, 0);
    }
    public double distanceFromOrigin(){
        return Math.hypot(x,y);
    }
    public static Point ORIGIN = new Point();
    public static  double distance(Point p1, Point p2){
        //x()为获取记录的属性值
        return Math.hypot(p1.x() - p2.x(), p1.y() - p2.y());
    }
}

record PointInTime(double x, double y, Date when){}

record Range(int from, int to){
    public Range{
        if(from > to){
            int tmp=from;
            from=to;
            to=tmp;
        }
    }
}