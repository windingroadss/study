package stream.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import stream.StreamSpringBootApplication;
import stream.comparator.UserComparator;
import stream.model.User;
import stream.service.DuplicateUserFilterService;
import stream.service.UserCollectService;
import stream.service.UserIdFilterService;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StreamSpringBootApplication.class)
public class StreamServiceTest {

    private Stream<User> streamUser;
    private List<User> listUser = new ArrayList<>();
    private Supplier<Stream<User>> streamSupplier;

    @Autowired
    private UserIdFilterService userService;

    @Autowired
    private DuplicateUserFilterService duplicateUserFilterService;

    @Autowired
    private UserCollectService userCollectService;

    @Before
    public void init() {
        listUser.add(User.builder().id("windingroad").build());
        listUser.add(User.builder().id("windingroad2").build());
        listUser.add(User.builder().id("windingroad3").build());
        listUser.add(User.builder().id("windingroad2").build());
        listUser.add(User.builder().id("windingroad4").build());
        listUser.add(User.builder().id("windingroad1").build());
        streamSupplier = () -> listUser.stream();
    }

    @Test
    public void userService_filter_sorted() {
        // Stream variable
        streamUser = streamSupplier.get();
        streamUser = userService.filter(streamUser);
        streamUser = duplicateUserFilterService.filter(streamUser);
        streamUser.forEach(user -> log.info(user.toString()));

        log.info("#######################################");

        // Use Supplier
        streamUser = streamSupplier.get().sorted(UserComparator.getInstance());
        streamUser.forEach(user -> log.info(user.toString()));

        log.info("#######################################");

        // map test
        int index = 0;
        streamUser = duplicateUserFilterService.filter(streamSupplier.get());
        streamUser = streamUser.map(user -> User.builder().id(user.getId() + " mapped").build());
        streamUser.forEach(user -> log.info(user.toString()));

        System.out.println("done");
    }

    @Test
    public void temp() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(1, 3);
        System.out.println(arrayList);
    }

    @Test
    public void temp2() {
        Map<Integer, String> map = new TreeMap<>();
        map.put(1, "A");
        map.put(6, "A");
        map.put(4, "A");
        map.put(2, "A");

        map.entrySet()
           .stream()
           .forEach(entry -> System.out.println(entry.getKey()));
    }

    @Test
    public void test_sequential() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<Double> convertedList = list.stream().parallel().map(num -> Double.valueOf(num))
                                         .collect(Collectors.toList());

        System.out.println(list);
        System.out.println(convertedList);
    }

    @Test
    public void test_peek() {
        List<String> numbers = Stream.of("one", "two", "three", "four")
                                     .filter(e -> e.length() > 3)
                                     .peek(e -> System.out.println("Filtered value: " + e))
                                     .map(String::toUpperCase)
                                     .peek(e -> System.out.println("Mapped value: " + e))
                                     .collect(Collectors.toList());

        System.out.println(numbers);
    }

    @Test
    public void test_getUserCollector() {
        Stream<User> userStream = List.of(
            User.builder().age(20).name("A").build(),
            User.builder().age(15).name("B").build(),
            User.builder().age(4).name("C").build(),
            User.builder().age(35).name("D").build(),
            User.builder().age(30).name("E").build()).stream();

        userCollectService.getGroupedUserStream(userStream)
                          .forEach(user -> System.out.println(user));

    }
}
