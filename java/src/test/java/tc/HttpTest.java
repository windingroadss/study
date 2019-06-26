package tc;

import java.net.URI;

import org.junit.Test;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpTest {

    @Test
    public void dubleSlash() {
        URI requestURI = UriComponentsBuilder.fromUriString("www.test.co.kr")
                                             .path("/api/v1/feed//delete/cache/index")
                                             .build()
                                             .toUri();

        System.out.println(requestURI);
    }
}
