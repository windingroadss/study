package async;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class CompletableFutureExampleTest {

    @Test
    public void test1() {
        CompletableFutureExample.test1();
        CompletableFutureExample.sleep(2000);
    }

    @Test
    public void test2() {
        CompletableFutureExample.test2();
        CompletableFutureExample.sleep(2000);
    }

    @Test
    public void test3() {
        CompletableFutureExample.test3();
        CompletableFutureExample.sleep(2000);
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFutureExample.test4();
        CompletableFutureExample.sleep(2000);
    }

    @Test
    public void test_whenComplete() {
        CompletableFutureExample.test_whenComplete();
        CompletableFutureExample.sleep(3000);
    }
}