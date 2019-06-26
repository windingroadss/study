package tc;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.web.util.UriComponentsBuilder;

public class ArrayListTest {

    @Test
    public void test_empty() {
        ArrayList<String> arrayList = new ArrayList<>();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void test_null_string() {
        System.out.println("abc" + null);
    }

    @Test
    public void dubleSlash() {
        URI requestURI = UriComponentsBuilder.fromUriString("www.test.co.kr")
                                             .path("/api/v1/feed//delete/cache/index")
                                             .build()
                                             .toUri();

        System.out.println(requestURI);
    }
}
