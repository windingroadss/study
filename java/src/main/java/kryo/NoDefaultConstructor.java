package kryo;

public abstract class NoDefaultConstructor {

  private int number;

  public NoDefaultConstructor(int number) {
    this.number = number;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }
}
