package stream.service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import stream.model.User;

@Slf4j
@Service
public class DuplicateUserFilterService implements FilterService<User> {

    @Override
    public Stream<User> filter(Stream<User> userStream) {
        log.debug("DuplicateUserFilterService");
        return userStream.filter(distinctByKey(User::getId));
    }

    // Function -> User: input, String: output
    private static Predicate<User> distinctByKey(Function<User, String> keyExtractor) {
        final Set<String> seen = new HashSet<>();
        return t -> {
            final boolean add = seen.add(keyExtractor.apply(t));
            if (log.isDebugEnabled() && add == false) {
                log.debug("[filtered] duplicated.. value:{}", t);
            }
            return add;
        };
    }

}
