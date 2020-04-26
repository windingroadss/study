package lombok.tolerate;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.Date;

import org.junit.Test;

class TolerateExampleTest {

    @Test
    public void test() {
        Date date = new Date();
        TolerateExample tolerateExample = TolerateExample.builder().build();
        tolerateExample.setDate(date);
        tolerateExample.setDate(Instant.now()); // avoid conflicts
    }
}
