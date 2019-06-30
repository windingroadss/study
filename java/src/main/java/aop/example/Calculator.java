package aop.example;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

    public int sum(int a, int b) {
        return a + b;
    }

    @Deprecated
    public int subtract(int a, int b) {
        return a + b;
    }
}
