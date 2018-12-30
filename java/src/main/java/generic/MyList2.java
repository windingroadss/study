package generic;

public class MyList2<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private E element[];
  private int index;

  public MyList2() {
    // Type parameter 'E' cannot be instantiated directly
    // element = new E[DEFAULT_CAPACITY];
  }

  public void add(E e) {
    this.element[index++] = e;
  }

  public E get(int index){
    return element[index];
  }
}

