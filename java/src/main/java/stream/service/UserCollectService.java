package stream.service;

import java.util.stream.Collector;
import java.util.stream.Stream;

import stream.model.User;

public class UserCollectService {

    private Collector<User, Integer, Stream<User>> getUserCollector() {
        //Collectors.groupingBy()
        return null;
    }
}
