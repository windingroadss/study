package effectivejava.item10.inheritance;

import effectivejava.item10.Color;
import effectivejava.item10.Point;
import effectivejava.item10.Smell;

// Attempting to add a value component to Point (Page 41)
public class SmellPoint extends Point {
    private final Smell smell;

    public SmellPoint(int x, int y, Smell color) {
        super(x, y);
        this.smell = color;
    }

    // Broken - violates symmetry!  (Page 41)
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof SmellPoint)) { return false; }
//        return super.equals(o) && ((SmellPoint) o).smell == smell;
//    }

    // Broken - violates transitivity! (page 42)
    @Override public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;

        // If o is a normal Point, do a smell-blind comparison
        if (!(o instanceof SmellPoint))
            return o.equals(this);

        // o is a ColorPoint; do a full comparison
        return super.equals(o) && ((SmellPoint) o).smell == smell;
    }

    public static void main(String[] args) {
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);
        SmellPoint sp = new SmellPoint(1, 2, Smell.SALTY);

        // endless loop
        cp.equals(sp);

        /*
            Exception in thread "main" java.lang.StackOverflowError
            at effectivejava.item10.inheritance.ColorPoint.equals(ColorPoint.java:29)
            at effectivejava.item10.inheritance.SmellPoint.equals(SmellPoint.java:30)
            at effectivejava.item10.inheritance.ColorPoint.equals(ColorPoint.java:29)
            at effectivejava.item10.inheritance.SmellPoint.equals(SmellPoint.java:30)
            at effectivejava.item10.inheritance.ColorPoint.equals(ColorPoint.java:29)
            at effectivejava.item10.inheritance.SmellPoint.equals(SmellPoint.java:30)
            at effectivejava.item10.inheritance.ColorPoint.equals(ColorPoint.java:29)
            at effectivejava.item10.inheritance.SmellPoint.equals(SmellPoint.java:30)
            at effectivejava.item10.inheritance.ColorPoint.equals(ColorPoint.java:29)
            ...
         */
    }
}
