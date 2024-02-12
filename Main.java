// DJV-Inherit/Main.java
 
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        Point pp = new Point(2,1);
        Point pt = new Pixel(1,2);
        Pixel px = new Pixel(new Color(255,51,102));

        System.out.println("is 'pp' a Point? " +
                (pp instanceof Point));
        System.out.println("is 'pp' a Pixel? " +
                (pp instanceof Pixel));
        System.out.println("is 'pt' a Point? " +
                (pt instanceof Point));
        System.out.println("is 'pt' a Pixel? " +
                (pt instanceof Pixel));
        System.out.println("is 'px' a Point? " +
                (px instanceof Point));
        System.out.println("is 'px' a Pixel? " +
                (px instanceof Pixel));
        System.out.println("class of pp: " +
                pp.getClass().getName());
        System.out.println("class of pt: " +
                pt.getClass().getName());
        System.out.println("class of px: " +
                px.getClass().getName());
          // Pixel is a Point; we can translate or scale it
        px.translate(5,4).scale(2,3).translate(-1,-3);
        System.out.println("pp: " + pp);
        System.out.println("pt: " + pt);
        System.out.println("px: " + px);
        System.out.println("Color px : " + px.getColor());
          // casting required!
        System.out.println("Color pt : " +
                           ((Pixel)pt).getColor());
    }
}
