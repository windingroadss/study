package reactor;

import static java.time.Duration.ofSeconds;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * https://www.baeldung.com/reactor-core
 */
public class ReactorExample {
  @Test
  public void basicTest() {
    Flux<String> flux = Flux.just("1", "2", "3");
    Mono<String> mono = Mono.just("foo");

    List<Integer> elements = new ArrayList<>();

    Flux.just(1, 2, 3, 4)
        .log()
        .subscribe(elements::add);

    assertThat(elements, contains(1, 2, 3, 4));
  }

  @Test
  void subscribeTest() {
    List<Integer> elements = new ArrayList<>();

    Flux.just(1, 2, 3, 4)
        .log()
        .subscribe(new Subscriber<Integer>() {
          @Override
          public void onSubscribe(Subscription s) {
            s.request(Long.MAX_VALUE);
          }

          @Override
          public void onNext(Integer integer) {
            elements.add(integer);
          }

          @Override
          public void onError(Throwable t) {}

          @Override
          public void onComplete() {}
        });

    List<Integer> collected = Stream.of(1, 2, 3, 4)
                                    .collect(toList());

    collected.forEach((n) -> System.out.println(n));

  }

  @Test
  void testRequest() {
    List<Integer> elements = new ArrayList<>();

    /**
     * If tweets were coming in but there are no requests from the downstream,
     * then the upstream could drop items,
     * store them in a buffer, or some other strategy.
     */
    Flux.just(1, 2, 3, 4)
        .log()
        .subscribe(new Subscriber<Integer>() {
          private Subscription s;
          int onNextAmount;

          @Override
          public void onSubscribe(Subscription s) {
            this.s = s;
            s.request(2); // 최초 request 2 elements
          }

          @Override
          public void onNext(Integer integer) {
            elements.add(integer);
            onNextAmount++;
            if (onNextAmount % 2 == 0) {
              s.request(2); // 끝날 때까지 request 2 elements
            }
          }

          @Override
          public void onError(Throwable t) {}

          @Override
          public void onComplete() {}
        });
  }

  @Test
  void mappingDataInStreamTest() {
    List<Integer> elements = new ArrayList<>();

    Flux.just(1, 2, 3, 4)
        .log()
        .map(i -> i * 2)
        .subscribe(elements::add);

    elements.forEach((n) -> System.out.println(n));
  }

  @Test
  void zipTest() {
    // combining two streams
    List<String> elements = new ArrayList<>();

    Flux.just(1, 2, 3, 4)
        .log()
        .map(i -> i * 2)
        .zipWith(Flux.range(0, Integer.MAX_VALUE),
                 (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
        .subscribe(elements::add);

    elements.forEach((s) -> System.out.println(s));

    assertThat(elements, contains(
      "First Flux: 2, Second Flux: 0",
      "First Flux: 4, Second Flux: 1",
      "First Flux: 6, Second Flux: 2",
      "First Flux: 8, Second Flux: 3")
    );
  }

  @Test
  void connectableFluxTest() {

    ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
      while (true) {
        fluxSink.next(System.currentTimeMillis());
      }
    }).publish();

    publish.subscribe(System.out::println);
    publish.subscribe(System.out::println);

    /**
     * It’s not until we call connect(), that the Flux will start emitting. It doesn’t matter whether we are subscribing or not.
     * publish.connect();
     */
  }

  @Test
  void throttlingTest() {
    ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
      while (true) {
        fluxSink.next(System.currentTimeMillis());
      }
    })
                                          .sample(ofSeconds(2))
                                          .publish();

    publish.subscribe(System.out::println);

    publish.connect();
  }

  @Test
  void concurrencyTest() {
    List<Integer> elements = new ArrayList<>();

    /**
     * The Scheduler interface provides an abstraction around asynchronous code,
     * for which many implementations are provided for us.
     * Let’s try subscribing to a different thread to main:
     */
    Flux.just(1, 2, 3, 4)
        .log()
        .map(i -> i * 2)
        .subscribeOn(Schedulers.parallel())
        .subscribe(elements::add);

    System.out.println("Before sleep");
    elements.forEach((n) -> System.out.println(n));

    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("After sleep");
    elements.forEach((n) -> System.out.println(n));
  }
}
