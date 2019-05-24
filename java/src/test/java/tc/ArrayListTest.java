package tc;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author euldong.kim <euldong.kim@linecorp.com>
 */
public class ArrayListTest {

    @Test
    public void test_empty() {
        ArrayList<String> arrayList = new ArrayList<>();
        assertTrue(arrayList.isEmpty());
    }
}
