package spring.lombok;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class ExampleModelToBuilderTest {

    @Test
    public void test_toBuilder() {
        ExampleModelToBuilder example = new ExampleModelToBuilder().toBuilder().build();
        ExampleModelToBuilder example2 = ExampleModelToBuilder.builder()
                                                              .build();
        System.out.println(example);
        System.out.println(example2);
    }
}