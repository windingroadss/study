package guava;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

public class SetsTest {

    @Test
    public void testCombineResults() {
        Set<String> combinedReceivedMidsAddAll = Sets.newHashSet();
        Set<String> combinedReceivedMidsUnion = Sets.newHashSet();

        combinedReceivedMidsAddAll.add("1");
        combinedReceivedMidsAddAll.add("2");

        combinedReceivedMidsUnion.add("1");
        combinedReceivedMidsUnion.add("3");

        ArrayList<ShareToTalkResult> resultArrayList = new ArrayList<>();

        ShareToTalkResult shareToTalkResult = new ShareToTalkResult();
        shareToTalkResult.setReceivedMids(new HashSet<String>());

        shareToTalkResult.getReceivedMids().add("2");
        shareToTalkResult.getReceivedMids().add("3");
        shareToTalkResult.getReceivedMids().add("4");

        resultArrayList.add(shareToTalkResult);

        for (ShareToTalkResult result : resultArrayList) {
            if (result != null) {
                combinedReceivedMidsAddAll.addAll(result.getReceivedMids());
                combinedReceivedMidsUnion = Sets.union(combinedReceivedMidsUnion, result.getReceivedMids());
            }
        }

        Assertions.assertEquals(combinedReceivedMidsAddAll, combinedReceivedMidsUnion);
    }

}
