package effectivejava.item63;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class PerformanceTest {

    private static int NUM_ITEMS = 80;
    private static int LINE_WIDTH = 100;
    private String lines[];

    @Before
    public void setLines() {
        lines = new String[NUM_ITEMS];
        for (int i = 0; i < NUM_ITEMS; i++) {
            lines[i] = strLineGenerator();
        }
    }

    private String strLineGenerator() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(LINE_WIDTH);
        for (int i = 0; i < LINE_WIDTH; i++) {
            int randomLimitedInt = leftLimit + (int)
                (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    @Test
    public void test1() {
        long start = System.currentTimeMillis();

        // 연결 연산자(+) 사용
        String result = "";
        for (int i = 0; i < NUM_ITEMS; i++) {
            result += lines[i];
        }

        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println((end - start) / (double) 1000);
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();

        // StringBuilder 사용
        StringBuilder b = new StringBuilder(NUM_ITEMS * LINE_WIDTH);
        for (int i = 0; i < NUM_ITEMS; i++) {
            b.append(lines[i]);
        }

        long end = System.currentTimeMillis();
        System.out.println(b.toString());
        System.out.println((end - start) / (double) 1000);
    }

}
