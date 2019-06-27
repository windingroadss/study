package effectivejava.item41;

public class Main {
    public static void main(String[] args) {
        Class<?> testClass = TestClassForAnnotation.class;

        if (testClass.isAnnotationPresent(CustomAnnotation.class)) {
            System.out.println("CustomAnnotation is present");
        }

        //CustomInterface customInterface = new TestClassForInterface();
    }
}
