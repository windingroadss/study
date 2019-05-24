package tc;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AssertTest {

    @Test
    public void containsTest() {
        List<String> actual = new ArrayList<>();
        actual.add("1");
        actual.add("2");
        actual.add("3");

        List<String> expect = new ArrayList<>();
        expect.add("0");
        assertThat(actual, everyItem(not(isIn(expect))));

        expect.remove(0);
        expect.add("1");
        expect.add("2");
        expect.add("3");
        assertThat(actual, contains(expect.toArray()));
    }
}
