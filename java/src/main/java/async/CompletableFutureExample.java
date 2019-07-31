package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    public static void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test1() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        CompletableFuture
            .runAsync(() -> {
                sleep(1000);
                System.out.println("Hello!");
            }, executor)
            .thenRun(() -> System.out.println("World"));

        System.out.println("async request is ready.");
    }

    public static void test2() {
        CompletableFuture cf =
            CompletableFuture
                .supplyAsync(() -> {
                    sleep(1000);
                    return "result A on thread " + Thread.currentThread().getId();
                })
                .thenApply(str -> str + "+ tailed")
                .thenAccept(finalResult -> System.out.println(finalResult));

        System.out.println("Task execution requested on on thread " + Thread.currentThread().getId());
    }

    public static void test3() {
        CompletableFuture
            .runAsync(() -> {
                sleep(1000);
                System.out.println("runAsync on thread " + Thread.currentThread().getId());
            })
            .thenRunAsync(
                () -> System.out.println("thenRunAsync on thread " + Thread.currentThread().getId())
            );

        System.out.println("Task execution requested on thread " + Thread.currentThread().getId());
    }

    public static void test4() throws ExecutionException, InterruptedException {
        ExecutorService e = Executors.newCachedThreadPool();

        long startTime = System.currentTimeMillis();

        CompletableFuture cf1 =
            CompletableFuture
                .supplyAsync(() -> {
                    sleep(5000);
                    System.out.println("cf1 supplyAsync on thread "
                                       + Thread.currentThread().getId() + " now="
                                       + (System.currentTimeMillis() - startTime));

                    return 100;
                });

        CompletableFuture cf2 =
            CompletableFuture
                .supplyAsync(() -> {
                    sleep(1000);
                    System.out.println("cf2 supplyAsync on thread "
                                       + Thread.currentThread().getId() + " now="
                                       + (System.currentTimeMillis() - startTime));

                    return 200;
                });

        CompletableFuture cf3 =
            CompletableFuture
                .supplyAsync(() -> {
                    sleep(3000);
                    System.out.println("cf3 supplyAsync on thread "
                                       + Thread.currentThread().getId() + " now="
                                       + (System.currentTimeMillis() - startTime));

                    return 300;
                });

        System.out.println("Task execution requested on thread " + Thread.currentThread().getId());

        cf3.thenComposeAsync((data1) -> cf2).thenComposeAsync((data2) -> cf1).join();

        System.out.println("final cf1.get()=" + cf1.get()
                           + ", cf2.get()=" + cf2.get()
                           + ", cf3.get()=" + cf3.get()
                           + ", now=" + (System.currentTimeMillis() - startTime));
    }

    public static void test_whenComplete() {
        CompletableFuture cf =
            CompletableFuture
                .supplyAsync(() -> {
                    sleep(1000);
                    return "result A on thread " + Thread.currentThread().getId();
                })
                .thenApply(str -> str + "+ concatenated");

        cf.whenComplete((result, cause) -> {
            System.out.println("completed");
            System.out.println(result);
        }).thenAccept((o) -> {
            System.out.println("then accept");
            System.out.println(o);
            sleep(-100);
        }).exceptionally(thrown -> {
            System.out.println("exceptionally");
            System.out.println(thrown);
            return thrown;
        });
    }
}
