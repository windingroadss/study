package guava;

import java.util.Set;

import com.google.common.collect.Sets;

public class ShareToTalkResult {
  private Set<String> receivedMids;
  private Set<String> failedMids;

  public ShareToTalkResult() {

  }

  public ShareToTalkResult(Set<String> receivedMids, Set<String> failedMids) {
    this.receivedMids = receivedMids;
    this.failedMids = failedMids;
  }

  public Set<String> getReceivedMids() {
    return receivedMids;
  }

  public void setReceivedMids(Set<String> receivedMids) {
    this.receivedMids = receivedMids;
  }

  public Set<String> getFailedMids() {
    return failedMids;
  }

  public void setFailedMids(Set<String> failedMids) {
    this.failedMids = failedMids;
  }

  public static ShareToTalkResult combineResults(ShareToTalkResult... results) {
    Set<String> combinedReceivedMids = Sets.newHashSet();
    Set<String> combinedFailedMids = Sets.newHashSet();

    for (ShareToTalkResult result : results) {
      if (result != null) {
        combinedReceivedMids.addAll(result.getReceivedMids());
        combinedFailedMids.addAll(result.getFailedMids());
      }
    }

    return new ShareToTalkResult(combinedReceivedMids, combinedFailedMids);
  }
}
