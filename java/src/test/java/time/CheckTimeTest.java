package time;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Calendar;

import org.junit.Test;

/**
 * @author euldong.kim <euldong.kim@linecorp.com>
 */
public class CheckTimeTest {

    @Test
    public void test_instant() {
        Calendar expire = Calendar.getInstance();
        expire.add(Calendar.DATE, 1);
        System.out.println(expire.getTimeInMillis());

        // same result
        long expireTime = ZonedDateTime.now().plusDays(1).toInstant().toEpochMilli();
        System.out.println(expireTime);
    }
}
