package generic.wildcard;

public class WildCardSample {
    public static void main(String[] ar) {
        WildCardSample ex = new WildCardSample();
        ex.callWildCardMethod();
    }

    public void callWildCardMethod() {
        WildCardGeneric<String> wildcard = new WildCardGeneric<String>();
        wildcard.setWildCard("A");
        wildcardStringMethod(wildcard);
        // A

        WildCardGeneric<Integer> wildcard2 = new WildCardGeneric<Integer>();
        wildcard2.setWildCard(777);
        wildcardStringMethod2(wildcard2);
        // 777

        wildcardStringMethod2(wildcard);
        // A

        // wildcardStringMethod(wildcard2);

        wildcardStringMethod2(wildcard2);
        // 777
    }

    public void wildcardStringMethod(WildCardGeneric<String> c) {
        String value = c.getWildCard();
        System.out.println(value);
    }

    public void wildcardStringMethod2(WildCardGeneric<?> c) {
        Object value = c.getWildCard();
        System.out.println(value);
    }
}
