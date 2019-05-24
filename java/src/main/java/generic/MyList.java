package generic;

import org.junit.jupiter.api.Test;

// E is the type parameter
public class MyList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object element[];
    private int index;

    public MyList() {
        element = new Object[DEFAULT_CAPACITY];
    }

    public void add(E e) {
        this.element[index++] = e;
    }

    public E get(int index) {
        return (E) element[index];
    }

    @Test
    public void testMyList() {
        MyList<String> myList = new MyList<>();
        myList.add("windingroad");
        myList.add("windingroadss");
        //myList.add(1); //컴파일 에러
        System.out.println(myList.get(0));
        System.out.println(myList.get(1));
    }
}