package effectivejava.item10.inheritance;

import java.util.concurrent.atomic.AtomicInteger;

import effectivejava.item10.Point;

// Trivial subclass of Point - doesn't add a value component (Page 43)
public class CounterPoint extends Point {
    private static final AtomicInteger counter =
        new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public static int numberCreated() { return counter.get(); }
}
