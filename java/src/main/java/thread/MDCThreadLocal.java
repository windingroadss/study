package thread;

public class MDCThreadLocal<T> {
    static final ThreadLocal threadLocal = new ThreadLocal();
    static int a = (int) (Math.random() * 100D);

    private MDCThreadLocal() {
        threadLocal.set((int) (Math.random() * 100D));
    }

    public static void get() {
        System.out.println(threadLocal);
    }

    public static <E> void genericCall(E e) {

    }

    public static <E> void genericCall(Class<E> e) {

    }

}
