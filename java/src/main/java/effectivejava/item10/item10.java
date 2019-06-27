package effectivejava.item10;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.junit.Test;

import effectivejava.item10.inheritance.ColorPoint;
import effectivejava.item10.inheritance.SmellPoint;

public class item10 {

    @Test
    public void test_regexEquals() {
        final Pattern P1 = Pattern.compile("//.*");
        final Pattern P2 = Pattern.compile("//.*");

        System.out.println(P1.equals(P1)); // true
        System.out.println(P1.equals(P2)); // false, check the reference
        System.out.println(P1.pattern().equals(P1.pattern())); // true
        System.out.println(P1.pattern().equals(P2.pattern())); // true
    }

    @Test
    public void test_setMapEquals() {
        Set<String> set = new HashSet<>();
        set.equals(set);
    }

    @Override
    public boolean equals(Object o) {
        throw new AssertionError(); // 호출 금지
    }

    @Test
    public void test_preventCallingEquals() {
        item10 a = new item10();
        item10 b = new item10();
        a.equals(b);
    }

    @Test
    public void test_smellPoint() {
        Point cp = new ColorPoint(1, 2, Color.RED);
        Point sp = new SmellPoint(1, 2, Smell.SWEET);

        System.out.println(cp.equals(sp));  //?
    }

    @Test
    public void test_transitivity() {
        ColorPoint a = new ColorPoint(1, 2, Color.RED);
        Point b = new Point(1, 2);
        ColorPoint c = new ColorPoint(1, 2, Color.BLUE);


    }
}
