package math;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class Pow {

    @Test
    public void test_pow() {
        Set<PostStatType> postStatTypes = new HashSet<>();
        postStatTypes.add(PostStatType.TEXT);
        postStatTypes.add(PostStatType.LARGE_TEXT);
        postStatTypes.add(PostStatType.HASHTAG);

        int pst = PostStatType.getStatType(postStatTypes);

        String str = "";
        if ((pst & (0x1<<PostStatType.TEXT.getNum())) != 0) str += "PLAIN,";
        if ((pst & (0x1<<PostStatType.LARGE_TEXT.getNum())) != 0) str += "LARGE_TEXT,";
        if ((pst & (0x1<<PostStatType.TEXT_CARD.getNum())) != 0) str += "TEXTCARD,";

        System.out.println(str);
    }
}
