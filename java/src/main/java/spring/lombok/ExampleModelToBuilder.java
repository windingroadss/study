package spring.lombok;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Builder(toBuilder = true)
@Value
@ToString
public class ExampleModelToBuilder {
    private int a = 1;
    private int b = 2;
}
