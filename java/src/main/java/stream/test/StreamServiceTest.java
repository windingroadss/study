package stream.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
}
