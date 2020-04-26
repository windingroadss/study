package lombok.tolerate;

import java.time.Instant;
import java.util.Date;

import lombok.Builder;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Builder
public class TolerateExample {

    @Setter
    private Date date;

    @Tolerate
    public void setDate(Instant instant) {
        date = Date.from(instant);
    }
}
