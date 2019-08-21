package tc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

public class StreamTest {

    @Test
    public void test_flatMap() {
        // https://www.mkyong.com/java8/java-8-flatmap-example/

        Cat cat1 = Cat.builder()
                      .age(1)
                      .name("star")
                      .hobbies(Sets.newSet(Hobby.builder().hobbyName("tv").build(),
                                           Hobby.builder().hobbyName("hunt").build()))
                      .build();

        Cat cat2 = Cat.builder()
                      .age(2)
                      .name("rang")
                      .hobbies(Sets.newSet(Hobby.builder().hobbyName("hunt").build(),
                                           Hobby.builder().hobbyName("soccer").build(),
                                           Hobby.builder().hobbyName("bite").build()))
                      .build();

        List<Object> list = Arrays.asList(cat1, cat2);
        Stream<Hobby> stream = list.stream()
                                    .map(cat -> (Cat) cat)
                                    .map(Cat::getHobbies)
                                    .flatMap(hobbies -> hobbies.stream());

        stream.forEach(hobby -> System.out.println(hobby));
    }

    @Builder
    @Data
    static class Cat {
        String name;
        int age;
        Set<Hobby> hobbies;
    }

    @Builder
    @ToString
    static class Hobby {
        String hobbyName;
    }

    @Test
    public void test_emptyList() {
        List<String> list = new ArrayList<>();
        list.stream()
            .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test_nullList() {
        List<String> list = null;
        list.stream()
            .collect(Collectors.toList());
        System.out.println(list);
    }
}
