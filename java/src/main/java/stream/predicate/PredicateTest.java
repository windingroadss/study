package stream.predicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import stream.StreamSpringBootApplication;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StreamSpringBootApplication.class)
public class PredicateTest {

    @Test
    public void test_predicateOr() {
        final Set<NumType> numTypes = Set.of(NumType.EVEN, NumType.ZERO);
        final ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(0);
        numbers.add(1);
        numbers.add(3);
        numbers.add(4);
        final Predicate<Integer> predicateResult = numTypes.stream()
                                                           .map(integerFilter::get)
                                                           .filter(Objects::nonNull)
                                                           .reduce(Predicate::or)
                                                           .orElse(item -> false);

        final List<Integer> resultNumList = numbers.stream()
                                                 .filter(predicateResult)
                                                 .collect(Collectors.toList());

        for (Integer a : resultNumList) {
            log.info("number: {}", a);
        }

    }

    public enum NumType {
        ODD,
        EVEN,
        ZERO
    }

    private static final Map<NumType, Predicate<Integer>> integerFilter;

    static {
        Map<NumType, Predicate<Integer>> tmp = new EnumMap<>(NumType.class);
        tmp.put(NumType.ODD, item -> item % 2 != 0 && item != 0);
        tmp.put(NumType.EVEN, item -> item % 2 == 0 && item != 0);
        tmp.put(NumType.ZERO, item -> item == 0);
        integerFilter = Collections.unmodifiableMap(tmp);
    }
}
