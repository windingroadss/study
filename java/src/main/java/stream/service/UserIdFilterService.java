package stream.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import stream.model.User;

@Service
public class UserIdFilterService implements FilterService<User> {

    @Override
    public Stream<User> filter(Stream<User> userStream) {
        Set<String> blockedUser = new HashSet<>();
        blockedUser.add("windingroad");

        if(CollectionUtils.isEmpty(blockedUser)) {
            return null;
        }

        return userStream.filter(user -> blockedUser.contains(user.getId()) == false);
    }
}
