package stream.service;

import java.util.stream.Stream;

public interface FilterService<T> {
    Stream<T> filter(Stream<T> stream);
}
