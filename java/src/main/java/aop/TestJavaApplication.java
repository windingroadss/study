package aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan("aop.*")
public class TestJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestJavaApplication.class, args);
    }
}

