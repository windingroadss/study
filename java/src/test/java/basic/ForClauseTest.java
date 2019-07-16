package basic;

import org.junit.Test;

public class ForClauseTest {

    private static final int RETRY_LIMIT = 1;

    @Test
    public void test_doWhile() {
        int loopCount = 0;

        do {
            System.out.println("count: " + loopCount);
        } while (++loopCount < RETRY_LIMIT); // retry loop
    }
}
