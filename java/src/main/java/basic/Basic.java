package basic;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Test;

public class Basic {

    @Test
    public void test_dateNull() {
        System.out.println(new Date(null)); // IllegalArgumentException
    }


    @Test
    public void test_set() {
        Set<String> set = Set.of("1", "2", "3");
        set.stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void test_collectionUtils() {
        String s1 = CollectionUtils.get(Set.of("1", "2", "3"), 0);
        String s2 = IterableUtils.get(Set.of("1", "2", "3"), 0);
        System.out.println(s1 + "," + s2);
        assertEquals(s1, s2);
    }

    @Test
    public void test_castNullObject() {
        Map<Integer, Object> map = Map.of(1, "1", 2, "2");
        String str = (String) map.get(3);
        System.out.println(str);
    }
}
