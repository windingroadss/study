package generic.wildcard;

public class WildCardGeneric<W> {
  W wildCard;

  public WildCardGeneric() {
  }

  public WildCardGeneric(W w) {
    wildCard = w;
  }

  public void setWildCard(W wildCard) {
    this.wildCard = wildCard;
  }

  public W getWildCard() {
    return wildCard;
  }
}
