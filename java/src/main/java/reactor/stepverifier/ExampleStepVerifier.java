package reactor.stepverifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ExampleStepVerifier {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("foo");
        list.add("bar");
        Flux<String> flux = Flux.fromIterable(list);
        StepVerifier.create(flux)
                    .expectNext("foo", "bar")
                    .expectComplete()
                    .verify();
    }

    @Test
    public void test_exception() {
        StepVerifier.create(createException(true))
                    .expectErrorMatches(throwable -> throwable instanceof RuntimeException)
                    .verify();

        StepVerifier.create(createException(false))
                    .expectNext("foo", "bar")
                    .verifyComplete();
    }

    private Flux createException(boolean isError) throws RuntimeException {
        Flux<String> flux = Flux.fromIterable(List.of("foo", "bar"));

        if(isError) {
            return Flux.error(new RuntimeException("exception test"));
        }

        return flux;
    }
}
