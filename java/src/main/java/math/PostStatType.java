package math;

import java.util.Set;

import org.springframework.util.CollectionUtils;

public enum PostStatType {
    TEXT(0),
    PHOTO(1),
    STICKER(2),
    MOVIE(3),
    LOCATION(4),
    LINK(5),
    SNAP(6),
    SHARE(7),
    RECALL(8),
    HASHTAG(9),
    ANIGIF(10),
    POP(11),
    LARGE_TEXT(12),
    RELAY(13),
    RELAY_JOINED(14),
    TEXT_CARD(15),
    BIRTHDAY(16),
    INTERACTIVE_MEDIA(17);

    private int num;

    private PostStatType(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public static int getStatType(Set<PostStatType> statList) {
        if (CollectionUtils.isEmpty(statList)) {
            return 0;
        }

        int sum = 0;
        for (PostStatType type : statList) {
            sum += (int)Math.pow(2, type.getNum());
        }
        return sum;
    }

    public static int addStatType(PostStatType statType, int originValue) {
        if (statType == null) {
            return originValue;
        }

        return (originValue += (int) Math.pow(2, statType.getNum()));
    }
}