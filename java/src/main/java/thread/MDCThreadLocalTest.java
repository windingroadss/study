package thread;

public class MDCThreadLocalTest {
  public static void main(String[] args) throws InterruptedException {
    MDCThreadLocal.get();
    MDCThreadLocal.get();
  }
}
