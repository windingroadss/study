package stream.comparator;

import java.util.Comparator;

import stream.model.User;

public class UserComparator implements Comparator<User> {

    static final UserComparator INSTANCE = new UserComparator();

    public static UserComparator getInstance() {
        return INSTANCE;
    }

    @Override
    public int compare(User o1, User o2) {
        // 1: o1, 0: o1=o2, -1: o2
        return o1.getId().compareTo(o2.getId());
    }
}
