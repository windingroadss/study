package stream.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import stream.model.User;

@Service
public class UserCollectService {

    public Stream<User> getGroupedUserStream(Stream<User> userStream) {
        return userStream.collect(getUserCollector());
    }

    public Collector<User, ?, Stream<User>> getUserCollector() {
        Collector<User, ?, HashMap<String, List<User>>> collector = Collectors.groupingBy(getUser(),
                                                                                          HashMap::new,
                                                                                          Collectors.toList());
        return Collectors.collectingAndThen(collector, this::convertAfterGrouping);
    }

    private Function<User, String> getUser() {
        return user -> {
            String groupName;
            if(user.getAge() <= 10) {
                groupName = "too young";
            } else if (user.getAge() <= 20) {
                groupName = "little young";
            } else {
                groupName = "young";
            }
            return groupName;
        };
    }

    private Stream<User> convertAfterGrouping(Map<String, List<User>> map) {
        return map.entrySet()
                  .stream()
                  .flatMap(entry -> {
                      String groupName = entry.getKey();
                      Stream<User> userStream = entry.getValue().stream();
                      return userStream.peek(user -> user.setGroupName(groupName));
                  });
    }
}
