package spring.lombok;

import org.junit.Test;

public class ExampleModelTest {

    @Test
    public void test_default() {
        ExampleModel exampleModel = ExampleModel.builder().build();
        System.out.println(exampleModel);

        ExampleModel exampleModel2 = ExampleModel.builder()
                                                 .a(5)
                                                 .build();
        System.out.println(exampleModel2);
    }

}