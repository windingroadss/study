package thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ThreadLocalExample {

  public static class MyRunnable implements Runnable {

    private ThreadLocal<ArrayList<Integer>> threadLocal = new ThreadLocal<ArrayList<Integer>>();
    private int a;
    private String name;

    public MyRunnable(String name) {
      this.name = name;
    }

    public void run() {
      ArrayList<Integer> arrayList = new ArrayList<Integer>();
      //threadLocal.set((int) (Math.random() * 100D));
      arrayList.add(1);
      threadLocal.set(arrayList);
      arrayList.add(2);

      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
      }

      threadLocal.get().forEach((n) -> System.out.println(n.toString()));
      System.out.println(this.name + ":" + threadLocal.get().get(0));
      System.out.println(this.name + ":" + threadLocal.get());
      System.out.println(this.name + " a: " + a);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    MyRunnable sharedRunnableInstance1 = new MyRunnable("thread1");
    MyRunnable sharedRunnableInstance2 = new MyRunnable("thread2");

    Thread thread1 = new Thread(sharedRunnableInstance1);
    Thread thread2 = new Thread(sharedRunnableInstance2);

    thread1.start();
    thread2.start();

    thread1.join(); //wait for thread 1 to terminate
    thread2.join(); //wait for thread 2 to terminate
  }

  @Test
  public void genericCall() {
    MDCThreadLocal.genericCall("test");
    MDCThreadLocal.genericCall(String.class);
  }

}