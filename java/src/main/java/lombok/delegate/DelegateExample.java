package lombok.delegate;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Delegate;

@Builder
@Value
public class DelegateExample {

    private interface Name {
        String getName();
    }

    @Delegate(types = Person.class, excludes = Name.class)
    private final Person person;

    @Builder
    @Value
    static class Person {
        int age;
        String name;
    }
}
