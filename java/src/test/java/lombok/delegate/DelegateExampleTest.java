package lombok.delegate;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lombok.delegate.DelegateExample.Person;

public class DelegateExampleTest {

    @Test
    public void test() {
        DelegateExample delegateExample = DelegateExample.builder()
                                                         .person(Person.builder()
                                                                       .age(20)
                                                                       .name("delegate test")
                                                                       .build())
                                                         .build();

        assertEquals(20, delegateExample.getAge());
        // assertEquals("delegate test", delegateExample.getName()); // invalid because of "excludes"
    }
}
