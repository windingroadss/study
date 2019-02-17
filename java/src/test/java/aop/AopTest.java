package aop;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AopTest {

  @Test
  public void sum() {
    Calculator.sum(1, 5);
  }
}
