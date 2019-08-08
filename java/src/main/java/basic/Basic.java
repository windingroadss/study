package basic;

import java.util.Date;

import org.junit.Test;

public class Basic {

    @Test
    public void test_dateNull() {
        System.out.println(new Date(null)); // IllegalArgumentException
    }
}
