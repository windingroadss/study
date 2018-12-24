package thread;

public class MDCThreadLocal {
    static final ThreadLocal threadLocal = new ThreadLocal();
    static int a = (int) (Math.random() * 100D);

    private MDCThreadLocal() {
        threadLocal.set((int) (Math.random() * 100D));
    }

    public static void get() {
        System.out.println(threadLocal);
    }
}
