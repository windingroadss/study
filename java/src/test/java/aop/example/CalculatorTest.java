package aop.example;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import aop.TestJavaApplicationTest;

public class CalculatorTest extends TestJavaApplicationTest {
    // this is aop test

    @Autowired
    private Calculator calculator;

    @Test
    public void test_sum() {
        calculator.sum(1, 5);
    }

    @Test
    public void test_subtract() {
        calculator.subtract(5, 4);
    }
}

