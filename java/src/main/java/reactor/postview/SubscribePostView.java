package reactor.postview;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SubscribePostView {

    public void mono() {
        int postLimit = 5;

        List<String> postIds = Arrays.asList("a", "b", "c");
        List<String> result = new ArrayList();

        Mono<List<String>> mono = Mono.just(postIds);
        mono.map(List::hashCode).repeatWhen(repeat -> repeat.zipWith(Flux.range(0, Integer.MAX_VALUE),
                                                 (t1, t2) -> String.format("%d, %d", t1, t2)))
            .subscribe(System.out::println);
        //mono.subscribe(System.out::println);
        //mono.repeatWhen().;
    }

    @Test
    public void test_mono() {
        mono();
    }

    public Flux<String> exponentialRepeatScenario2() {
        AtomicInteger i = new AtomicInteger();
        return Mono.<String>create(s -> {
            if (i.incrementAndGet() == 4) {
                s.success("hey");
            } else {
                s.success();
            }
        }).repeatWhen(repeat -> repeat.zipWith(Flux.range(1, 3), (t1, t2) -> t2)
                                      .flatMap(time -> Mono.delay(Duration.ofSeconds(time))));
    }

    @Test
    public void test_exponentialRepeatScenario2() throws InterruptedException {
        List<String> elements = new ArrayList();
        Flux<String> flux = exponentialRepeatScenario2();
        flux.subscribe(System.out::println);
        //elements.forEach(s -> System.out.println(s));
    }

    private static Mono<String> check(int i) {
        return Mono.fromSupplier(
            () -> {
                if (i % 2 == 0) {
                    return i + " is even";
                } else {
                    return i + " is odd";
                }
            }).subscribeOn(Schedulers.parallel());
    }

}
