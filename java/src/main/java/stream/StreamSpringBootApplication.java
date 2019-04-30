package stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("stream.*")
public class StreamSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamSpringBootApplication.class, args);
    }
}
