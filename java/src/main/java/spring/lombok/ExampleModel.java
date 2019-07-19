package spring.lombok;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Builder
@Value
@ToString
public class ExampleModel {
    @Builder.Default
    private int a = 1;
    private int b = 2;
}
